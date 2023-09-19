package com.example.teammanagement.model.request;

/**
 * Represents a request object for user login.
 * This class is used to encapsulate the user's email address and password when submitting a login request.
 */
public class LoginRequest {
    private String emailAddress;
    private String password;

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPassword() {
        return password;
    }
}
