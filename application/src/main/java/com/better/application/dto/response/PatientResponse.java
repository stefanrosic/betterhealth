package com.better.application.dto.response;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class PatientResponse {

    Integer id;

    String firstName;

    String lastName;

    @Builder.Default
    List<String> diseases = List.of();
}
