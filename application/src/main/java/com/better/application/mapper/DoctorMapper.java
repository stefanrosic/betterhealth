package com.better.application.mapper;

import com.better.application.dto.response.DoctorResponse;
import com.better.application.dto.response.PatientResponse;
import com.better.application.entity.Doctor;
import com.better.application.entity.Patient;
import com.better.application.model.DoctorModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DoctorMapper {

    Doctor toDoctor(DoctorModel doctorModel);

    List<DoctorResponse> toDoctorsResponse(List<Doctor> patients);

    DoctorResponse toDoctorResponse(Doctor doctor);
}
