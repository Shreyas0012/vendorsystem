package com.localvendor.vendorsystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}




//  method 2 nd 

// package com.localvendor.vendorsystem.config;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.web.SecurityFilterChain;

// @Configuration
// public class SecurityConfig {

//     /*
//     =====================================
//     PASSWORD ENCODER
//     =====================================
//     */

//     @Bean
//     public PasswordEncoder passwordEncoder() {
//         return new BCryptPasswordEncoder();
//     }

//     /*
//     =====================================
//     SECURITY FILTER CHAIN
//     Allow:
//     /login
//     /register
//     /auth/**
//     without authentication
//     =====================================
//     */

//     @Bean
//     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

//         http
//                 // Disable CSRF for beginner project
//                 .csrf(csrf -> csrf.disable())

//                 // Allow public routes
//                 .authorizeHttpRequests(auth -> auth
//                         .requestMatchers(
//                                 "/",
//                                 "/login",
//                                 "/register",
//                                 "/auth/**",
//                                 "/css/**",
//                                 "/js/**"
//                         ).permitAll()

//                         // Everything else requires login
//                         .anyRequest().authenticated()
//                 )

//                 // Disable default Spring login page
//                 .formLogin(form -> form.disable());

//         return http.build();
//     }
// }