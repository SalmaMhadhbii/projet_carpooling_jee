package com.fst.projet_CarPooling_jee;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

//These annotations tell Spring to treat this class as a configuration class and enable Spring Security for the application.
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/showNewRideForm", "/loginn","/searchRides", "/AllUsers", "/reservations") // Allow public access
                        .permitAll()
                        .anyRequest().authenticated() // All other requests require authentication
                )
                .formLogin(form -> form
                        .loginPage("/loginn")  // Custom login page
                        .permitAll()          // Allow everyone to access the login page
                        .defaultSuccessUrl("/", true) // Redirect after successful login
                )
                .logout(config -> config
                        .logoutSuccessUrl("/") // Redirect after logout
                )
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}