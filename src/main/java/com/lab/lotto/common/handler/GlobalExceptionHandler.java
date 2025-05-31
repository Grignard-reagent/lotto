package com.lab.lotto.common.handler;

import com.lab.lotto.common.exception.ErrorCode;
import com.lab.lotto.common.exception.base.BaseException;
import com.lab.lotto.common.exception.business.BusinessException;
import com.lab.lotto.common.exception.file.FileProcessException;
import com.lab.lotto.common.exception.system.SystemException;
import com.lab.lotto.common.exception.validation.ValidationException;
import com.lab.lotto.common.response.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseException.class)
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<Void> handleBaseException(BaseException e, HttpServletRequest request) {
        log.error("基础异常: {}", e.getMessage(), e);
        return ApiResponse.error(e.getCode(), e.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<Void> handleBusinessException(BusinessException e, HttpServletRequest request) {
        log.error("业务异常: {}", e.getMessage(), e);
        return ApiResponse.error(e.getCode(), e.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<Void> handleValidationException(ValidationException e, HttpServletRequest request) {
        log.error("验证异常: {}", e.getMessage(), e);
        return ApiResponse.error(e.getCode(), e.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(FileProcessException.class)
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<Void> handleFileProcessException(FileProcessException e, HttpServletRequest request) {
        log.error("文件处理异常: {}", e.getMessage(), e);
        return ApiResponse.error(e.getCode(), e.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(SystemException.class)
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<Void> handleSystemException(SystemException e, HttpServletRequest request) {
        log.error("系统异常: {}", e.getMessage(), e);
        return ApiResponse.error(e.getCode(), e.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<Void> handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        String message = fieldErrors.stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));
        log.error("参数验证异常: {}", message, e);
        return ApiResponse.error(ErrorCode.VALIDATION_PARAM_ERROR, message, request.getRequestURI());
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<Void> handleBindException(BindException e, HttpServletRequest request) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        String message = fieldErrors.stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));
        log.error("参数绑定异常: {}", message, e);
        return ApiResponse.error(ErrorCode.VALIDATION_PARAM_ERROR, message, request.getRequestURI());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<Void> handleException(Exception e, HttpServletRequest request) {
        log.error("未知异常: {}", e.getMessage(), e);
        return ApiResponse.error(ErrorCode.SYSTEM_ERROR, "系统异常，请联系管理员", request.getRequestURI());
    }
} 