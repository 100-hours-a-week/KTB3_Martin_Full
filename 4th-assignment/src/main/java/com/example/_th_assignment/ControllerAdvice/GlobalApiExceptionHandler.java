package com.example._th_assignment.ControllerAdvice;


import com.example._th_assignment.ApiResponse.ApiResponse;
import com.example._th_assignment.CustomException.DtoConflictException;
import com.example._th_assignment.CustomException.DtoNotFoundException;
import com.example._th_assignment.CustomException.UserUnAuthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice(annotations = RestController.class)
public class GlobalApiExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<?> handleResponseStatus(ResponseStatusException ex) {
        HttpStatusCode statuscode = ex.getStatusCode();
        HttpStatus status = HttpStatus.valueOf(statuscode.value());
        
        String message = ex.getReason();
        if(message==null){message = "아마 이유를 지정안한것같음";}



        return ResponseEntity
                .status(ex.getStatusCode())
                .body(ApiResponse.failed(status.getReasonPhrase(), message));
    }

    @ExceptionHandler(DtoNotFoundException.class)
    public ResponseEntity<?> handleDtoNotFound(DtoNotFoundException ex) {
        HttpStatusCode statuscode = HttpStatus.NOT_FOUND;
        HttpStatus status = HttpStatus.valueOf(statuscode.value());
        String message = ex.getMessage();

        return ResponseEntity
                .status(statuscode)
                .body(ApiResponse.failed(status.getReasonPhrase(), message));
    }

    @ExceptionHandler(DtoConflictException.class)
    public ResponseEntity<?> handleDtoConflict(DtoConflictException ex) {
        HttpStatusCode statuscode = HttpStatus.CONFLICT;

        return ResponseEntity
                .status(statuscode)
                .body(ApiResponse.failed(ex.explain(),  ex.getMessage()));
    }

    @ExceptionHandler(UserUnAuthorizedException.class)
    public ResponseEntity<?> handleUserNotFound(UserUnAuthorizedException ex) {
        HttpStatusCode statuscode = HttpStatus.UNAUTHORIZED;
        HttpStatus status = HttpStatus.valueOf(statuscode.value());

        return ResponseEntity.status(statuscode)
                .body(ApiResponse.failed(status.getReasonPhrase(), ex.getMessage()));
    }



    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {

        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            map.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return  ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.failed(HttpStatus.BAD_REQUEST.getReasonPhrase(), map));
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleBadJson(HttpMessageNotReadableException ex) {

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.failed(HttpStatus.BAD_REQUEST.getReasonPhrase(), "Malformed JSON"));
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGeneric(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.failed(ex.getMessage(), "internal server error"));

    }
}

