package com.example.eHealthProject.Models;

public class Location
{
    private String id;
    private String locationName;

    public Location() {
    }

    public Location(String id, String locationName) {
        this.id = id;
        this.locationName = locationName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }
}
