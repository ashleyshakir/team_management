package com.example.teammanagement.service;

import com.example.teammanagement.model.User;
import com.example.teammanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User createUser(User userObject){
        return userObject;
    }
}
