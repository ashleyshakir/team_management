package com.example.teammanagement.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.logging.Logger;

@Service
public class JWTUtils {
    Logger logger = Logger.getLogger(JWTUtils.class.getName());

    /**
     * The secret key used for signing JWT tokens.
     * This value is populated from application properties via the @Value annotation.
     */
    @Value("${jwt-secret}")
    private String jwtSecret;

    /**
     * The expiration time for JWT tokens, specified in milliseconds.
     * This value is populated from application properties via the @Value annotation.
     */
    @Value("${jwt-expiration-ms}")
    private int jwtExpirationMs;

    /**
     * Generate a JWT from username, issue date, expiration date, secret
     * @param myUserDetails The user details for which the JWT is generated.
     * @return A JWT string representing the users authentication and authorization
     */
    public String generateJwtToken(MyUserDetails myUserDetails){
        return Jwts.builder()
                .setSubject((myUserDetails.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
    }
}
