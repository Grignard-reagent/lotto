package com.lab.lotto.common.exception.system;

import com.lab.lotto.common.exception.base.BaseException;

public class SystemException extends BaseException {
    public SystemException(String code, String message) {
        super(code, message);
    }

    public SystemException(String code, String message, String path) {
        super(code, message, path);
    }

    public SystemException(String code, String message, Throwable cause) {
        super(code, message, cause);
    }

    public SystemException(String code, String message, String path, Throwable cause) {
        super(code, message, path, cause);
    }
} 