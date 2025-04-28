package com.example.Hospital.repository;

import com.example.Hospital.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment,Integer> {
    List<Appointment> findByPatient_PatientID(int patientId);
    List<Appointment> findByDoctor_DoctorID(int doctorId);
    //List<Appointment> findbystatus(String status);
}
