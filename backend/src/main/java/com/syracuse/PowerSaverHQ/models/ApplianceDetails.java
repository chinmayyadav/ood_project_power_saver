package com.syracuse.PowerSaverHQ.models;

public class ApplianceDetails {
    private int addID;
    private int applianceID;
    private int count;
    private int hours;
    private int days;
    private float rating;

    public ApplianceDetails(int addID, int applianceID, int count, int hours, int days, float rating){
        this.addID = addID;
        this.applianceID = applianceID;
        this.count = count;
        this.hours = hours;
        this.days = days;
        this.rating = rating;
    }

    public int getAddID() {
        return addID;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public void setAddID(int addID) {
        this.addID = addID;
    }

    public int getApplianceID() {
        return applianceID;
    }

    public void setApplianceID(int applianceID) {
        this.applianceID = applianceID;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }
}
