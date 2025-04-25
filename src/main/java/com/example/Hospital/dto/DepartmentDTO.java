package com.example.Hospital.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DepartmentDTO {
    private int departmentID;
    private String Name;
    private List<Integer> doctorIds;
}