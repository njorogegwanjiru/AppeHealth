package com.example.eHealthProject.Models;

public class Medication {

    String patientid, name, from, to, times;

    public Medication(String patientid, String name, String from, String to, String times) {
        this.patientid = patientid;
        this.name = name;
        this.from = from;
        this.to = to;
        this.times = times;
    }

    public Medication() {
    }

    public String getPatientid() {
        return patientid;
    }

    public void setPatientid(String patientid) {
        this.patientid = patientid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    @Override
    public String toString() {
        return "Medication{" +
                ", patientid='" + patientid + '\'' +
                ", name='" + name + '\'' +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", times='" + times + '\'' +
                '}';
    }
}
