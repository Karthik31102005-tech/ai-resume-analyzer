package com.resumeai.resume_analyzer.model;

import java.util.List;

public class AnalysisResponse {

    private int atsScore;

    private List<String> matchedSkills;
    private List<String> missingSkills;

    private List<String> strengths;
    private List<String> weaknesses;
    private List<String> suggestions;

    private String overallAssessment;

    // =========================
    // RESUME INFORMATION
    // =========================

    private String name;
    private String email;
    private String phone;
    private String location;
    private String linkedin;
    private String github;
    private String portfolio;

    private String summary;

    private List<String> skills;

    private String experience;

    private String projects;

    private String education;

    private String certifications;

    private String achievements;


    // =========================
    // ATS SCORE
    // =========================

    public int getAtsScore() {
        return atsScore;
    }

    public void setAtsScore(int atsScore) {
        this.atsScore = atsScore;
    }


    // =========================
    // MATCHED SKILLS
    // =========================

    public List<String> getMatchedSkills() {
        return matchedSkills;
    }

    public void setMatchedSkills(List<String> matchedSkills) {
        this.matchedSkills = matchedSkills;
    }


    // =========================
    // MISSING SKILLS
    // =========================

    public List<String> getMissingSkills() {
        return missingSkills;
    }

    public void setMissingSkills(List<String> missingSkills) {
        this.missingSkills = missingSkills;
    }


    // =========================
    // STRENGTHS
    // =========================

    public List<String> getStrengths() {
        return strengths;
    }

    public void setStrengths(List<String> strengths) {
        this.strengths = strengths;
    }


    // =========================
    // WEAKNESSES
    // =========================

    public List<String> getWeaknesses() {
        return weaknesses;
    }

    public void setWeaknesses(List<String> weaknesses) {
        this.weaknesses = weaknesses;
    }


    // =========================
    // SUGGESTIONS
    // =========================

    public List<String> getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(List<String> suggestions) {
        this.suggestions = suggestions;
    }


    // =========================
    // OVERALL ASSESSMENT
    // =========================

    public String getOverallAssessment() {
        return overallAssessment;
    }

    public void setOverallAssessment(String overallAssessment) {
        this.overallAssessment = overallAssessment;
    }


    // =========================
    // NAME
    // =========================

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    // =========================
    // EMAIL
    // =========================

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    // =========================
    // PHONE
    // =========================

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    // =========================
    // LOCATION
    // =========================

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    // =========================
    // LINKEDIN
    // =========================

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }


    // =========================
    // GITHUB
    // =========================

    public String getGithub() {
        return github;
    }

    public void setGithub(String github) {
        this.github = github;
    }
    public String getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(String portfolio) {
        this.portfolio = portfolio;
    }


    // =========================
    // SUMMARY
    // =========================

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }


    // =========================
    // SKILLS
    // =========================

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }


    // =========================
    // EXPERIENCE
    // =========================

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }


    // =========================
    // PROJECTS
    // =========================

    public String getProjects() {
        return projects;
    }

    public void setProjects(String projects) {
        this.projects = projects;
    }


    // =========================
    // EDUCATION
    // =========================

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }


    // =========================
    // CERTIFICATIONS
    // =========================

    public String getCertifications() {
        return certifications;
    }

    public void setCertifications(String certifications) {
        this.certifications = certifications;
    }


    // =========================
    // ACHIEVEMENTS
    // =========================

    public String getAchievements() {
        return achievements;
    }

    public void setAchievements(String achievements) {
        this.achievements = achievements;
    }

}