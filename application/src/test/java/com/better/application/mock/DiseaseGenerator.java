package com.better.application.mock;

import com.better.application.dto.response.DiseaseResponse;

import java.util.List;

public final class DiseaseGenerator {

    public static DiseaseResponse diseaseResponse() {
        return DiseaseResponse.builder().diseases(List.of("disease_A", "disease_B")).build();
    }

}
