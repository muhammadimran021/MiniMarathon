package com.example.muhammadimran.minimarathon.Models;

/**
 * Created by muhammad imran on 3/7/2017.
 */

public class SignUpModel {
    private String firsName;
    private String lastName;
    private String email;
    private String password;

    public SignUpModel() {
    }

    public SignUpModel(String firsName, String lastName, String email, String password) {
        this.firsName = firsName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public String getFirsName() {
        return firsName;
    }

    public void setFirsName(String firsName) {
        this.firsName = firsName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
