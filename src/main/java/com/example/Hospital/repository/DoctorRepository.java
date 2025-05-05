package com.example.Hospital.repository;

import com.example.Hospital.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor,Integer> {

    Optional<Doctor> findByName(String doctor);

    List<Doctor> findAllByDepartment_DepartmentID(int id);

}
