package com.example.Hospital.controller;

import com.example.Hospital.dto.DepartmentDTO;
import com.example.Hospital.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {
//
//    @Autowired
//    private DepartmentService departmentService;
//
//    @PostMapping
//    public ResponseEntity<DepartmentDTO> createDepartment(@RequestBody DepartmentDTO departmentDTO) {
//        DepartmentDTO createdDepartment = departmentService.createDepartment(departmentDTO);
//        return new ResponseEntity<>(createdDepartment, HttpStatus.CREATED);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<DepartmentDTO> getDepartment(@PathVariable int id) {
//        DepartmentDTO departmentDTO = departmentService.getDepartmentById(id);
//        return ResponseEntity.ok(departmentDTO);
//    }
//
//    @GetMapping
//    public ResponseEntity<List<DepartmentDTO>> getAllDepartments() {
//        List<DepartmentDTO> departments = departmentService.getAllDepartments();
//        return ResponseEntity.ok(departments);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<DepartmentDTO> updateDepartment(@PathVariable int id,
//                                                          @RequestBody DepartmentDTO departmentDTO) {
//        DepartmentDTO updatedDepartment = departmentService.updateDepartment(id, departmentDTO);
//        return ResponseEntity.ok(updatedDepartment);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteDepartment(@PathVariable int id) {
//        departmentService.deleteDepartment(id);
//        return ResponseEntity.noContent().build();
//    }
}