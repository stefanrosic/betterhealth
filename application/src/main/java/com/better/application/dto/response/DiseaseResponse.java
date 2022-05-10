package com.better.application.dto.response;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class DiseaseResponse {

    @Builder.Default
    List<String> diseases = List.of();
}
