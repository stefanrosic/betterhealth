package com.better.application.mock;

import com.better.application.dto.response.DoctorResponse;
import com.better.application.entity.Doctor;
import com.better.application.model.DoctorModel;

import java.util.List;

public final class DoctorGenerator {

    public static Doctor doctor() {
        return Doctor.builder().department("Department A").id(1).build();
    }

    public static List<Doctor> doctors() {
        return List.of(doctor());
    }

    public static DoctorModel doctorModel() {
        return DoctorModel.builder()
                .department("Department A")
                .id(1)
                .patients(List.of())
                .build();
    }

    public static DoctorResponse doctorResponse() {
        return DoctorResponse.builder()
                .department("Department A")
                .id(1)
                .build();
    }

    public static List<DoctorResponse> doctorsResponse() {
        return List.of(doctorResponse());
    }
}
