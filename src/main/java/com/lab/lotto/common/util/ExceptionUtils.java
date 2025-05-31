package com.lab.lotto.common.util;

import com.lab.lotto.common.exception.ErrorCode;
import com.lab.lotto.common.exception.business.BusinessException;
import com.lab.lotto.common.exception.file.FileProcessException;
import com.lab.lotto.common.exception.system.SystemException;
import com.lab.lotto.common.exception.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExceptionUtils {
    private ExceptionUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static void throwBusinessException(String message) {
        log.error("业务异常: {}", message);
        throw new BusinessException(ErrorCode.BUSINESS_ERROR, message);
    }

    public static void throwBusinessException(String code, String message) {
        log.error("业务异常: {}", message);
        throw new BusinessException(code, message);
    }

    public static void throwValidationException(String message) {
        log.error("验证异常: {}", message);
        throw new ValidationException(ErrorCode.VALIDATION_ERROR, message);
    }

    public static void throwValidationException(String code, String message) {
        log.error("验证异常: {}", message);
        throw new ValidationException(code, message);
    }

    public static void throwFileProcessException(String message) {
        log.error("文件处理异常: {}", message);
        throw new FileProcessException(ErrorCode.FILE_ERROR, message);
    }

    public static void throwFileProcessException(String code, String message) {
        log.error("文件处理异常: {}", message);
        throw new FileProcessException(code, message);
    }

    public static void throwSystemException(String message) {
        log.error("系统异常: {}", message);
        throw new SystemException(ErrorCode.SYSTEM_ERROR, message);
    }

    public static void throwSystemException(String code, String message) {
        log.error("系统异常: {}", message);
        throw new SystemException(code, message);
    }

    public static void throwSystemException(String message, Throwable cause) {
        log.error("系统异常: {}", message, cause);
        throw new SystemException(ErrorCode.SYSTEM_ERROR, message, cause);
    }

    public static void throwSystemException(String code, String message, Throwable cause) {
        log.error("系统异常: {}", message, cause);
        throw new SystemException(code, message, cause);
    }
} 