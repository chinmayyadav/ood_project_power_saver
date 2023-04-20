package com.syracuse.PowerSaverHQ.models;

public class ApplianceDetails {
    private int addID;
    private int applianceID;
    private int count;
    private float hours;
    private int days;

    public ApplianceDetails(int addID, int applianceID, int count, float hours, int days){
        this.addID = addID;
        this.applianceID = applianceID;
        this.count = count;
        this.hours = hours;
        this.days = days;
    }

    public int getAddID() {
        return addID;
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

    public float getHours() {
        return hours;
    }

    public void setHours(float hours) {
        this.hours = hours;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }
}
