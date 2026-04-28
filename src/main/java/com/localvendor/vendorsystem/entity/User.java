package com.localvendor.vendorsystem.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "role", nullable = false)
    private String role;

    @Column(name = "is_active")
    private Boolean isActive = true;
}


// package com.localvendor.vendorsystem.entity;

// import jakarta.persistence.*;

// @Entity
// @Table(name = "users")
// public class User {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Integer id;

//     @Column(name = "full_name", nullable = false)
//     private String fullName;

//     @Column(name = "email", nullable = false, unique = true)
//     private String email;

//     @Column(name = "password", nullable = false)
//     private String password;

//     @Column(name = "phone_number")
//     private String phoneNumber;

//     @Column(name = "role", nullable = false)
//     private String role;

//     @Column(name = "is_active")
//     private Boolean isActive = true;

//     // ===== GETTERS AND SETTERS =====

//     public Integer getId() {
//         return id;
//     }

//     public void setId(Integer id) {
//         this.id = id;
//     }

//     public String getFullName() {
//         return fullName;
//     }

//     public void setFullName(String fullName) {
//         this.fullName = fullName;
//     }

//     public String getEmail() {
//         return email;
//     }

//     public void setEmail(String email) {
//         this.email = email;
//     }

//     public String getPassword() {
//         return password;
//     }

//     public void setPassword(String password) {
//         this.password = password;
//     }

//     public String getPhoneNumber() {
//         return phoneNumber;
//     }

//     public void setPhoneNumber(String phoneNumber) {
//         this.phoneNumber = phoneNumber;
//     }

//     public String getRole() {
//         return role;
//     }

//     public void setRole(String role) {
//         this.role = role;
//     }

//     public Boolean getIsActive() {
//         return isActive;
//     }

//     public void setIsActive(Boolean isActive) {
//         this.isActive = isActive;
//     }
// }