package com.example.eHealthProject.Models;

import java.util.HashMap;
import java.util.List;

public class Doctor {
    private String id, Names, Specialization,Email, Phone, Practice, Gender, Institution, YOB, Location, nationalID;
    private List<String> Days;
    private HashMap<String, Object> Slots, Times;

    public Doctor() {
    }

    public Doctor(String id, String names, String specialization, String email, String phone, String practice, String gender, String institution, String YOB, String location, String nationalID, List<String> days, HashMap<String, Object> slots, HashMap<String, Object> times) {
        this.id = id;
        Names = names;
        Specialization = specialization;
        Email = email;
        Phone = phone;
        Practice = practice;
        Gender = gender;
        Institution = institution;
        this.YOB = YOB;
        Location = location;
        this.nationalID = nationalID;
        Days = days;
        Slots = slots;
        Times = times;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNames() {
        return Names;
    }

    public void setNames(String names) {
        Names = names;
    }

    public String getSpecialization() {
        return Specialization;
    }

    public void setSpecialization(String specialization) {
        Specialization = specialization;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getPractice() {
        return Practice;
    }

    public void setPractice(String practice) {
        Practice = practice;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getInstitution() {
        return Institution;
    }

    public void setInstitution(String institution) {
        Institution = institution;
    }

    public String getYOB() {
        return YOB;
    }

    public void setYOB(String YOB) {
        this.YOB = YOB;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getNationalID() {
        return nationalID;
    }

    public void setNationalID(String nationalID) {
        this.nationalID = nationalID;
    }

    public List<String> getDays() {
        return Days;
    }

    public void setDays(List<String> days) {
        Days = days;
    }

    public HashMap<String, Object> getSlots() {
        return Slots;
    }

    public void setSlots(HashMap<String, Object> slots) {
        Slots = slots;
    }

    public HashMap<String, Object> getTimes() {
        return Times;
    }

    public void setTimes(HashMap<String, Object> times) {
        Times = times;
    }
}
