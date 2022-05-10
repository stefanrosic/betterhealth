package com.better.application.mapper;

import com.better.application.dto.response.PatientResponse;
import com.better.application.entity.Doctor;
import com.better.application.entity.Patient;
import com.better.application.model.PatientModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PatientMapper {

    @Mapping(target = "doctor", source = "doctor")
    @Mapping(target = "id", source = "patientModel.id")
    Patient toPatient(PatientModel patientModel, Doctor doctor);

    List<PatientResponse> toPatientsResponse(List<Patient> patients);

    PatientResponse toPatientResponse(Patient patient);
}
