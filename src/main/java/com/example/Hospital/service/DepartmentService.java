package com.example.Hospital.service;

import com.example.Hospital.dto.DepartmentDTO;

import java.util.List;

public interface DepartmentService {
    List<DepartmentDTO> getAllDepartments();
    DepartmentDTO createDepartment(DepartmentDTO departmentDTO);

    DepartmentDTO getDepartmentById(int id);

    DepartmentDTO updateDepartment(int id, DepartmentDTO departmentDTO);

    boolean deleteDepartment(int id);
}
