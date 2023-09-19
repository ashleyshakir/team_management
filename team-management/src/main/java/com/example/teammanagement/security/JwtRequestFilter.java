package com.example.teammanagement.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    Logger logger = Logger.getLogger(JwtRequestFilter.class.getName());

    private MyUserDetailsService myUserDetailsService;
    private JWTUtils jwtUtils;

    @Autowired
    public void setMyUserDetailsService(MyUserDetailsService myUserDetailsService) {
        this.myUserDetailsService = myUserDetailsService;
    }

    @Autowired
    public void setJwtUtils(JWTUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    /**
     * Parses a JSON Web Token (JWT) from the "Authorization" header of an HTTP request.
     * @param request The HttpServletRequest containing the "Authorization" header.
     * @return The extracted JWT string, or null if not found.
     */
    private String parseJwt(HttpServletRequest request){
        String headerAuth = request.getHeader("Authorization");
        // find out if the header contains the word "bearer"
        if(StringUtils.hasLength(headerAuth) && headerAuth.startsWith("Bearer")){
            return headerAuth.substring(7); // return everything after "Bearer "
        }
        return null;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

    }
}
