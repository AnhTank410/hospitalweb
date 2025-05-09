package com.example.Hospital.controller;

import com.example.Hospital.dto.request.LoginRequest;
import com.example.Hospital.dto.response.ApiResponse;
import com.example.Hospital.dto.response.LoginResponse;
import com.example.Hospital.service.LoginService;
import com.example.Hospital.service.impl.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class LoginController {
    @Autowired
    private LoginServiceImpl loginService;

    @PostMapping("/log-in")
    ApiResponse<LoginResponse> authenticate(@RequestBody LoginRequest request){
        var result = loginService.authen(request);
        return ApiResponse.<LoginResponse>builder()
                .result(result)
                .build();
    }
}
