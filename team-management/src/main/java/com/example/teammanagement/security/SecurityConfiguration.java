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
        // urls added for testing purposes - remove later
        http.authorizeRequests().antMatchers("/auth/users", "/auth/users/login/", "/auth/users/register/","/api/teams/", "/api/teams/{teamId}/").permitAll()
                .antMatchers("/h2-console/**").permitAll()
                .anyRequest().authenticated()
                .and().sessionManagement()
                .and().csrf().disable()
                .headers().frameOptions().disable();
        return http.build();
    }
}
