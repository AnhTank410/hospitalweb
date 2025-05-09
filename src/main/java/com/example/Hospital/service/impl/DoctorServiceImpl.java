package com.example.Hospital.service.impl;

import com.example.Hospital.dto.request.DoctorRequest;
import com.example.Hospital.model.Doctor;
import com.example.Hospital.model.Role;
import com.example.Hospital.repository.DepartmentRepository;
import com.example.Hospital.repository.DoctorRepository;
import com.example.Hospital.service.DoctorSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DoctorServiceImpl implements DoctorSevice {

    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public DoctorRequest getDoctorbyId(int id) {
        Optional<Doctor> optionalDoctor= doctorRepository.findById(id);
        if(optionalDoctor.isPresent()){
            return convertToDTO(optionalDoctor.get());
        }
        return null;
    }
    @Override
    public List<DoctorRequest> getAllDoctors() {
        List<Doctor> doctors = doctorRepository.findAll();
        return doctors.stream().map(doctor -> convertToDTO(doctor)).collect(Collectors.toList());
    }
    @Override
    public List<DoctorRequest> getDoctorbyDepartment(int departmentId) {
        List<Doctor> doctors=doctorRepository.findAllByDepartment_DepartmentID(departmentId);
        return doctors.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public DoctorRequest createDoctor(DoctorRequest doctorRequest) {
        Doctor doctor=new Doctor();
        doctor.setUsename(doctorRequest.getUsename());
        doctor.setPassword(doctorRequest.getPassword());
        doctor.setId(doctorRequest.getDoctorID());
        doctor.setName(doctorRequest.getName());
        doctor.setDepartment(departmentRepository.findByName(doctorRequest.getDepartment()));
        doctor.setGender(doctorRequest.getGender());
        doctor.setSpecialization(doctorRequest.getSpecialization());
        doctor.setPhone(doctorRequest.getPhone());
        doctor.setRole(Role.DOCTOR);

        Doctor saveDoctor=doctorRepository.save(doctor);
        return convertToDTO(saveDoctor);
    }

    @Override
    public DoctorRequest updateDoctor(int id, DoctorRequest doctorRequest) {
        Optional<Doctor> optionalDoctor=doctorRepository.findById(id);
        if(optionalDoctor.isPresent()){
            Doctor doctor=optionalDoctor.get();
            doctor.setId(doctorRequest.getDoctorID());
            doctor.setName(doctorRequest.getName());
            doctor.setDepartment(departmentRepository.findByName(doctorRequest.getDepartment()));
            doctor.setGender(doctorRequest.getGender());
            doctor.setSpecialization(doctorRequest.getSpecialization());
            doctor.setPhone(doctorRequest.getPhone());

            return convertToDTO(doctor);
        }
        return null;
    }

    @Override
    public boolean deterDoctor(int id) {
        if(doctorRepository.existsById(id)){
            doctorRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private DoctorRequest convertToDTO(Doctor doctor){
        DoctorRequest doctorRequest =new DoctorRequest();
        doctorRequest.setUsename(doctor.getUsename());
        doctorRequest.setPassword(doctor.getPassword());
        doctorRequest.setDoctorID(doctor.getId());
        doctorRequest.setName(doctor.getName());
        doctorRequest.setGender(doctor.getGender());
        doctorRequest.setSpecialization(doctor.getSpecialization());
        doctorRequest.setPhone(doctor.getPhone());
        doctorRequest.setDepartment(doctor.getDepartment()!=null ? doctor.getDepartment().getName(): null);
        doctorRequest.setRole(doctor.getRole());
        return doctorRequest;

    }
}
