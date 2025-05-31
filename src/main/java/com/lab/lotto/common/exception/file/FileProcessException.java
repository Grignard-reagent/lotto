package com.lab.lotto.common.exception.file;

import com.lab.lotto.common.exception.base.BaseException;

public class FileProcessException extends BaseException {
    public FileProcessException(String code, String message) {
        super(code, message);
    }

    public FileProcessException(String code, String message, String path) {
        super(code, message, path);
    }

    public FileProcessException(String code, String message, Throwable cause) {
        super(code, message, cause);
    }

    public FileProcessException(String code, String message, String path, Throwable cause) {
        super(code, message, path, cause);
    }
} 