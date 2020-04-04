package ru.itis.flamingo.ecofood.exception;

public class MediaServiceException extends RuntimeException {
    public MediaServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
