package com.example.Hospital.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DepartmentDTO {
    private int departmentID;
    private String Name;

    public int getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(int departmentID) {
        this.departmentID = departmentID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }


}