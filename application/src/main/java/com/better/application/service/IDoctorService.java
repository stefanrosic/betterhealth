package com.better.application.service;

import com.better.application.dto.response.DoctorResponse;
import com.better.application.dto.response.PatientResponse;
import com.better.application.model.DoctorModel;

import java.util.List;

public interface IDoctorService{

    void save(DoctorModel doctorModel);

    List<PatientResponse> getPatients(Integer doctorId);

    List<DoctorResponse> getAll();

    DoctorResponse getOne(Integer id);
}
