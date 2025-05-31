package com.lab.lotto.dto.response;

import lombok.Data;

/**
 * 统一响应结果类
 */
@Data
public class ResponseResult<T> {
    private Integer code;
    private String message;
    private T data;

    private ResponseResult(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 成功响应
     */
    public static <T> ResponseResult<T> success() {
        return new ResponseResult<>(200, "操作成功", null);
    }

    /**
     * 成功响应（带数据）
     */
    public static <T> ResponseResult<T> success(T data) {
        return new ResponseResult<>(200, "操作成功", data);
    }

    /**
     * 成功响应（带消息和数据）
     */
    public static <T> ResponseResult<T> success(String message, T data) {
        return new ResponseResult<>(200, message, data);
    }

    /**
     * 错误响应
     */
    public static <T> ResponseResult<T> error(String message) {
        return new ResponseResult<>(500, message, null);
    }

    /**
     * 错误响应（带状态码）
     */
    public static <T> ResponseResult<T> error(Integer code, String message) {
        return new ResponseResult<>(code, message, null);
    }

    /**
     * 错误响应（带状态码和数据）
     */
    public static <T> ResponseResult<T> error(Integer code, String message, T data) {
        return new ResponseResult<>(code, message, data);
    }
} 