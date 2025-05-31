package com.lab.lotto.common.exception.validation;

import com.lab.lotto.common.exception.base.BaseException;

public class ValidationException extends BaseException {
    public ValidationException(String code, String message) {
        super(code, message);
    }

    public ValidationException(String code, String message, String path) {
        super(code, message, path);
    }

    public ValidationException(String code, String message, Throwable cause) {
        super(code, message, cause);
    }

    public ValidationException(String code, String message, String path, Throwable cause) {
        super(code, message, path, cause);
    }
} 