package com.better.application.exception;

public class PatientNotFoundException extends RuntimeException {

    public static final String MESSAGE = "Patient with %s does not exist.";

    public PatientNotFoundException(Integer id) {
        super(String.format(MESSAGE, id.toString()));
    }
}
