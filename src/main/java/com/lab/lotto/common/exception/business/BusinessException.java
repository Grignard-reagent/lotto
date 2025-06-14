package com.lab.lotto.common.exception.business;

import com.lab.lotto.common.exception.base.BaseException;

public class BusinessException extends BaseException {
    public BusinessException(String code, String message) {
        super(code, message);
    }

    public BusinessException(String code, String message, String path) {
        super(code, message, path);
    }

    public BusinessException(String code, String message, Throwable cause) {
        super(code, message, cause);
    }

    public BusinessException(String code, String message, String path, Throwable cause) {
        super(code, message, path, cause);
    }
} 