package com.example._th_assignment.ApiResponse;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
public class ApiResponse<T> {
    private String message;
    private T data;
    private T attacheddata;

    public ApiResponse(){
    }
    public ApiResponse(String message, T data, T attacheddata) {
        this.message = message;
        this.data = data;
        this.attacheddata = attacheddata;
    }

    public static <T> ApiResponse<T> success(String message, T data, T attacheddata) {
        return new ApiResponse<>(message, data,attacheddata);
    }

    public static <T> ApiResponse<T> success(String message, T data){
        return new ApiResponse<>(message,data, null);
    }
    public static <T> ApiResponse<T> success(String message){
        return new ApiResponse<>(message,null, null);
    }

    public static <T> ApiResponse<T> failed(String message){
        return new ApiResponse<>(message, null, null);
    }
}
