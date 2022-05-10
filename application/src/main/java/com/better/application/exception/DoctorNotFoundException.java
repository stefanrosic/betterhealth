package com.better.application.exception;

public class DoctorNotFoundException extends RuntimeException  {

    public static final String MESSAGE = "Doctor with %s does not exist.";

    public DoctorNotFoundException(Integer id) {
        super(String.format(MESSAGE, id.toString()));
    }
}
