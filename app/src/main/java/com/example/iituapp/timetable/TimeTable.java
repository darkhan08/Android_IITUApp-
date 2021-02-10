package com.example.iituapp.timetable;

public class TimeTable {
    private String classes="";
    private String date = "";
    private String name_of_class = "";
    private String office = "";
    private String teacher = "";

    public TimeTable(String classes, String date, String name_of_class, String office, String teacher) {
        this.classes = classes;
        this.date = date;
        this.name_of_class = name_of_class;
        this.office = office;
        this.teacher = teacher;
    }

    public TimeTable() {
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName_of_class() {
        return name_of_class;
    }

    public void setName_of_class(String name_of_class) {
        this.name_of_class = name_of_class;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }
}
