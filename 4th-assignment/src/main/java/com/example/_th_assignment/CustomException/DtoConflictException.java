package com.example._th_assignment.CustomException;

public abstract class DtoConflictException extends RuntimeException {
    public DtoConflictException(String message) {
        super(message);
    }
    public abstract String explain();
}
