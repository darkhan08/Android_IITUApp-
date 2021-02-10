package com.example.iituapp.message;

import android.net.Uri;

class Messages {
    private String name = "";
    private String title_message = "";
    private String date = "";
    private String message = "";

    public Messages(String name, String title_message, String date, String message) {
        this.name = name;
        this.title_message = title_message;
        this.date = date;
        this.message = message;
    }
    public Messages(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle_message() {
        return title_message;
    }

    public void setTitle_message(String title_message) {
        this.title_message = title_message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
