package com.example._th_assignment.ControllerAdvice;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Map<String, Object>> handleResponseStatus(ResponseStatusException ex) {
        String message = ex.getReason();
        if(message==null){message = "아마 이유를 지정안한것같음";}
        return ResponseEntity
                .status(ex.getStatusCode())
                .body(Map.of("message", message));
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            map.put(fieldError.getField(), fieldError.getDefaultMessage());
        }


        return  ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Map.of("message", map));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneric(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of(
                        "message", "서버 내부 오류가 발생했습니다.",
                        "detail", ex.getMessage()
                ));
    }
}

