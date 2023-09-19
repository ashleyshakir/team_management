package com.example.teammanagement.service;

import com.example.teammanagement.exception.InformationExistException;
import com.example.teammanagement.model.User;
import com.example.teammanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Registers a new user.
     * @param userObject The user object to be registered.
     * @return Returns the registered user object.
     */
    public User createUser(User userObject){
        if(!userRepository.existsByEmailAddress(userObject.getEmailAddress())){
            userObject.setPassword(passwordEncoder.encode(userObject.getPassword()));
            return userRepository.save(userObject);
        } else{
            throw new InformationExistException("User with email address " + userObject.getEmailAddress() + " already exists.");
        }
    }

    public User findUserByEmailAddress(String emailAddress){
        return userRepository.findUserByEmailAddress(emailAddress);
    }


}
