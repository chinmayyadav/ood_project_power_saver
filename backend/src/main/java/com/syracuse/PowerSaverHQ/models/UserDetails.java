package com.syracuse.PowerSaverHQ.models;

public class UserDetails {
    private String FirstName;
    private String LastName;
    private String Email;
    private String Password;
    private int userID;
    private int addressID;

    public UserDetails() {

    }

    public UserDetails(String firstName, String lastName, String email, String password, int userID, int addressID) {
        this.LastName = lastName;
        this.Email = email;
        this.FirstName = firstName;
        this.Password = password;
        this.userID = userID;
        this.addressID = addressID;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        this.FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        this.LastName = lastName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        this.Password = password;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getAddressID() {
        return addressID;
    }

    public void setAddressID(int addressID) {
        this.addressID = addressID;
    }
}
