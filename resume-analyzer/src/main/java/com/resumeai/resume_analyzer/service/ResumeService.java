package com.resumeai.resume_analyzer.service;

import tools.jackson.databind.json.JsonMapper;
import com.resumeai.resume_analyzer.model.AnalysisResponse;
import com.resumeai.resume_analyzer.service.ai.AIAnalysisService;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ResumeService {

    private final AIAnalysisService aiAnalysisService;
    private final JsonMapper objectMapper;

    public ResumeService(
            AIAnalysisService aiAnalysisService,
            JsonMapper objectMapper) {

        this.aiAnalysisService = aiAnalysisService;
        this.objectMapper = objectMapper;
    }

    public String extractTextFromPdf(MultipartFile file) throws IOException {

        if (file == null || file.isEmpty()) {
            throw new IOException("Resume PDF is empty or missing.");
        }

        try (PDDocument document = Loader.loadPDF(file.getBytes())) {

            PDFTextStripper pdfTextStripper = new PDFTextStripper();

            return pdfTextStripper.getText(document);
        }
    }

    public AnalysisResponse analyzeResume(
            String resumeText,
            String jobDescription) {

        if (resumeText == null || resumeText.isBlank()) {
            throw new IllegalArgumentException(
                    "Resume text cannot be empty."
            );
        }

        if (jobDescription == null || jobDescription.isBlank()) {
            throw new IllegalArgumentException(
                    "Job description cannot be empty."
            );
        }

        String aiResult =
                aiAnalysisService.analyzeResumeWithAI(
                        resumeText,
                        jobDescription
                );

        return parseAIResponse(aiResult);
    }

    private AnalysisResponse parseAIResponse(String aiResult) {

        if (aiResult == null || aiResult.isBlank()) {

            throw new RuntimeException(
                    "AI returned an empty analysis."
            );
        }

        try {

            AnalysisResponse response =
                    objectMapper.readValue(
                            aiResult,
                            AnalysisResponse.class
                    );

            response.setAtsScore(
                    Math.max(
                            0,
                            Math.min(
                                    100,
                                    response.getAtsScore()
                            )
                    )
            );

            return response;

        } catch (Exception e) {

            throw new RuntimeException(
                    "Failed to parse AI analysis: "
                            + e.getMessage(),
                    e
            );
        }
    }
}