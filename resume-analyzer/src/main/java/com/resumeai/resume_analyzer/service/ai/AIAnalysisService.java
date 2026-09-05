package com.resumeai.resume_analyzer.service.ai;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.GenerateContentResponse;
import com.google.genai.types.Schema;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AIAnalysisService {

    private final Client client;

    public AIAnalysisService() {
        this.client = new Client();
    }

    public String analyzeResumeWithAI(
            String resumeText,
            String jobDescription) {

        String prompt = """
                You are an expert ATS resume evaluator,
                technical recruiter, and resume information extractor.

                Analyze the candidate's resume against the job description.

                ============================================================
                IMPORTANT ATS RULES
                ============================================================

                - Do not reward simple keyword stuffing.
                - A skill should count strongly only when the resume provides
                  evidence that the candidate actually used it.
                - Distinguish between skills merely mentioned and skills
                  demonstrated through projects or professional experience.
                - Do not invent experience.
                - Do not invent technologies.
                - Do not invent achievements.
                - Do not invent job titles.
                - Do not invent companies.
                - Do not invent dates.
                - Do not invent metrics.
                - Extract information only from the resume.
                - If information is not present, return an empty string
                  or an empty array.
                - Evaluate the actual requirements of the job.
                - Consider technical skills, projects, experience,
                  technologies, responsibilities, APIs, databases,
                  frontend, backend, architecture, cloud, deployment,
                  testing and Git.
                - HTML/CSS alone must never produce a high
                  Full Stack Developer score.


                ============================================================
                ATS SCORE
                ============================================================

                Give a realistic ATS score from 0 to 100.

                SCORE GUIDELINE:

                90-100 = exceptional match
                80-89  = very strong match
                70-79  = strong match
                60-69  = reasonable match
                50-59  = partial match
                40-49  = weak match
                0-39   = poor match


                ============================================================
                MATCHED SKILLS
                ============================================================

                List important skills from the job description that are
                actually demonstrated in the resume.

                Briefly explain the evidence.

                Example:

                "Java — demonstrated through Spring Boot backend project."


                ============================================================
                MISSING SKILLS
                ============================================================

                List important job requirements that are absent or
                insufficiently demonstrated in the resume.

                Do not mark a skill missing if the resume clearly
                demonstrates it.


                ============================================================
                STRENGTHS
                ============================================================

                Identify the candidate's strongest areas relevant to
                the job description.


                ============================================================
                WEAKNESSES
                ============================================================

                Identify important weaknesses or gaps that reduce
                the candidate's suitability for the job.


                ============================================================
                SUGGESTIONS
                ============================================================

                Give specific and actionable suggestions for improving
                the resume for this job.


                ============================================================
                OVERALL ASSESSMENT
                ============================================================

                Give a concise overall assessment of the candidate's
                suitability for the job.


                ============================================================
                RESUME INFORMATION EXTRACTION
                ============================================================

                Extract the following information from the resume.

                NAME:

                Extract the candidate's full name.


                EMAIL:

                Extract the email address.


                PHONE:

                Extract the phone number.


                LOCATION:

                Extract city/state/country if available.


                LINKEDIN:

                Extract the complete LinkedIn URL if available.

                Preserve the URL exactly as it appears in the resume.

                Do not modify, shorten, rewrite, or invent the URL.


                GITHUB:

                Extract the complete GitHub URL if available.

                Preserve the URL exactly as it appears in the resume.

                Do not modify, shorten, rewrite, or invent the URL.


                PORTFOLIO:

                Extract the candidate's personal portfolio,
                personal website, or professional website URL
                if available.

                Preserve the URL exactly as it appears in the resume.

                Do not modify, shorten, rewrite, or invent the URL.

                If no portfolio or personal website URL exists,
                return an empty string.

                Do not use LinkedIn or GitHub as the portfolio URL.


                SUMMARY:

                Extract the existing professional summary/objective if
                the resume contains one.

                Do NOT create a new summary at this stage.


                SKILLS:

                Extract the candidate's actual technical and professional
                skills from the resume.


                EXPERIENCE:

                Extract professional experience including company,
                role, duration, responsibilities and achievements.


                PROJECTS:

                Extract projects including project name, technologies,
                responsibilities and achievements.

                Preserve any project GitHub or Demo URLs exactly
                as they appear in the resume.

                Do not invent project URLs.


                EDUCATION:

                Extract degrees, institutions, dates and grades/CGPA
                when available.


                CERTIFICATIONS:

                Extract certifications and issuing organizations.

                Preserve certificate URLs exactly if available.

                Do not invent certificate URLs.


                ACHIEVEMENTS:

                Extract awards, hackathons, competitions, leadership
                achievements and other notable accomplishments.


                ============================================================
                URL PRESERVATION RULES
                ============================================================

                IMPORTANT:

                URLs are factual information.

                Never modify an existing URL.

                Never shorten an existing URL.

                Never replace an existing URL.

                Never invent a URL.

                Never remove an existing URL.

                Preserve URLs exactly as they appear in the original
                resume.

                Keep each URL associated with the correct person,
                project, certification, or section.


                ============================================================
                JOB DESCRIPTION
                ============================================================

                %s


                ============================================================
                RESUME
                ============================================================

                %s
                """.formatted(
                jobDescription,
                resumeText
        );


        /*
         * ============================================================
         * STRUCTURED JSON SCHEMA
         * ============================================================
         *
         * Map.of() supports only a limited number of entries.
         * Map.ofEntries() allows us to define all resume fields.
         */

        Schema responseSchema =
                Schema.builder()
                        .type("OBJECT")
                        .properties(Map.ofEntries(

                                Map.entry(
                                        "atsScore",
                                        Schema.builder()
                                                .type("INTEGER")
                                                .build()
                                ),

                                Map.entry(
                                        "matchedSkills",
                                        Schema.builder()
                                                .type("ARRAY")
                                                .items(
                                                        Schema.builder()
                                                                .type("STRING")
                                                                .build()
                                                )
                                                .build()
                                ),

                                Map.entry(
                                        "missingSkills",
                                        Schema.builder()
                                                .type("ARRAY")
                                                .items(
                                                        Schema.builder()
                                                                .type("STRING")
                                                                .build()
                                                )
                                                .build()
                                ),

                                Map.entry(
                                        "strengths",
                                        Schema.builder()
                                                .type("ARRAY")
                                                .items(
                                                        Schema.builder()
                                                                .type("STRING")
                                                                .build()
                                                )
                                                .build()
                                ),

                                Map.entry(
                                        "weaknesses",
                                        Schema.builder()
                                                .type("ARRAY")
                                                .items(
                                                        Schema.builder()
                                                                .type("STRING")
                                                                .build()
                                                )
                                                .build()
                                ),

                                Map.entry(
                                        "suggestions",
                                        Schema.builder()
                                                .type("ARRAY")
                                                .items(
                                                        Schema.builder()
                                                                .type("STRING")
                                                                .build()
                                                )
                                                .build()
                                ),

                                Map.entry(
                                        "overallAssessment",
                                        Schema.builder()
                                                .type("STRING")
                                                .build()
                                ),

                                Map.entry(
                                        "name",
                                        Schema.builder()
                                                .type("STRING")
                                                .build()
                                ),

                                Map.entry(
                                        "email",
                                        Schema.builder()
                                                .type("STRING")
                                                .build()
                                ),

                                Map.entry(
                                        "phone",
                                        Schema.builder()
                                                .type("STRING")
                                                .build()
                                ),

                                Map.entry(
                                        "location",
                                        Schema.builder()
                                                .type("STRING")
                                                .build()
                                ),

                                Map.entry(
                                        "linkedin",
                                        Schema.builder()
                                                .type("STRING")
                                                .build()
                                ),

                                Map.entry(
                                        "github",
                                        Schema.builder()
                                                .type("STRING")
                                                .build()
                                ),

                                Map.entry(
                                        "portfolio",
                                        Schema.builder()
                                                .type("STRING")
                                                .build()
                                ),

                                Map.entry(
                                        "summary",
                                        Schema.builder()
                                                .type("STRING")
                                                .build()
                                ),

                                Map.entry(
                                        "skills",
                                        Schema.builder()
                                                .type("ARRAY")
                                                .items(
                                                        Schema.builder()
                                                                .type("STRING")
                                                                .build()
                                                )
                                                .build()
                                ),

                                Map.entry(
                                        "experience",
                                        Schema.builder()
                                                .type("STRING")
                                                .build()
                                ),

                                Map.entry(
                                        "projects",
                                        Schema.builder()
                                                .type("STRING")
                                                .build()
                                ),

                                Map.entry(
                                        "education",
                                        Schema.builder()
                                                .type("STRING")
                                                .build()
                                ),

                                Map.entry(
                                        "certifications",
                                        Schema.builder()
                                                .type("STRING")
                                                .build()
                                ),

                                Map.entry(
                                        "achievements",
                                        Schema.builder()
                                                .type("STRING")
                                                .build()
                                )

                        ))
                        .required(List.of(

                                "atsScore",
                                "matchedSkills",
                                "missingSkills",
                                "strengths",
                                "weaknesses",
                                "suggestions",
                                "overallAssessment",

                                "name",
                                "email",
                                "phone",
                                "location",
                                "linkedin",
                                "github",
                                "portfolio",

                                "summary",
                                "skills",
                                "experience",
                                "projects",
                                "education",
                                "certifications",
                                "achievements"

                        ))
                        .build();


        /*
         * ============================================================
         * GENERATION CONFIG
         * ============================================================
         */

        GenerateContentConfig config =
                GenerateContentConfig.builder()
                        .responseMimeType("application/json")
                        .responseSchema(responseSchema)
                        .build();


        /*
         * ============================================================
         * GEMINI REQUEST
         * ============================================================
         */

        GenerateContentResponse response =
                client.models.generateContent(
                        "gemini-3.7-flash",
                        prompt,
                        config
                );


        return response.text();
    }
}