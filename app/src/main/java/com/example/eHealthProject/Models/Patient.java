package com.example.eHealthProject.Models;

public class Patient {
    private String id;
    private String fname;
    private String mname;
    private String lname;
    private String email;
    private String phone;
    private String dob;
    private String sex;
    private String userType = "0";
    private String pass;

    public Patient() {
    }

    public Patient(String id, String fname, String mname, String lname, String email, String phone, String dob, String sex, String userType, String pass) {
        this.id = id;
        this.fname = fname;
        this.mname = mname;
        this.lname = lname;
        this.email = email;
        this.phone = phone;
        this.dob = dob;
        this.sex = sex;
        this.userType = userType;
        this.pass = pass;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

}
