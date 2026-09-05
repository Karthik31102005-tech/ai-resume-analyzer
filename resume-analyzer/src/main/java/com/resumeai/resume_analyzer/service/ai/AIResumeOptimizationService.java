package com.resumeai.resume_analyzer.service.ai;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.GenerateContentResponse;
import com.google.genai.types.Schema;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AIResumeOptimizationService {

    private final Client client;

    public AIResumeOptimizationService() {
        this.client = new Client();
    }

    public String optimizeResume(
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

        String prompt = """
                You are an expert ATS resume writer,
                technical recruiter, and resume optimization specialist.

                Your task is to optimize the candidate's resume
                specifically for the provided job description.

                ============================================================
                CRITICAL RULES
                ============================================================

                1. NEVER invent information.

                2. NEVER invent:
                   - Companies
                   - Job titles
                   - Technologies
                   - Projects
                   - Achievements
                   - Metrics
                   - Dates
                   - Responsibilities
                   - Certifications
                   - Education
                   - Work experience

                3. Use ONLY information that exists in the original resume.

                4. You may improve:
                   - Grammar
                   - Wording
                   - Structure
                   - Clarity
                   - Conciseness
                   - ATS relevance
                   - Professional presentation

                5. You may reorder existing skills and information
                   to emphasize relevance to the job.

                6. You may use terminology from the job description
                   ONLY when the original resume provides evidence
                   that the candidate has that skill or experience.

                7. Do NOT add a skill simply because it appears
                   in the job description.

                8. Do NOT keyword stuff.

                9. Do NOT create fake achievements or metrics.

                10. Preserve all factual information.


                ============================================================
                IMPORTANT URL PRESERVATION RULES
                ============================================================

                11. NEVER modify, rewrite, shorten, replace, or remove
                    any URL from the original resume.

                12. Preserve every existing URL EXACTLY as provided,
                    character-for-character.

                13. Project URLs must remain attached to their
                    corresponding project.

                14. Certificate URLs must remain attached to their
                    corresponding certificate.

                15. Do NOT convert URLs into markdown links such as:
                    [GitHub](URL)

                16. Do NOT convert URLs into HTML links.

                17. Return URLs as plain text inside the structured
                    response fields.

                18. For certifications, whenever certificate information
                    exists, preserve this exact four-line structure:

                    Certificate Name
                    Issuing Organization
                    Year
                    Certificate URL

                19. For projects, whenever project information exists,
                    preserve this exact four-line structure:

                    Project Name
                    Project Description
                    GitHub URL
                    Demo URL

                20. If a GitHub URL does not exist in the original resume,
                    leave the GitHub URL field empty.

                    NEVER invent a GitHub URL.

                21. If a Demo URL does not exist in the original resume,
                    leave the Demo URL field empty.

                    NEVER invent a Demo URL.

                22. If a Certificate URL does not exist in the original
                    resume, leave the Certificate URL field empty.

                    NEVER invent a Certificate URL.

                23. Do NOT move a URL from one project or certification
                    to another.

                24. Do NOT associate a URL with the wrong project,
                    certification, or section.

                25. URLs are factual data and must be preserved even
                    when the surrounding text is rewritten.


                ============================================================
                PROFESSIONAL SUMMARY
                ============================================================

                Rewrite the existing summary for the target job.

                Make it concise, professional and ATS-friendly.

                Use only facts supported by the resume.

                If there is no summary, create one ONLY from
                information explicitly available in the resume.


                ============================================================
                SKILLS
                ============================================================

                Organize the candidate's existing skills.

                Put the most relevant existing skills first.

                Do not add unsupported skills.


                ============================================================
                EXPERIENCE
                ============================================================

                Rewrite existing experience descriptions.

                Use strong action verbs.

                Make responsibilities clear and concise.

                Emphasize relevant technologies and responsibilities
                that are already present in the resume.

                Do not change factual information.


                ============================================================
                PROJECTS
                ============================================================

                Rewrite existing project descriptions.

                Clearly communicate:

                - Project name
                - Technologies used, if present
                - What was implemented
                - Candidate's contribution

                Do not invent functionality.

                IMPORTANT:

                Preserve the project structure exactly as follows
                whenever project information exists:

                Project Name
                Project Description
                GitHub URL
                Demo URL

                If a URL does not exist, keep its corresponding line empty.

                Example:

                AI Resume Analyzer
                Spring Boot based AI resume analysis application.
                https://github.com/example/resume-analyzer
                https://example.com/demo


                ============================================================
                EDUCATION
                ============================================================

                Preserve the candidate's actual education.

                Improve formatting and clarity only.


                ============================================================
                CERTIFICATIONS
                ============================================================

                Preserve only certifications present in the resume.

                IMPORTANT:

                Preserve the certification structure exactly as follows:

                Certificate Name
                Issuing Organization
                Year
                Certificate URL

                Do not remove certificate URLs.

                Do not modify certificate URLs.

                Do not invent certificate URLs.

                Example:

                Java Programming
                NPTEL
                2025
                https://certificate-link.com


                ============================================================
                ACHIEVEMENTS
                ============================================================

                Preserve actual achievements.

                Improve wording and presentation.

                Do not invent achievements.


                ============================================================
                OUTPUT
                ============================================================

                Return the optimized resume divided into the following
                JSON fields:

                summary
                skills
                experience
                projects
                education
                certifications
                achievements

                Each field must contain only its corresponding section.

                Do not include markdown code fences.

                Do not include explanations outside the JSON.

                IMPORTANT:

                The "projects" field must preserve project URLs
                as plain text.

                The "certifications" field must preserve certificate URLs
                as plain text.

                Never replace a URL with a different URL.

                Never remove an existing URL.


                ============================================================
                JOB DESCRIPTION
                ============================================================

                %s


                ============================================================
                ORIGINAL RESUME
                ============================================================

                %s
                """.formatted(
                jobDescription,
                resumeText
        );


        /*
         * ============================================================
         * STRUCTURED RESPONSE SCHEMA
         * ============================================================
         */

        Schema responseSchema =
                Schema.builder()
                        .type("OBJECT")
                        .properties(Map.ofEntries(

                                Map.entry(
                                        "summary",
                                        Schema.builder()
                                                .type("STRING")
                                                .build()
                                ),

                                Map.entry(
                                        "skills",
                                        Schema.builder()
                                                .type("STRING")
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


        String result = response.text();


        if (result == null || result.isBlank()) {

            throw new RuntimeException(
                    "AI returned an empty optimized resume."
            );
        }


        return result.trim();
    }
}