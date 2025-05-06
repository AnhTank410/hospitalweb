package com.example.Hospital.service.impl;

import com.example.Hospital.dto.DoctorDTO;
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
    public DoctorDTO getDoctorbyId(int id) {
        Optional<Doctor> optionalDoctor= doctorRepository.findById(id);
        if(optionalDoctor.isPresent()){
            return convertToDTO(optionalDoctor.get());
        }
        return null;
    }

    @Override
    public List<DoctorDTO> getDoctorbyDepartment(int departmentId) {
        List<Doctor> doctors=doctorRepository.findAllByDepartment_DepartmentID(departmentId);
        return doctors.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public DoctorDTO createDoctor(DoctorDTO doctorDTO) {
        Doctor doctor=new Doctor();
        doctor.setUsename(doctorDTO.getUsename());
        doctor.setPassword(doctorDTO.getPassword());
        doctor.setId(doctorDTO.getDoctorID());
        doctor.setName(doctorDTO.getName());
        doctor.setDepartment(departmentRepository.findByName(doctorDTO.getDepartment()));
        doctor.setGender(doctorDTO.getGender());
        doctor.setSpecialization(doctorDTO.getSpecialization());
        doctor.setPhone(doctorDTO.getPhone());
        doctor.setRole(Role.DOCTOR);

        Doctor saveDoctor=doctorRepository.save(doctor);
        return convertToDTO(saveDoctor);
    }

    @Override
    public DoctorDTO updateDoctor(int id, DoctorDTO doctorDTO) {
        Optional<Doctor> optionalDoctor=doctorRepository.findById(id);
        if(optionalDoctor.isPresent()){
            Doctor doctor=optionalDoctor.get();
            doctor.setId(doctorDTO.getDoctorID());
            doctor.setName(doctorDTO.getName());
            doctor.setDepartment(departmentRepository.findByName(doctorDTO.getDepartment()));
            doctor.setGender(doctorDTO.getGender());
            doctor.setSpecialization(doctorDTO.getSpecialization());
            doctor.setPhone(doctorDTO.getPhone());

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

    private DoctorDTO convertToDTO(Doctor doctor){
        DoctorDTO doctorDTO=new DoctorDTO();
        doctorDTO.setUsename(doctor.getUsename());
        doctorDTO.setPassword(doctor.getPassword());
        doctorDTO.setDoctorID(doctor.getId());
        doctorDTO.setName(doctor.getName());
        doctorDTO.setGender(doctor.getGender());
        doctorDTO.setSpecialization(doctor.getSpecialization());
        doctorDTO.setPhone(doctor.getPhone());
        doctorDTO.setDepartment(doctor.getDepartment()!=null ? doctor.getDepartment().getName(): null);
        doctorDTO.setRole(doctor.getRole());
        return doctorDTO;

    }
}
