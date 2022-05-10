package com.better.application.entity;

import com.better.application.enums.DocumentSourceType;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "document_report")
public class Report {

    @NotNull
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Integer id;

    @NotNull
    Long executionTime; // In milliseconds

    @NotNull
    Integer doctorId;

    String error;

    DocumentSourceType documentSourceType;
}
