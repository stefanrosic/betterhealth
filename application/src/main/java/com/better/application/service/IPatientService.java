package com.better.application.service;

import com.better.application.dto.response.DiseaseResponse;
import com.better.application.dto.response.PatientResponse;
import com.better.application.model.PatientModel;

import java.util.List;

public interface IPatientService {

    void saveAll(List<PatientModel> patientsModel, Integer doctorId);

    List<PatientResponse> getAll();

    List<PatientResponse> getAll(Integer doctorId);

    PatientResponse getOne(Integer id);

    DiseaseResponse getDiseases(Integer patientId);
}
