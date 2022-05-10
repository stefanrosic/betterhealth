package com.better.application.controller;

import com.better.application.dto.response.DiseaseResponse;
import com.better.application.dto.response.PatientResponse;
import com.better.application.service.IPatientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final IPatientService patientService;

    public PatientController(IPatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public List<PatientResponse> getAllPatients() {
        return patientService.getAll();
    }

    @GetMapping("/{patientId}")
    public PatientResponse getPatient(@PathVariable Integer patientId) {
        return patientService.getOne(patientId);
    }

    @GetMapping("/{patientId}/diseases")
    public DiseaseResponse getDiseasesForPatient(@PathVariable Integer patientId) {
        return patientService.getDiseases(patientId);
    }
}
