package com.syracuse.PowerSaverHQ.models;

public class NotificationPreferance {
    private int addressID;
    private boolean emailNotification;
    private boolean phoneNotification;
    private boolean servicePhoneCalls;

    public void  NotificationPreferance(int addressID, boolean emailNotification, boolean phoneNotification, boolean servicePhoneCalls){
        this.addressID = addressID;
        this.emailNotification = emailNotification;
        this.phoneNotification = phoneNotification;
        this.servicePhoneCalls = servicePhoneCalls;
    }

    public int getAddressID() {
        return addressID;
    }

    public void setAddressID(int addressID) {
        this.addressID = addressID;
    }

    public boolean isEmailNotification() {
        return emailNotification;
    }

    public void setEmailNotification(boolean emailNotification) {
        this.emailNotification = emailNotification;
    }

    public boolean isPhoneNotification() {
        return phoneNotification;
    }

    public void setPhoneNotification(boolean phoneNotification) {
        this.phoneNotification = phoneNotification;
    }

    public boolean isServicePhoneCalls() {
        return servicePhoneCalls;
    }

    public void setServicePhoneCalls(boolean servicePhoneCalls) {
        this.servicePhoneCalls = servicePhoneCalls;
    }
}
