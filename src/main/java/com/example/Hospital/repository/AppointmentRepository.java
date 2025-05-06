package com.example.Hospital.repository;

import com.example.Hospital.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment,Integer> {
    List<Appointment> findByPatient_Id(int id);
    List<Appointment> findByDoctor_Id(int id);
    //List<Appointment> findbystatus(String status);
}
