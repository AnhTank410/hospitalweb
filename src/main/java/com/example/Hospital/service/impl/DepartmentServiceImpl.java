package com.example.Hospital.service.impl;

import com.example.Hospital.dto.DepartmentDTO;
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
    public List<DepartmentDTO> getAllDepartments(){
        List<Department> departmentList= departmentRepository.findAll();
        return departmentList.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public DepartmentDTO createDepartment(DepartmentDTO departmentDTO) {
        Department department=new Department();
        department.setDepartmentID(departmentDTO.getDepartmentID());
        department.setName(departmentDTO.getName());

        Department saveDepartment= departmentRepository.save(department);
        return  convertToDTO(saveDepartment);
    }

    @Override
    public DepartmentDTO getDepartmentById(int id) {
        Department department=departmentRepository.findById(id).orElse(null);
        return department!=null ? convertToDTO(department): null;
    }

    @Override
    public DepartmentDTO updateDepartment(int id, DepartmentDTO departmentDTO) {
        Optional<Department> optionalDepartment=departmentRepository.findById(id);
        if(optionalDepartment.isPresent()){
            Department department=optionalDepartment.get();
            department.setName(departmentDTO.getName());

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


    private DepartmentDTO convertToDTO(Department department){
        DepartmentDTO departmentDTO=new DepartmentDTO();
        departmentDTO.setDepartmentID(department.getDepartmentID());
        departmentDTO.setName(department.getName());
        return departmentDTO;
    }
}
