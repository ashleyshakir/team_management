package com.example.teammanagement.controller;

import com.example.teammanagement.model.User;
import com.example.teammanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
