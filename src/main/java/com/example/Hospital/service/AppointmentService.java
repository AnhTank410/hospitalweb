package com.example.Hospital.service;

import com.example.Hospital.dto.AppointmentDTO;
import java.util.List;

public interface AppointmentService {
    List<AppointmentDTO> getAllAppointments();
    AppointmentDTO getAppointmentById(int id);
    List<AppointmentDTO> getAppointmentsByDoctorId(int doctorId);
    List<AppointmentDTO> getAppointmentsByPatientId(int patientId);

    AppointmentDTO createAppointment(AppointmentDTO appointmentDTO);

    AppointmentDTO updateAppointment(int id, AppointmentDTO appointmentDTO);

    boolean deleteAppointment(int id);
}
