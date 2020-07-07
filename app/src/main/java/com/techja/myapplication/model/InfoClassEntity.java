package com.techja.myapplication.model;

public class InfoClassEntity {
    private String coach, className, description, duration, modul, supporter;

    public InfoClassEntity(String coach, String className, String description, String duration, String modul, String supporter) {
        this.coach = coach;
        this.className = className;
        this.description = description;
        this.duration = duration;
        this.modul = modul;
        this.supporter = supporter;
    }

    public String getCoach() {
        return coach;
    }

    public void setCoach(String coach) {
        this.coach = coach;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getModul() {
        return modul;
    }

    public void setModul(String modul) {
        this.modul = modul;
    }

    public String getSupporter() {
        return supporter;
    }

    public void setSupporter(String supporter) {
        this.supporter = supporter;
    }
}
