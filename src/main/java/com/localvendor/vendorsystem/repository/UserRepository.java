package com.localvendor.vendorsystem.repository;

import com.localvendor.vendorsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);
}





// 2nd method 

// package com.localvendor.vendorsystem.repository;

// import com.localvendor.vendorsystem.entity.User;
// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.stereotype.Repository;

// import java.util.Optional;

// @Repository
// public interface UserRepository extends JpaRepository<User, Integer> {

//     Optional<User> findByEmail(String email);

// }