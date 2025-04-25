package com.example.Hospital.service.impl;

import com.example.Hospital.dto.AppointmentDTO;
import com.example.Hospital.model.Appointment;
import com.example.Hospital.repository.AppointmentRepository;
import com.example.Hospital.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public List<AppointmentDTO> getAllAppointments() {
        List<Appointment> appointments = appointmentRepository.findAll();
        return appointments.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public AppointmentDTO getAppointmentById(int id) {
        Appointment appointment = appointmentRepository.findById(id).orElse(null);
        return appointment != null ? convertToDTO(appointment) : null;
    }

    @Override
    public List<AppointmentDTO> getAppointmentsByDoctorId(int doctorId) {
        List<Appointment> appointments = appointmentRepository.findByDoctor_DoctorID(doctorId);
        return appointments.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<AppointmentDTO> getAppointmentsByPatientId(int patientId) {
        List<Appointment> appointments = appointmentRepository.findByPatient_PatientID(patientId);
        return appointments.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private AppointmentDTO convertToDTO(Appointment appointment) {
        AppointmentDTO dto = new AppointmentDTO();
        dto.setAppointmentID(appointment.getAppointmentID());
        dto.setDate(appointment.getDate());
        dto.setReason(appointment.getReason());
        dto.setStatus(appointment.getStatus());
        dto.setPatient(appointment.getPatient().getName());  // Sửa tùy theo field thực tế
        dto.setDoctor(appointment.getDoctor().getName());    // Sửa tùy theo field thực tế
        return dto;
    }
}
