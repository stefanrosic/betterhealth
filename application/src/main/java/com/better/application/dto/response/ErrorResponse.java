package com.better.application.dto.response;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ErrorResponse {

    String message;
}
