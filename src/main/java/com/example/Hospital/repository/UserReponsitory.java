package com.example.Hospital.repository;

import com.example.Hospital.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserReponsitory extends JpaRepository<User,Integer> {
    Optional<User> findByUsename(String usename);
}
