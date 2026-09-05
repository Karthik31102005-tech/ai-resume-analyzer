# AI Resume Analyzer & Optimizer

An AI-powered Resume Analyzer that evaluates resumes against a target job description, provides an ATS compatibility score, identifies skill gaps, and generates an optimized, ATS-friendly resume.

## Overview

The AI Resume Analyzer combines PDF text extraction, ATS-oriented analysis, and Google Gemini AI to help candidates understand how well their resume matches a specific job description.

The application can:

- Upload and extract text from PDF resumes
- Analyze a resume against a job description
- Generate an ATS compatibility score
- Identify matched and missing skills
- Detect resume strengths and weaknesses
- Provide actionable improvement suggestions
- Extract structured resume information
- Automatically populate a resume builder
- Optimize resume content using AI
- Preserve existing factual information
- Preserve project and certificate URLs
- Generate an ATS-friendly resume
- Support multiple resume templates
- Make LinkedIn, GitHub, portfolio, project, and certificate links clickable

---

## Key Features

### 1. Resume PDF Upload

Users can upload their resume in PDF format.

Apache PDFBox extracts the text from the uploaded document for further processing.

### 2. Job Description Analysis

Users provide the job description they are targeting.

The system compares the resume against the actual requirements of the job.

### 3. ATS Compatibility Score

The AI generates a score from 0 to 100 based on the relevance of the resume to the target job.

The analysis considers:

- Technical skills
- Projects
- Professional experience
- Technologies
- APIs
- Databases
- Frontend development
- Backend development
- Architecture
- Cloud/deployment
- Testing
- Git
- Evidence of actual skill usage

The system is designed to avoid rewarding simple keyword stuffing.

### 4. Matched Skills

The application identifies important job requirements that are actually demonstrated in the resume.

### 5. Missing Skills

The application identifies important requirements that are absent or insufficiently demonstrated.

### 6. Resume Strengths & Weaknesses

The AI identifies the strongest parts of the resume and areas that may reduce suitability for the target role.

### 7. AI Resume Optimization

The application can rewrite the resume specifically for the target job.

The optimizer can improve:

- Grammar
- Wording
- Structure
- Clarity
- Conciseness
- ATS relevance
- Professional presentation

The system does not intentionally invent:

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

### 8. URL Preservation

Existing URLs are treated as factual information.

The optimizer preserves:

- LinkedIn URLs
- GitHub URLs
- Portfolio URLs
- Project GitHub URLs
- Project Demo URLs
- Certificate URLs

URLs are not intentionally replaced with invented links.

### 9. AI Resume Builder

The extracted resume information can automatically populate the resume builder.

Supported sections include:

- Personal Information
- Professional Summary
- Skills
- Experience
- Projects
- Education
- Certifications
- Achievements

### 10. Resume Templates

The builder supports multiple resume styles:

- Professional
- Modern
- Fresher

### 11. Clickable Resume Links

The generated resume can contain clickable:

- Email
- Phone
- LinkedIn
- GitHub
- Portfolio
- Project GitHub
- Project Demo
- Certificate titles

Certificate URLs are hidden behind the certificate name rather than displayed as raw URLs.

---

# Technology Stack

## Backend

- Java
- Spring Boot
- Apache PDFBox
- Google Gemini API

## Frontend

- HTML
- CSS
- JavaScript

## AI

- Google Gemini
- Structured JSON responses
- ATS-oriented resume evaluation
- AI-powered resume optimization

## Build Tool

- Maven

---

# System Architecture

```text
                    ┌─────────────────────┐
                    │       User          │
                    └──────────┬──────────┘
                               │
                               ▼
                    ┌─────────────────────┐
                    │   Web Application   │
                    │     HTML/CSS/JS     │
                    └──────────┬──────────┘
                               │
                               ▼
                    ┌─────────────────────┐
                    │  Spring Boot API    │
                    └──────────┬──────────┘
                               │
                ┌──────────────┴──────────────┐
                │                             │
                ▼                             ▼
       ┌─────────────────┐          ┌────────────────────┐
       │   PDFBox        │          │   Gemini AI        │
       │ PDF Text        │          │ Resume Analysis    │
       │ Extraction      │          │ & Optimization     │
       └────────┬────────┘          └─────────┬──────────┘
                │                             │
                └──────────────┬──────────────┘
                               │
                               ▼
                    ┌─────────────────────┐
                    │ Structured Resume   │
                    │ Analysis / Output   │
                    └──────────┬──────────┘
                               │
                               ▼
                    ┌─────────────────────┐
                    │   Resume Builder    │
                    │    & Templates      │
                    └─────────────────────┘
1. Upload Resume PDF
          ↓
2. Extract Resume Text
          ↓
3. Enter Job Description
          ↓
4. Send Resume + JD to Gemini
          ↓
5. Analyze Resume
          ↓
6. Generate ATS Score
          ↓
7. Identify Matched/Missing Skills
          ↓
8. Display Strengths & Weaknesses
          ↓
9. Open Resume Builder
          ↓
10. AI Optimize Resume
          ↓
11. Preserve Existing Information & URLs
          ↓
12. Generate ATS-Friendly Resume
          ↓
13. Print / Save as PDF
resume-analyzer
│
├── pom.xml
│
├── README.md
│
└── src
    └── main
        ├── java
        │   └── com.resumeai.resume_analyzer
        │       │
        │       ├── ResumeAnalyzerApplication.java
        │       │
        │       ├── controller
        │       │   └── ResumeController.java
        │       │
        │       ├── model
        │       │   ├── AnalysisRequest.java
        │       │   ├── AnalysisResponse.java
        │       │   ├── ResumeOptimizationRequest.java
        │       │   └── ResumeOptimizationResponse.java
        │       │
        │       └── service
        │           ├── ResumeService.java
        │           │
        │           └── ai
        │               ├── AIAnalysisService.java
        │               └── AIResumeOptimizationService.java
        │
        └── resources
            │
            ├── application.properties
            │
            └── static
                ├── index.html
                └── builder.html

Requirements

Before running the project, make sure you have:

Java 26 or compatible Java version
Maven
IntelliJ IDEA or another Java IDE
Google Gemini API key

Gemini API Configuration

The application reads the Gemini API key from the environment.

Set:

GEMINI_API_KEY=YOUR_API_KEY

Do not hard-code the API key inside the source code.

For IntelliJ IDEA:

Run
→ Edit Configurations
→ Environment variables
→ Add GEMINI_API_KEY

Example:

GEMINI_API_KEY=your-api-key

Never commit the actual API key to GitHub.

Installation

Clone the repository:

git clone YOUR_GITHUB_REPOSITORY_URL

Open the project in IntelliJ IDEA.

Maven will automatically download the required dependencies.

Run the Application

Run:

ResumeAnalyzerApplication.java

The Spring Boot server starts on:

http://localhost:8080

Open:

http://localhost:8080

API Endpoints
Analyze Resume
POST /api/resume/analyze

Parameters:

file
jobDescription

The endpoint:

Receives the resume PDF
Extracts text
Sends the resume and job description to Gemini
Returns structured ATS analysis

Optimize Resume
POST /api/resume/optimize

Request body:

{
  "resumeText": "Resume content",
  "jobDescription": "Target job description"
}

Returns optimized resume sections:

{
  "summary": "...",
  "skills": "...",
  "experience": "...",
  "projects": "...",
  "education": "...",
  "certifications": "...",
  "achievements": "..."
}
Example Job Description
Junior Software Developer

We are looking for a Junior Software Developer with knowledge of
JavaScript, React, Node.js, REST APIs, SQL, and backend development.

Responsibilities:
- Develop web applications using React and Node.js.
- Build and integrate REST APIs.
- Work with SQL databases.
- Debug and improve applications.
- Collaborate with the development team.

Requirements:
- Good programming and problem-solving skills.
- Knowledge of data structures and algorithms.
- Understanding of AI/NLP is a plus.
Example Project Format

Projects can be entered using:

Project Name
Project Description
GitHub URL
Demo URL

Example:

AI Resume Analyzer
Spring Boot based AI resume analysis application.
https://github.com/yourname/resume-analyzer
https://your-demo.com
Example Certification Format
Certificate Name
Issuing Organization
Year
Certificate URL

Example:

Java Programming
NPTEL
2025
https://certificate-link.com

The generated resume displays the certificate name as the clickable link instead of displaying the raw URL.

Security

The Gemini API key should always be supplied through an environment variable.

Do not commit:

.env

or any file containing API credentials.

Recommended .gitignore entries:

.env
*.env
.idea/
target/
Current Capabilities

The completed application provides an end-to-end workflow:

Resume PDF
    ↓
Text Extraction
    ↓
AI ATS Analysis
    ↓
ATS Score
    ↓
Skill Matching
    ↓
Resume Improvement Suggestions
    ↓
Structured Resume Data
    ↓
Resume Builder
    ↓
AI Optimization
    ↓
ATS-Friendly Resume
    ↓
PDF / Print
Future Enhancements

Possible future improvements include:

Resume version history
Multiple job-description comparison
Resume scoring history
Cover letter generation
Job-specific resume versions
Keyword gap visualization
Advanced ATS formatting checks
Resume analytics dashboard
User authentication
Cloud deployment
Persistent database storage
Drag-and-drop resume sections
More resume templates
Disclaimer

The ATS score is an AI-generated evaluation and should be treated as an estimate rather than an official score from any particular applicant tracking system.

The optimizer is designed to improve presentation and relevance while preserving information from the original resume.

Author

Developed as an AI-powered Resume Analysis and Optimization project using Java, Spring Boot, PDFBox, and Google Gemini AI.


# Requirements

# Gemini API Configuration

# Installation

# Run the Application

# API Endpoints