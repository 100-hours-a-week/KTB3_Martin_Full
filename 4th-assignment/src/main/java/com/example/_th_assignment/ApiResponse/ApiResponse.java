package com.example._th_assignment.ApiResponse;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
public class ApiResponse<T> {
    private String message;
    private T data;

    public ApiResponse(){
    }
    public ApiResponse(String message, T data) {
        this.message = message;
        this.data = data;
    }

    public static <T> ApiResponse<T> success(String message, T data){
        return new ApiResponse<>(message,data);
    }
    public static <T> ApiResponse<T> success(String message){
        return new ApiResponse<>(message,null);
    }

    public static <T> ApiResponse<T> failed(String message){
        return new ApiResponse<>(message, null);
    }
}
