package com.example.teammanagement.model.response;

/**
 * Represents a response object containing a JSON Web Token (JWT) for user authentication.
 * This class is used to encapsulate the JWT token that is returned to the client after a successful login.
 */
public class LoginResponse {
    private String jwt;

    public LoginResponse(String jwt){
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt){
        this.jwt = jwt;
    }
}
