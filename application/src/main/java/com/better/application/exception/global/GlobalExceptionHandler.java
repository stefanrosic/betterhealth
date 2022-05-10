package com.better.application.exception.global;

import com.better.application.dto.response.ErrorResponse;
import com.better.application.exception.DoctorNotFoundException;
import com.better.application.exception.PatientNotFoundException;
import com.better.application.exception.UnsupportedFileException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UnsupportedFileException.class)
    public ResponseEntity<ErrorResponse> processUnsupportedFileException(UnsupportedFileException e) {
        logException(e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.builder().message(e.getMessage()).build());
    }

    @ExceptionHandler(DoctorNotFoundException.class)
    public ResponseEntity<ErrorResponse> processDoctorNotFoundException(DoctorNotFoundException e) {
        logException(e);
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.builder().message(e.getMessage()).build());
    }

    @ExceptionHandler(PatientNotFoundException.class)
    public ResponseEntity<ErrorResponse> processPatientNotFoundException(PatientNotFoundException e) {
        logException(e);
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.builder().message(e.getMessage()).build());
    }

    private void logException(Exception e) {
        log.error(e.getClass().getSimpleName() + " thrown", e);
    }
}
