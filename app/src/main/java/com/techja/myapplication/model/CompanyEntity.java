package com.techja.myapplication.model;

public class CompanyEntity {
    private String codeDD, latitude, longitude, maxdistance;

    public CompanyEntity(String codeDD, String latitude, String longitude, String maxdistance) {
        this.codeDD = codeDD;
        this.latitude = latitude;
        this.longitude = longitude;
        this.maxdistance = maxdistance;
    }

    public String getCodeDD() {
        return codeDD;
    }

    public void setCodeDD(String codeDD) {
        this.codeDD = codeDD;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getMaxdistance() {
        return maxdistance;
    }

    public void setMaxdistance(String maxdistance) {
        this.maxdistance = maxdistance;
    }
}
