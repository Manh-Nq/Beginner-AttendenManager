package com.techja.myapplication.model;

public class ItemDayEntity {
    private String day, time,state;

    public ItemDayEntity(String day, String time, String state) {
        this.day = day;
        this.time = time;
        this.state = state;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
