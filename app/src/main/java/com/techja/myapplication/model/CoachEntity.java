package com.techja.myapplication.model;

public class CoachEntity {
    private String chucVu, coach, company, phoneNumber, state;

    public CoachEntity(String chucVu, String coach, String company, String phoneNumber, String state) {
        this.chucVu = chucVu;
        this.coach = coach;
        this.company = company;
        this.phoneNumber = phoneNumber;
        this.state = state;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

    public String getCoach() {
        return coach;
    }

    public void setCoach(String coach) {
        this.coach = coach;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
