package com.example.e_health.Model;

public class Patient {
    private String fname;
    private String mname;
    private String lname;
    private String email;
    private String phone;
    private String dob;
    private String sex;
    private String password;

    public Patient() {
    }

    public Patient(String fname, String mname, String lname, String email, String phone, String dob, String sex, String password) {
        this.fname = fname;
        this.mname = mname;
        this.lname = lname;
        this.email = email;
        this.phone = phone;
        this.dob = dob;
        this.sex = sex;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
