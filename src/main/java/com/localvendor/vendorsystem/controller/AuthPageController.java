// package com.localvendor.vendorsystem.controller;

// import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.GetMapping;

// @Controller
// public class AuthPageController {

//     // Login Page
//     @GetMapping("/")
//     public String homePage() {
//         return "login";
//     }

//     @GetMapping("/login")
//     public String loginPage() {
//         return "login";
//     }

//     // Register Page
//     @GetMapping("/register")
//     public String registerPage() {
//         return "register";
//     }

//     // Admin Dashboard
//     @GetMapping("/admin-dashboard")
//     public String adminDashboard() {
//         return "admin-dashboard";
//     }

//     // Customer Dashboard
//     @GetMapping("/customer-dashboard")
//     public String customerDashboard() {
//         return "customer-dashboard";
//     }

//     // Vendor Dashboard
//     @GetMapping("/vendor-dashboard")
//     public String vendorDashboard() {
//         return "vendor-dashboard";
//     }
// }

//  basic 


package com.localvendor.vendorsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthPageController {

    /*
    Open Login Page
    URL: http://localhost:8080/login
    */
    @GetMapping("/login")
    public String openLoginPage() {
        return "login";
    }

    /*
    Open Register Page
    URL: http://localhost:8080/register
    */
    @GetMapping("/register")
    public String openRegisterPage() {
        return "register";
    }
}