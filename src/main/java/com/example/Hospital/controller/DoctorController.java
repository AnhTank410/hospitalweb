package com.example.Hospital.controller;

import com.example.Hospital.dto.request.DoctorRequest;
import com.example.Hospital.dto.response.ApiResponse;
import com.example.Hospital.service.impl.DoctorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {
    @Autowired
    private DoctorServiceImpl doctorService;

    @GetMapping
    public ApiResponse<List<DoctorRequest>> getAllDoctors() {
        return ApiResponse.<List<DoctorRequest>>builder()
                .result(doctorService.getAllDoctors()).build();
    }

    @GetMapping("/{id}")
    public ApiResponse<DoctorRequest> getDoctorById(@PathVariable int id){
        return ApiResponse
                .<DoctorRequest>builder()
                .result(doctorService.getDoctorbyId(id))
                .build();
    }

    @GetMapping("/department/{id}")
    public ResponseEntity<List<DoctorRequest>> getDoctorByDepartment(@PathVariable int id){
        List<DoctorRequest> doctorRequestList =doctorService.getDoctorbyDepartment(id);
        if(doctorRequestList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(doctorRequestList,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DoctorRequest> createDoctor(@RequestBody DoctorRequest doctorRequest){
        DoctorRequest newdoctor= doctorService.createDoctor(doctorRequest);
        return new ResponseEntity<>(newdoctor,HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<DoctorRequest> updateDoctor(@PathVariable int id, @RequestBody DoctorRequest doctorRequest){
        DoctorRequest updateDoctor=doctorService.updateDoctor(id, doctorRequest);
        if(updateDoctor!=null){
            return new ResponseEntity<>(updateDoctor,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<DoctorRequest> delDoctor(@PathVariable int id){
        boolean delDoctor=doctorService.deterDoctor(id);
        if(delDoctor){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
