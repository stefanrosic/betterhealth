package com.better.application.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Patient {

    @NotNull
    @Id
    Integer id;

    @NotBlank
    String firstName;

    @NotBlank
    String lastName;

    @ManyToOne
    Doctor doctor;

    @Builder.Default
    @ElementCollection
    List<String> diseases = new ArrayList<>();
}
