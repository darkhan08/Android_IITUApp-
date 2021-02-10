package com.example.iituapp.profile;

public class Student {
    private String info_data;
    private String student_data;

    public Student(String info_data, String student_data) {
        this.info_data = info_data;
        this.student_data = student_data;
    }

    public String getInfo_data() {
        return info_data;
    }

    public void setInfo_data(String info_data) {
        this.info_data = info_data;
    }

    public String getStudent_data() {
        return student_data;
    }

    public void setStudent_data(String student_data) {
        this.student_data = student_data;
    }
}
