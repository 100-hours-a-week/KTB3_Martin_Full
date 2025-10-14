package com.example._th_assignment.ControllerAdvice;


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
    public ResponseEntity<Map<String, Object>> handleResponseStatus(ResponseStatusException ex) {
        HttpStatusCode statuscode = ex.getStatusCode();
        HttpStatus status = HttpStatus.valueOf(statuscode.value());
        
        String message = ex.getReason();
        if(message==null){message = "아마 이유를 지정안한것같음";}

        Map<String,Object> map = new LinkedHashMap<>();
        map.put("status",status.value());
        map.put("error", status.getReasonPhrase());
        map.put("message", message);


        return ResponseEntity
                .status(ex.getStatusCode())
                .body(map);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {

        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            map.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        Map<String,Object> bodymap = new LinkedHashMap<>();
        bodymap.put("status",HttpStatus.BAD_REQUEST.value());
        bodymap.put("error", HttpStatus.BAD_REQUEST.getReasonPhrase());
        bodymap.put("message", map);


        return  ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(bodymap);
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String,Object>> handleBadJson(HttpMessageNotReadableException ex) {
        Map<String,Object> map = new LinkedHashMap<>();
        map.put("status",HttpStatus.BAD_REQUEST.value());
        map.put("error", HttpStatus.BAD_REQUEST.getReasonPhrase());
        map.put("message", "Malformed JSON");

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(map);
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

