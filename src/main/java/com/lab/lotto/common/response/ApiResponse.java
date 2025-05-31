package com.lab.lotto.common.response;

import lombok.Data;

@Data
public class ApiResponse<T> {
    private String code;
    private String message;
    private T data;
    private long timestamp;
    private String path;

    public ApiResponse() {
        this.timestamp = System.currentTimeMillis();
    }

    public ApiResponse(String code, String message) {
        this.code = code;
        this.message = message;
        this.timestamp = System.currentTimeMillis();
    }

    public ApiResponse(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.timestamp = System.currentTimeMillis();
    }

    public ApiResponse(String code, String message, T data, String path) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.path = path;
        this.timestamp = System.currentTimeMillis();
    }

    public static <T> ApiResponse<T> success() {
        return new ApiResponse<>("200", "操作成功");
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>("200", "操作成功", data);
    }

    public static <T> ApiResponse<T> success(T data, String path) {
        return new ApiResponse<>("200", "操作成功", data, path);
    }

    public static <T> ApiResponse<T> error(String code, String message) {
        return new ApiResponse<>(code, message);
    }

    public static <T> ApiResponse<T> error(String code, String message, String path) {
        return new ApiResponse<>(code, message, null, path);
    }
} 