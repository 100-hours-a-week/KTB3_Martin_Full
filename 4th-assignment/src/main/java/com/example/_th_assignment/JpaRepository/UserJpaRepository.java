package com.example._th_assignment.JpaRepository;

import com.example._th_assignment.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmailAndIsdeletedFalse(String Email);
    Boolean existsByEmail(String Email);
}
