package com.example._th_assignment.CustomException;

public class UserUnAuthorizedException extends RuntimeException {
    private final String email;
    public UserUnAuthorizedException(String email) {
        super("failed authenticate with email:" + email);
        this.email = email;

    }
}
