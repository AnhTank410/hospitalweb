package com.example.Hospital.service;

import com.example.Hospital.dto.DoctorDTO;

import java.util.List;

public interface DoctorSevice {
    DoctorDTO getDoctorbyId(int id);
    List<DoctorDTO>  getDoctorbyDepartment(int departmentId);
    DoctorDTO createDoctor(DoctorDTO doctorDTO);
    DoctorDTO updateDoctor(int id,DoctorDTO doctorDTO);
    boolean deterDoctor(int id);
}
