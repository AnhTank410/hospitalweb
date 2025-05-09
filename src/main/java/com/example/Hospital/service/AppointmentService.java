package com.example.Hospital.service;

import com.example.Hospital.dto.request.AppointmentRequest;
import java.util.List;

public interface AppointmentService {
    List<AppointmentRequest> getAllAppointments();
    AppointmentRequest getAppointmentById(int id);
    List<AppointmentRequest> getAppointmentsByDoctorId(int doctorId);
    List<AppointmentRequest> getAppointmentsByPatientId(int patientId);

    AppointmentRequest createAppointment(AppointmentRequest appointmentRequest);

    AppointmentRequest updateAppointment(int id, AppointmentRequest appointmentRequest);

    boolean deleteAppointment(int id);
}
