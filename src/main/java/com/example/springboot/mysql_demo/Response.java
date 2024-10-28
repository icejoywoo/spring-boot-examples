package com.example.springboot.mysql_demo;

import lombok.Data;

@Data
public class Response<T> {
    private T data;
    private boolean success;
    private String errorMessage;

    public static <T> Response<T> success(T data) {
        Response<T> response = new Response<>();
        response.setSuccess(true);
        response.setData(data);
        return response;
    }

    public static Response<Void> fail(Throwable throwable) {
        Response<Void> response = new Response<>();
        response.setSuccess(false);
        response.setErrorMessage(throwable.getMessage());
        return response;
    }
}
