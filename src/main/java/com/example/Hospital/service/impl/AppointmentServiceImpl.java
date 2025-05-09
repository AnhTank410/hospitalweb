package com.example.Hospital.service.impl;

import com.example.Hospital.dto.request.AppointmentRequest;
import com.example.Hospital.model.Appointment;
import com.example.Hospital.model.Doctor;
import com.example.Hospital.model.Patient;
import com.example.Hospital.repository.AppointmentRepository;
import com.example.Hospital.repository.DoctorRepository;
import com.example.Hospital.repository.PatientRepository;
import com.example.Hospital.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DoctorRepository doctorRepository;   // Thêm để lấy Doctor
    @Autowired
    private PatientRepository patientRepository; // Thêm để lấy Patient

    @Override
    public List<AppointmentRequest> getAllAppointments() {
        List<Appointment> appointments = appointmentRepository.findAll();
        return appointments.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public AppointmentRequest getAppointmentById(int id) {
        Appointment appointment = appointmentRepository.findById(id).orElse(null);
        return appointment != null ? convertToDTO(appointment) : null;
    }

    @Override
    public List<AppointmentRequest> getAppointmentsByDoctorId(int doctorId) {
        List<Appointment> appointments = appointmentRepository.findByDoctor_Id(doctorId);
        return appointments.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<AppointmentRequest> getAppointmentsByPatientId(int patientId) {
        List<Appointment> appointments = appointmentRepository.findByPatient_Id(patientId);
        return appointments.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public AppointmentRequest createAppointment(AppointmentRequest appointmentRequest) {
        Appointment appointment = new Appointment();
        appointment.setDate(appointmentRequest.getDate());
        appointment.setReason(appointmentRequest.getReason());
        appointment.setStatus(appointmentRequest.getStatus());

        // Gán Doctor và Patient từ ID/name
        Optional<Doctor> doctor = doctorRepository.findByName(appointmentRequest.getDoctor());
        Optional<Patient> patient = patientRepository.findByName(appointmentRequest.getPatient());

        doctor.ifPresent(appointment::setDoctor);
        patient.ifPresent(appointment::setPatient);

        Appointment savedAppointment = appointmentRepository.save(appointment);
        return convertToDTO(savedAppointment);
    }

    // ====== CRUD CẬP NHẬT ======

    @Override
    public AppointmentRequest updateAppointment(int id, AppointmentRequest appointmentRequest) {
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(id);
        if (optionalAppointment.isPresent()) {
            Appointment appointment = optionalAppointment.get();
            appointment.setDate(appointmentRequest.getDate());
            appointment.setReason(appointmentRequest.getReason());
            appointment.setStatus(appointmentRequest.getStatus());

            Optional<Doctor> doctor = doctorRepository.findByName(appointmentRequest.getDoctor());
            Optional<Patient> patient = patientRepository.findByName(appointmentRequest.getPatient());

            doctor.ifPresent(appointment::setDoctor);
            patient.ifPresent(appointment::setPatient);

            Appointment updatedAppointment = appointmentRepository.save(appointment);
            return convertToDTO(updatedAppointment);
        }
        return null;
    }

    // ====== CRUD XÓA ======

    @Override
    public boolean deleteAppointment(int id) {
        if (appointmentRepository.existsById(id)) {
            appointmentRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Chuyển Appointment -> AppointmentDTO

    private AppointmentRequest convertToDTO(Appointment appointment) {
        AppointmentRequest dto = new AppointmentRequest();
        dto.setAppointmentID(appointment.getAppointmentID());
        dto.setDate(appointment.getDate());
        dto.setReason(appointment.getReason());
        dto.setStatus(appointment.getStatus());
        dto.setPatient(appointment.getPatient() != null ? appointment.getPatient().getName() : null);
        dto.setDoctor(appointment.getDoctor() != null ? appointment.getDoctor().getName() : null);
        return dto;
    }
}
