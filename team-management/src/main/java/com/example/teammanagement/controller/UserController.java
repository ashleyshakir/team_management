package com.example.teammanagement.controller;

import com.example.teammanagement.model.User;
import com.example.teammanagement.model.request.LoginRequest;
import com.example.teammanagement.model.response.LoginResponse;
import com.example.teammanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.Option;
import java.util.Optional;

@RestController
@RequestMapping(path = "/auth/users")
public class UserController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }

    /**
     * Create a new user by processing a POST request to the "/register/" endpoint.
     * @param userObject The user object to be created, provided in the request body.
     * @return The created user object.
     */
    @PostMapping(path = "/register/")
    public User createUser(@RequestBody User userObject){
        return userService.createUser(userObject);
    }

    /**
     * Handles a POST request to perform user login.
     * @param loginRequest The LoginRequest object containing the user's email address and password.
     * @return A ResponseEntity containing a LoginResponse with a JWT token if authentication is successful;
     *  *         otherwise, an unauthorized (401) response with a LoginResponse indicating authentication failure.
     */
    @PostMapping(path = "/login/")
    public ResponseEntity<LoginResponse> loginUser(@RequestBody LoginRequest loginRequest){
        // Attempt to authenticate the user and obtain a JWT token
        Optional<String> jwtToken = userService.loginUser(loginRequest);
        if(jwtToken.isPresent()){
            // If authentication is successful, return a 200 OK response with the JWT token
            return ResponseEntity.ok(new LoginResponse(jwtToken.get()));
        }
        // If authentication fails, return an unauthorized (401) response with an error message
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new LoginResponse("Authentication failed."));
    }

}
