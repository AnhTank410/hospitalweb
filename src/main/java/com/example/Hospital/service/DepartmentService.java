package com.example.Hospital.service;

import com.example.Hospital.dto.request.DepartmentRequest;

import java.util.List;

public interface DepartmentService {
    List<DepartmentRequest> getAllDepartments();
    DepartmentRequest createDepartment(DepartmentRequest departmentRequest);

    DepartmentRequest getDepartmentById(int id);

    DepartmentRequest updateDepartment(int id, DepartmentRequest departmentRequest);

    boolean deleteDepartment(int id);
}
