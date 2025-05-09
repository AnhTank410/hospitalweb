package com.example.Hospital.dto.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String usename;
    private String password;

    public String getUsename() {
        return usename;
    }

    public String getPassword() {
        return password;
    }
}
