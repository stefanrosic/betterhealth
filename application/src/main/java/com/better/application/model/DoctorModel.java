package com.better.application.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DoctorModel {

    @NotNull
    Integer id;

    @NotBlank
    String department;

    @Builder.Default
    List<PatientModel> patients = new ArrayList<>();
}
