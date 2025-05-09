package com.example.Hospital.service.impl;

import com.example.Hospital.dto.request.LoginRequest;
import com.example.Hospital.dto.response.LoginResponse;
import com.example.Hospital.exception.AppException;
import com.example.Hospital.exception.ErrorCode;
import com.example.Hospital.repository.UserReponsitory;
import com.example.Hospital.service.LoginService;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private UserReponsitory userReponsitory;

    @NonFinal
    @Value("${jwt.signerKey}")
    protected String SIGNER_KEY;

    @Override
    public LoginResponse authen(LoginRequest request){
        var user=userReponsitory.findByUsename(request.getUsename()).orElseThrow(
                () -> new AppException(ErrorCode.USER_NOT_EXISTED));

        if(!user.getPassword().equals(request.getPassword())){
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }
        var token=generateToken(request.getUsename());
        return LoginResponse.builder()
                .token(token)
                .authen(true).build();

    }
    private String generateToken(String username){
        JWSHeader header=new JWSHeader(JWSAlgorithm.HS512);
        JWTClaimsSet jwtClaimsSet=new JWTClaimsSet.Builder()
                .subject(username)
                .issuer("test")
                .issueTime(new Date())
                .expirationTime(new Date(Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()))
                .build();

        Payload payload= new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject=new JWSObject(header,payload);

        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
    }

}
