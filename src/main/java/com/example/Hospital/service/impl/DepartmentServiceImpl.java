package com.example.Hospital.service.impl;

import com.example.Hospital.dto.request.DepartmentRequest;
import com.example.Hospital.model.Department;
import com.example.Hospital.repository.DepartmentRepository;
import com.example.Hospital.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public List<DepartmentRequest> getAllDepartments(){
        List<Department> departmentList= departmentRepository.findAll();
        return departmentList.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public DepartmentRequest createDepartment(DepartmentRequest departmentRequest) {
        Department department=new Department();
        department.setDepartmentID(departmentRequest.getDepartmentID());
        department.setName(departmentRequest.getName());

        Department saveDepartment= departmentRepository.save(department);
        return  convertToDTO(saveDepartment);
    }

    @Override
    public DepartmentRequest getDepartmentById(int id) {
        Department department=departmentRepository.findById(id).orElse(null);
        return department!=null ? convertToDTO(department): null;
    }

    @Override
    public DepartmentRequest updateDepartment(int id, DepartmentRequest departmentRequest) {
        Optional<Department> optionalDepartment=departmentRepository.findById(id);
        if(optionalDepartment.isPresent()){
            Department department=optionalDepartment.get();
            department.setName(departmentRequest.getName());

            Department saveDepartment=departmentRepository.save(department);
            return convertToDTO(department);
        }
        return null;
    }

    @Override
    public boolean deleteDepartment(int id) {
        if(departmentRepository.existsById(id)){
            departmentRepository.deleteById(id);
            return true;
        }
        return false;
    }


    private DepartmentRequest convertToDTO(Department department){
        DepartmentRequest departmentRequest =new DepartmentRequest();
        departmentRequest.setDepartmentID(department.getDepartmentID());
        departmentRequest.setName(department.getName());
        return departmentRequest;
    }
}
