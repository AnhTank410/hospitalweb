package com.example.Hospital.controller;

import com.example.Hospital.dto.request.DepartmentRequest;
import com.example.Hospital.service.impl.DepartmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {
    @Autowired
    private DepartmentServiceImpl departmentService;

    @PostMapping
    public ResponseEntity<DepartmentRequest> createDepartment(@RequestBody DepartmentRequest departmentRequest) {
        DepartmentRequest createdDepartment = departmentService.createDepartment(departmentRequest);
        return new ResponseEntity<>(createdDepartment, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentRequest> getDepartment(@PathVariable int id) {
        DepartmentRequest departmentRequest = departmentService.getDepartmentById(id);
        return ResponseEntity.ok(departmentRequest);
    }

    @GetMapping
    public ResponseEntity<List<DepartmentRequest>> getAllDepartments() {
        List<DepartmentRequest> departments = departmentService.getAllDepartments();
        return ResponseEntity.ok(departments);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartmentRequest> updateDepartment(@PathVariable int id,
                                                              @RequestBody DepartmentRequest departmentRequest) {
        DepartmentRequest updatedDepartment = departmentService.updateDepartment(id, departmentRequest);
        return ResponseEntity.ok(updatedDepartment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable int id) {
        departmentService.deleteDepartment(id);
        return ResponseEntity.noContent().build();
    }
}