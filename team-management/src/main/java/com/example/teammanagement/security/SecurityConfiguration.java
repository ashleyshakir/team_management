package com.example.teammanagement.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration {
    /**
     * Creates and returns a BCryptPasswordEncoder instance for password hashing and verification.
     * @return Returns a BCryptPasswordEncoder instance for password hashing and verification.
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * Defines a Spring Bean for the JwtRequestFilter.
     * @return An instance of the JwtRequestFilter.
     */
    @Bean
    public JwtRequestFilter authJwtRequestFilter(){
        return new JwtRequestFilter();
    }

    /**
     * Configures various security settings for different URL patterns
     * @param http The HttpSecurity object used to configure the security settings
     * @return Returns the configured security filter chain
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // any url that starts with '/auth/users' or auth/users/login/ or auth/users/register/, allow access
        http.authorizeRequests().antMatchers("/auth/users", "/auth/users/login/", "/auth/users/register/").permitAll()
                // Allow access to H2 database console (only for development purposes)
                .antMatchers("/h2-console/**").permitAll()
                // Require authentication for any other request
                .anyRequest().authenticated()
                // Set the session creation policy to STATELESS (no HTTP sessions)
                .and().sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                // Disable CSRF protection
                .and().csrf().disable()
                // Disable frame options, necessary when using H2 database
                .headers().frameOptions().disable();
        // Add a custom JWT authentication filter before the standard UsernamePasswordAuthenticationFilter
        http.addFilterBefore(authJwtRequestFilter(), UsernamePasswordAuthenticationFilter.class);
        // Build and return the configured security filter chain
        return http.build();
    }
}
