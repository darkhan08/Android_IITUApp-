package com.example.iituapp.systemcalendar;

public class SystemCalendar {
    private Long id;
    private String event,status,startTime,endTime;

    public SystemCalendar(Long id, String event, String status, String startTime, String endTime) {
        this.id = id;
        this.event = event;
        this.status = status;
        this.startTime = startTime;
        this.endTime = endTime;
    }
    public SystemCalendar(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
