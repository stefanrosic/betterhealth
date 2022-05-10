package com.better.application.model;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class PatientModel {

    @NotNull
    Integer id;

    @NotBlank
    @JsonProperty("first_name")
    String firstName;

    @NotBlank
    @JsonProperty("last_name")
    String lastName;

    @Builder.Default
    List<String> diseases = new ArrayList<>();
}
