package com.better.application.mock;

import com.better.application.dto.response.PatientResponse;
import com.better.application.entity.Patient;
import com.better.application.model.PatientModel;

import java.util.List;
import java.util.Set;

public final class PatientGenerator {

    public static PatientResponse patientResponse() {
        return PatientResponse.builder()
                .id(1)
                .firstName("John")
                .lastName("Larson")
                .diseases(List.of("disease_A", "disease_B"))
                .build();
    }

    public static List<PatientResponse> patientsResponse() {
        return List.of(patientResponse());
    }

    public static PatientModel patientModel() {
        return PatientModel.builder()
                .id(1)
                .firstName("John")
                .lastName("Larson")
                .diseases(List.of("disease_A", "disease_B"))
                .build();
    }

    public static List<PatientModel> patientsModel() {
        return List.of(patientModel());
    }

    public static Patient patient() {
        return Patient.builder()
                .id(1)
                .firstName("John")
                .lastName("Larson")
                .diseases(List.of("disease_A", "disease_B"))
                .doctor(DoctorGenerator.doctor())
                .build();
    }

    public static Set<Patient> patients() {
        return Set.of(patient());
    }
}
