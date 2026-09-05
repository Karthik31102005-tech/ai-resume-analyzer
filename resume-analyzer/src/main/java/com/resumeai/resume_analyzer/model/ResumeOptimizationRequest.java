package com.resumeai.resume_analyzer.model;

public class ResumeOptimizationRequest {

    private String resumeText;
    private String jobDescription;

    public ResumeOptimizationRequest() {
    }

    public String getResumeText() {
        return resumeText;
    }

    public void setResumeText(String resumeText) {
        this.resumeText = resumeText;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }
}