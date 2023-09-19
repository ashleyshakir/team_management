package com.example.teammanagement.security;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.logging.Level;
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
    /**
     * Get username from JWT
     * @param token The JWT from which the username is to be extracted.
     * @return The username contained within the JWT.
     */
    public String getUserNameFromJwtToken(String token){
        return Jwts.parserBuilder().setSigningKey(jwtSecret)
                .build().parseClaimsJws(token).getBody().getSubject();
    }

    /**
     * Validate a JWT
     * @param token The JWT token to be validated.
     * @return Returns true if the token is valid, otherwise returns false.
     */
    public boolean validateJwtToken(String token){
        try{
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch(SecurityException e){
            logger.log(Level.SEVERE, "Invalid JWT signature: {}", e.getMessage());
        }catch(MalformedJwtException e){
            logger.log(Level.SEVERE, "Invalid JWT Malformed JWT Exception: {}", e.getMessage());
        }catch(ExpiredJwtException e){
            logger.log(Level.SEVERE, "JWT token is expired: {}", e.getMessage());
        }catch(UnsupportedJwtException e){
            logger.log(Level.SEVERE, "JWT token is unsupported: {}",e.getMessage());
        }catch(IllegalArgumentException e){
            logger.log(Level.SEVERE,"JWT claims string is empty: {}",e.getMessage());
        }
        return false;
    }
}
