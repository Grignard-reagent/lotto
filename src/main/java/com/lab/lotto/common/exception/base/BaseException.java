package com.lab.lotto.common.exception.base;

import lombok.Getter;

@Getter
public class BaseException extends RuntimeException {
    private final String code;
    private final String message;
    private final String path;
    private final long timestamp;

    public BaseException(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
        this.path = "";
        this.timestamp = System.currentTimeMillis();
    }

    public BaseException(String code, String message, String path) {
        super(message);
        this.code = code;
        this.message = message;
        this.path = path;
        this.timestamp = System.currentTimeMillis();
    }

    public BaseException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.message = message;
        this.path = "";
        this.timestamp = System.currentTimeMillis();
    }

    public BaseException(String code, String message, String path, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.message = message;
        this.path = path;
        this.timestamp = System.currentTimeMillis();
    }
} 