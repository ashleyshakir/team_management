package com.example.teammanagement.service;

import com.example.teammanagement.exception.InformationExistException;
import com.example.teammanagement.model.User;
import com.example.teammanagement.model.request.LoginRequest;
import com.example.teammanagement.repository.UserRepository;
import com.example.teammanagement.security.JWTUtils;
import com.example.teammanagement.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public UserService(UserRepository userRepository, @Lazy PasswordEncoder passwordEncoder,
                       JWTUtils jwtUtils, @Lazy AuthenticationManager authenticationManager){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
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

    /**
     * Retrieves a user by their email address from the user repository.
     * @param emailAddress The email address of the user to be retrieved.
     * @return Returns the user object associated with the email address.
     */
    public User findUserByEmailAddress(String emailAddress){
        return userRepository.findUserByEmailAddress(emailAddress);
    }

    /**
     * Authenticates a user based on the provided login credentials and
     * generates a JWT upon successful authentication.
     * @param loginRequest The LoginRequest object containing the user's email address and password.
     * @return An Optional containing the generated JWT if authentication is successful; otherwise, an empty Optional.
     */
    public Optional<String> loginUser(LoginRequest loginRequest){
        // Create an authentication token with the user's email address and password
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.getEmailAddress(),loginRequest.getPassword());
        try{
            // Authenticate the user using the AuthenticationManager
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            // Set the authenticated user's information in the security context
            SecurityContextHolder.getContext().setAuthentication(authentication);
            // Obtain user details from the authenticated principal
            MyUserDetails myUserDetails = (MyUserDetails) authentication.getPrincipal();
            // Generate a JWT token for the authenticated user
            return Optional.of(jwtUtils.generateJwtToken(myUserDetails));
        } catch(Exception e){
            // Return an empty Optional if authentication fails or an exception occurs
            return Optional.empty();
        }
    }


}
