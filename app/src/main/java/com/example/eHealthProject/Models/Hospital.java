package com.example.eHealthProject.Models;

public class Hospital {
    private String HospitalName, HospitalLocation, Image;

    public Hospital() {
    }

    public Hospital(String hospitalName, String hospitalLocation, String image) {
        HospitalName = hospitalName;
        HospitalLocation = hospitalLocation;
        Image = image;
    }

    public String getHospitalName() {
        return HospitalName;
    }

    public void setHospitalName(String hospitalName) {
        HospitalName = hospitalName;
    }

    public String getHospitalLocation() {
        return HospitalLocation;
    }

    public void setHospitalLocation(String hospitalLocation) {
        HospitalLocation = hospitalLocation;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
