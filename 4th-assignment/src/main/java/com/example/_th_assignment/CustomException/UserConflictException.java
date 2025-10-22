package com.example._th_assignment.CustomException;

public class UserConflictException extends DtoConflictException {
    private final String email;
    public UserConflictException(String email) {
        super("User conflict with email:" + email);
        this.email = email;
    }

    public String explain(){
        return "same email already exists";
    }
}
