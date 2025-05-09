package com.example.Hospital.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponse {
    private boolean authen;
    private String token;
}
