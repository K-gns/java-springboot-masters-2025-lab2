package ru.filatov.MySecondTestAppSpringBoot.exception;

public class UnsupportedCodeException extends RuntimeException {
    public UnsupportedCodeException(String message) {
        super(message);
    }
}