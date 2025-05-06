package com.example.Hospital.controller;

import com.example.Hospital.dto.DoctorDTO;
import com.example.Hospital.repository.DoctorRepository;
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

    @GetMapping("/{id}")
    public ResponseEntity<DoctorDTO> getDoctorById(@PathVariable int id){
        DoctorDTO doctorDTO= doctorService.getDoctorbyId(id);
        if(doctorDTO!=null){
            return new ResponseEntity<>(doctorDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/department/{id}")
    public ResponseEntity<List<DoctorDTO>> getDoctorByDepartment(@PathVariable int id){
        List<DoctorDTO> doctorDTOList=doctorService.getDoctorbyDepartment(id);
        if(doctorDTOList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(doctorDTOList,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DoctorDTO> createDoctor(@RequestBody DoctorDTO doctorDTO){
        DoctorDTO newdoctor= doctorService.createDoctor(doctorDTO);
        return new ResponseEntity<>(newdoctor,HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<DoctorDTO> updateDoctor(@PathVariable int id,@RequestBody DoctorDTO doctorDTO){
        DoctorDTO updateDoctor=doctorService.updateDoctor(id,doctorDTO);
        if(updateDoctor!=null){
            return new ResponseEntity<>(updateDoctor,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<DoctorDTO> delDoctor(@PathVariable int id){
        boolean delDoctor=doctorService.deterDoctor(id);
        if(delDoctor){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
