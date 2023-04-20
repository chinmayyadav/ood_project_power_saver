package com.syracuse.PowerSaverHQ.models;

public class UserDetails {
    private String FirstName;
    private String LastName;
    private String Email;
    private String Password;

    public UserDetails() {

    }

    public UserDetails(String firstName, String lastName, String email, String password) {
        this.LastName = lastName;
        this.Email = email;
        this.FirstName = firstName;
        this.Password = password;
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
}
