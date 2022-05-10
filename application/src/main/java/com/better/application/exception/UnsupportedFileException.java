package com.better.application.exception;

public class UnsupportedFileException extends RuntimeException {

    public static final String MESSAGE = "Unsupported file. Select either xml or json file.";

    public UnsupportedFileException() {
        super(MESSAGE);
    }
}
