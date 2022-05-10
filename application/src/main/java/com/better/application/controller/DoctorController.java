package com.better.application.controller;

import com.better.application.dto.response.DoctorResponse;
import com.better.application.dto.response.PatientResponse;
import com.better.application.service.IDoctorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    private final IDoctorService doctorService;

    public DoctorController(IDoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping
    public List<DoctorResponse> getAllDoctors() {
        return doctorService.getAll();
    }

    @GetMapping("/{doctorId}")
    public DoctorResponse getDoctor(@PathVariable Integer doctorId) {
        return doctorService.getOne(doctorId);
    }

    @GetMapping("/{doctorId}/patients")
    public List<PatientResponse> getPatientsForDoctor(@PathVariable Integer doctorId) {
        return doctorService.getPatients(doctorId);
    }
}
