package com.example.Hospital.service;

import com.example.Hospital.dto.request.LoginRequest;
import com.example.Hospital.dto.response.LoginResponse;

public interface LoginService {
    LoginResponse authen(LoginRequest loginRequest);
}
