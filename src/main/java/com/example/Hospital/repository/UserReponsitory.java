package com.example.Hospital.repository;

import com.example.Hospital.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserReponsitory extends JpaRepository<User,Integer> {

}
