package com.example._th_assignment.CustomException;

public abstract class DtoNotFoundException extends RuntimeException {
    public DtoNotFoundException(String message) {
        super(message);
    }
}
