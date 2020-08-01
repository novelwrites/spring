package com.golden.project.model.request;

import javax.persistence.Column;

public class UserRequest {        //When an employee has feedback for the boss but doesn't go himself - he requests the middleman relays the information
    private String first_name;
    private String last_name;
    private String email;
    private String password; //only for inputting password data, not for returning because would violate security


public UserRequest() { //Spring uses empty constructor when initializes and the populated constructor when it gets a request
}

    public UserRequest(String first_name, String last_name, String email, String password) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
    }

    public String getFirst_name() { //Spring is using these even if intelliJ doesn't know it
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
