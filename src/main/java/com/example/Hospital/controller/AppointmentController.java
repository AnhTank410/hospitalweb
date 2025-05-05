package com.example.Hospital.controller;

import com.example.Hospital.dto.AppointmentDTO;
import com.example.Hospital.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
@CrossOrigin(origins = "*")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    // Lấy tất cả các cuộc hẹn
    @GetMapping
    public ResponseEntity<?> getAllAppointments() {
        List<AppointmentDTO> appointments = appointmentService.getAllAppointments();
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

    // Lấy cuộc hẹn theo ID
    @GetMapping("/{id}")
    public ResponseEntity<AppointmentDTO> getAppointmentById(@PathVariable int id) {
        AppointmentDTO appointment = appointmentService.getAppointmentById(id);
        if (appointment != null) {
            return new ResponseEntity<>(appointment, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Lấy cuộc hẹn theo ID bác sĩ
    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<AppointmentDTO>> getByDoctor(@PathVariable int doctorId) {
        List<AppointmentDTO> appointments = appointmentService.getAppointmentsByDoctorId(doctorId);
        if (appointments.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

    // Lấy cuộc hẹn theo ID bệnh nhân
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<AppointmentDTO>> getByPatient(@PathVariable int patientId) {
        List<AppointmentDTO> appointments = appointmentService.getAppointmentsByPatientId(patientId);
        if (appointments.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

    // Tạo mới cuộc hẹn (Create)
    @PostMapping
    public ResponseEntity<AppointmentDTO> createAppointment(@RequestBody AppointmentDTO appointmentDTO) {
        AppointmentDTO newAppointment = appointmentService.createAppointment(appointmentDTO);
        //System.out.println(newAppointment);
        return new ResponseEntity<>(newAppointment, HttpStatus.CREATED);
    }

    // Cập nhật cuộc hẹn (Update)
    @PutMapping("/{id}")
    public ResponseEntity<AppointmentDTO> updateAppointment(@PathVariable int id, @RequestBody AppointmentDTO appointmentDTO) {
        AppointmentDTO updatedAppointment = appointmentService.updateAppointment(id, appointmentDTO);
        if (updatedAppointment != null) {
            return new ResponseEntity<>(updatedAppointment, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteAppointment(@PathVariable int id) {
        boolean isDeleted = appointmentService.deleteAppointment(id);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
