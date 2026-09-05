package com.resumeai.resume_analyzer.controller;

import com.resumeai.resume_analyzer.model.AnalysisResponse;
import com.resumeai.resume_analyzer.model.ResumeOptimizationRequest;
import com.resumeai.resume_analyzer.model.ResumeOptimizationResponse;
import com.resumeai.resume_analyzer.service.ResumeService;
import com.resumeai.resume_analyzer.service.ai.AIResumeOptimizationService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import tools.jackson.databind.json.JsonMapper;

import java.util.Map;

@RestController
@RequestMapping("/api/resume")
@CrossOrigin
public class ResumeController {

    private final ResumeService resumeService;
    private final AIResumeOptimizationService optimizationService;
    private final JsonMapper objectMapper;

    public ResumeController(
            ResumeService resumeService,
            AIResumeOptimizationService optimizationService,
            JsonMapper objectMapper) {

        this.resumeService = resumeService;
        this.optimizationService = optimizationService;
        this.objectMapper = objectMapper;
    }


    /*
     * ============================================================
     * RESUME ANALYSIS
     * ============================================================
     */

    @PostMapping("/analyze")
    public ResponseEntity<?> analyzeResume(
            @RequestParam("file") MultipartFile file,
            @RequestParam("jobDescription") String jobDescription) {

        try {

            if (file == null || file.isEmpty()) {

                return ResponseEntity
                        .badRequest()
                        .body(Map.of(
                                "error",
                                "Please upload a resume PDF."
                        ));
            }


            if (jobDescription == null ||
                    jobDescription.isBlank()) {

                return ResponseEntity
                        .badRequest()
                        .body(Map.of(
                                "error",
                                "Please enter a job description."
                        ));
            }


            String resumeText =
                    resumeService.extractTextFromPdf(file);


            if (resumeText == null ||
                    resumeText.isBlank()) {

                return ResponseEntity
                        .badRequest()
                        .body(Map.of(
                                "error",
                                "Could not extract text from the PDF."
                        ));
            }


            AnalysisResponse response =
                    resumeService.analyzeResume(
                            resumeText,
                            jobDescription
                    );


            return ResponseEntity.ok(response);


        } catch (Exception e) {

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of(
                            "error",
                            "Resume analysis failed.",
                            "message",
                            getSafeErrorMessage(e)
                    ));
        }
    }


    /*
     * ============================================================
     * AI RESUME OPTIMIZATION
     * ============================================================
     */

    @PostMapping("/optimize")
    public ResponseEntity<?> optimizeResume(
            @RequestBody ResumeOptimizationRequest request) {

        try {

            if (request == null) {

                return ResponseEntity
                        .badRequest()
                        .body(Map.of(
                                "error",
                                "Optimization request is missing."
                        ));
            }


            if (request.getResumeText() == null ||
                    request.getResumeText().isBlank()) {

                return ResponseEntity
                        .badRequest()
                        .body(Map.of(
                                "error",
                                "Resume content is empty."
                        ));
            }


            if (request.getJobDescription() == null ||
                    request.getJobDescription().isBlank()) {

                return ResponseEntity
                        .badRequest()
                        .body(Map.of(
                                "error",
                                "Job description is empty."
                        ));
            }


            String aiResult =
                    optimizationService.optimizeResume(
                            request.getResumeText(),
                            request.getJobDescription()
                    );


            if (aiResult == null ||
                    aiResult.isBlank()) {

                return ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(Map.of(
                                "error",
                                "AI returned an empty response."
                        ));
            }


            ResumeOptimizationResponse response =
                    objectMapper.readValue(
                            aiResult,
                            ResumeOptimizationResponse.class
                    );


            return ResponseEntity.ok(response);


        } catch (Exception e) {

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of(
                            "error",
                            "Resume optimization failed.",
                            "message",
                            getSafeErrorMessage(e)
                    ));
        }
    }


    /*
     * ============================================================
     * ERROR MESSAGE HELPER
     * ============================================================
     */

    private String getSafeErrorMessage(Exception e) {

        Throwable cause = e;

        while (cause.getCause() != null) {
            cause = cause.getCause();
        }

        String message = cause.getMessage();

        if (message == null ||
                message.isBlank()) {

            return "An unexpected error occurred.";
        }

        return message;
    }
}