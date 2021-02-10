package com.example.iituapp.transcription;

public class Transcription {
    private String code,course_name;
    private Long CR,ECTS,grade;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public Long getCR() {
        return CR;
    }

    public void setCR(Long CR) {
        this.CR = CR;
    }

    public Long getECTS() {
        return ECTS;
    }

    public void setECTS(Long ECTS) {
        this.ECTS = ECTS;
    }

    public Long getGrade() {
        return grade;
    }

    public void setGrade(Long grade) {
        this.grade = grade;
    }

    public Double getPoint() {
        return point;
    }

    public void setPoint(Double point) {
        this.point = point;
    }

    public String getLG() {
        return LG;
    }

    public void setLG(String LG) {
        this.LG = LG;
    }

    public String getTraditional() {
        return traditional;
    }

    public void setTraditional(String traditional) {
        this.traditional = traditional;
    }

    public Transcription(String code, String course_name, Long CR, Long ECTS, Long grade, Double point, String LG, String traditional) {
        this.code = code;
        this.course_name = course_name;
        this.CR = CR;
        this.ECTS = ECTS;
        this.grade = grade;
        this.point = point;
        this.LG = LG;
        this.traditional = traditional;
    }

    private Double point;
    private String LG,traditional;


    public Transcription(){}
}
