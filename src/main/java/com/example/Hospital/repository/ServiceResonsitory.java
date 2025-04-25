package com.example.Hospital.repository;

import com.example.Hospital.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceResonsitory extends JpaRepository<Service,Integer> {
}
