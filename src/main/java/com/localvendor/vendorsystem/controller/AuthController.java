package com.localvendor.vendorsystem.controller;

import com.localvendor.vendorsystem.entity.User;
import com.localvendor.vendorsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /*
    =====================================
    REGISTER USER
    CUSTOMER + VENDOR
    =====================================
    */

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {

        // Check duplicate email
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return ResponseEntity.badRequest()
                    .body("Email already exists!");
        }

        // Encrypt password
        user.setPassword(
                passwordEncoder.encode(user.getPassword())
        );

        // Default active account
        user.setIsActive(true);

        // Save user
        userRepository.save(user);

        return ResponseEntity.ok(
                "Registration Successful as " + user.getRole()
        );
    }

    /*
    =====================================
    LOGIN FOR:
    ADMIN / VENDOR / CUSTOMER
    =====================================
    */

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody User loginRequest) {

        // Find user by email
        Optional<User> optionalUser =
                userRepository.findByEmail(loginRequest.getEmail());

        if (optionalUser.isEmpty()) {
            return ResponseEntity.badRequest()
                    .body("User not found!");
        }

        User user = optionalUser.get();

        // Check password
        boolean passwordMatch =
                passwordEncoder.matches(
                        loginRequest.getPassword(),
                        user.getPassword()
                );

        if (!passwordMatch) {
            return ResponseEntity.badRequest()
                    .body("Wrong Password!");
        }

        // Check blocked account
        if (user.getIsActive() == false) {
            return ResponseEntity.badRequest()
                    .body("Account Blocked by Admin!");
        }

        // Role-wise login

        if (user.getRole().equalsIgnoreCase("ADMIN")) {
            return ResponseEntity.ok(
                    "ADMIN Login Successful → Open Admin Dashboard"
            );
        }

        else if (user.getRole().equalsIgnoreCase("VENDOR")) {
            return ResponseEntity.ok(
                    "VENDOR Login Successful → Open Vendor Dashboard"
            );
        }

        else if (user.getRole().equalsIgnoreCase("CUSTOMER")) {
            return ResponseEntity.ok(
                    "CUSTOMER Login Successful → Open Customer Dashboard"
            );
        }

        else {
            return ResponseEntity.badRequest()
                    .body("Invalid Role Found!");
        }
    }
}
//  method 1 
// package com.localvendor.vendorsystem.controller;

// import com.localvendor.vendorsystem.entity.User;
// import com.localvendor.vendorsystem.repository.UserRepository;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.web.bind.annotation.*;

// import java.util.Optional;

// @RestController
// @RequestMapping("/api/auth")
// public class AuthController {

//     @Autowired
//     private UserRepository userRepository;

//     @Autowired
//     private PasswordEncoder passwordEncoder;

//     /*
//     =====================================
//     LOGIN FOR:
//     1. ADMIN
//     2. VENDOR
//     3. CUSTOMER (USER)
//     =====================================
//     */

//     @PostMapping("/login")
//     public ResponseEntity<String> loginUser(@RequestBody User loginRequest) {

//         // STEP 1: Find user by email
//         Optional<User> optionalUser =
//                 userRepository.findByEmail(loginRequest.getEmail());

//         // If email not found
//         if (optionalUser.isEmpty()) {
//             return ResponseEntity.badRequest()
//                     .body("User not found!");
//         }

//         // Get user data from database
//         User user = optionalUser.get();

//         // STEP 2: Check password
//         boolean passwordMatch =
//                 passwordEncoder.matches(
//                         loginRequest.getPassword(),
//                         user.getPassword()
//                 );

//         if (!passwordMatch) {
//             return ResponseEntity.badRequest()
//                     .body("Wrong Password!");
//         }

//         // STEP 3: Check if account is active
//         if (user.getIsActive() == false) {
//             return ResponseEntity.badRequest()
//                     .body("Account Blocked by Admin!");
//         }

//         // STEP 4: Separate login by role

//         // -------- ADMIN LOGIN --------
//         if (user.getRole().equalsIgnoreCase("ADMIN")) {

//             return ResponseEntity.ok(
//                     "ADMIN Login Successful → Open Admin Dashboard"
//             );
//         }

//         // -------- VENDOR LOGIN --------
//         else if (user.getRole().equalsIgnoreCase("VENDOR")) {

//             return ResponseEntity.ok(
//                     "VENDOR Login Successful → Open Vendor Dashboard"
//             );
//         }

//         // -------- CUSTOMER LOGIN --------
//         else if (user.getRole().equalsIgnoreCase("CUSTOMER")) {

//             return ResponseEntity.ok(
//                     "CUSTOMER Login Successful → Open Customer Dashboard"
//             );
//         }

//         // -------- INVALID ROLE --------
//         else {
//             return ResponseEntity.badRequest()
//                     .body("Invalid Role Found!");
//         }
//     }
// }




//  method 2nd ( html one )

// package com.localvendor.vendorsystem.controller;

// import com.localvendor.vendorsystem.entity.User;
// import com.localvendor.vendorsystem.repository.UserRepository;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.*;

// import java.util.Optional;

// @Controller
// @RequestMapping("/auth")
// public class AuthController {

//     @Autowired
//     private UserRepository userRepository;

//     @Autowired
//     private PasswordEncoder passwordEncoder;

//     /*
//     REGISTER USER
//     */
//     @PostMapping("/register")
//     public String registerUser(
//             @RequestParam String fullName,
//             @RequestParam String email,
//             @RequestParam String password,
//             @RequestParam String phoneNumber,
//             @RequestParam String role,
//             Model model
//     ) {

//         if (userRepository.findByEmail(email).isPresent()) {
//             model.addAttribute("error", "Email already exists!");
//             return "register";
//         }

//         User user = new User();
//         user.setFullName(fullName);
//         user.setEmail(email);
//         user.setPassword(passwordEncoder.encode(password));
//         user.setPhoneNumber(phoneNumber);
//         user.setRole(role);
//         user.setIsActive(true);

//         userRepository.save(user);

//         return "redirect:/login";
//     }

//     /*
//     LOGIN USER
//     */
//     @PostMapping("/login")
//     public String loginUser(
//             @RequestParam String email,
//             @RequestParam String password,
//             Model model
//     ) {

//         Optional<User> optionalUser =
//                 userRepository.findByEmail(email);

//         if (optionalUser.isEmpty()) {
//             model.addAttribute("error", "User not found!");
//             return "login";
//         }

//         User user = optionalUser.get();

//         if (!passwordEncoder.matches(password, user.getPassword())) {
//             model.addAttribute("error", "Wrong Password!");
//             return "login";
//         }

//         if (!user.getIsActive()) {
//             model.addAttribute("error", "Account Blocked!");
//             return "login";
//         }

//         if (user.getRole().equalsIgnoreCase("ADMIN")) {
//             model.addAttribute("name", user.getFullName());
//             return "admin-dashboard";
//         }

//         else if (user.getRole().equalsIgnoreCase("VENDOR")) {
//             model.addAttribute("name", user.getFullName());
//             return "vendor-dashboard";
//         }

//         else {
//             model.addAttribute("name", user.getFullName());
//             return "customer-dashboard";
//         }
//     }
// }