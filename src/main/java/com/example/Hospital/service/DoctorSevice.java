package com.example.Hospital.service;

import com.example.Hospital.dto.request.DoctorRequest;

import java.util.List;

public interface DoctorSevice {
    List<DoctorRequest> getAllDoctors();
    DoctorRequest getDoctorbyId(int id);
    List<DoctorRequest>  getDoctorbyDepartment(int departmentId);
    DoctorRequest createDoctor(DoctorRequest doctorRequest);
    DoctorRequest updateDoctor(int id, DoctorRequest doctorRequest);
    boolean deterDoctor(int id);
}
