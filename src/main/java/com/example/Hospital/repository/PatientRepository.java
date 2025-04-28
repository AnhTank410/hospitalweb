package com.example.Hospital.repository;

import com.example.Hospital.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient,Integer> {
    Optional<Patient> findByName(String patient);
}
