package com.lab.lotto.common.exception;

public class ErrorCode {
    // 系统错误码 (1000-1999)
    public static final String SYSTEM_ERROR = "1000";
    public static final String SYSTEM_BUSY = "1001";
    public static final String SYSTEM_TIMEOUT = "1002";
    public static final String SYSTEM_CONFIG_ERROR = "1003";
    public static final String SYSTEM_DEPENDENCY_ERROR = "1004";

    // 业务错误码 (2000-2999)
    public static final String BUSINESS_ERROR = "2000";
    public static final String BUSINESS_INVALID_OPERATION = "2001";
    public static final String BUSINESS_DUPLICATE_OPERATION = "2002";
    public static final String BUSINESS_RESOURCE_NOT_FOUND = "2003";
    public static final String BUSINESS_RESOURCE_EXPIRED = "2004";
    public static final String BUSINESS_RESOURCE_LOCKED = "2005";

    // 验证错误码 (3000-3999)
    public static final String VALIDATION_ERROR = "3000";
    public static final String VALIDATION_PARAM_ERROR = "3001";
    public static final String VALIDATION_PARAM_MISSING = "3002";
    public static final String VALIDATION_PARAM_FORMAT_ERROR = "3003";
    public static final String VALIDATION_PARAM_RANGE_ERROR = "3004";
    public static final String VALIDATION_PARAM_LENGTH_ERROR = "3005";

    // 文件处理错误码 (4000-4999)
    public static final String FILE_ERROR = "4000";
    public static final String FILE_NOT_FOUND = "4001";
    public static final String FILE_READ_ERROR = "4002";
    public static final String FILE_WRITE_ERROR = "4003";
    public static final String FILE_FORMAT_ERROR = "4004";
    public static final String FILE_SIZE_ERROR = "4005";
    public static final String FILE_TYPE_ERROR = "4006";

    // 数据库错误码 (5000-5999)
    public static final String DB_ERROR = "5000";
    public static final String DB_CONNECTION_ERROR = "5001";
    public static final String DB_QUERY_ERROR = "5002";
    public static final String DB_UPDATE_ERROR = "5003";
    public static final String DB_DUPLICATE_KEY = "5004";
    public static final String DB_FOREIGN_KEY = "5005";
    public static final String DB_DEADLOCK = "5006";

    // 权限错误码 (6000-6999)
    public static final String AUTH_ERROR = "6000";
    public static final String AUTH_UNAUTHORIZED = "6001";
    public static final String AUTH_FORBIDDEN = "6002";
    public static final String AUTH_TOKEN_EXPIRED = "6003";
    public static final String AUTH_TOKEN_INVALID = "6004";
    public static final String AUTH_PERMISSION_DENIED = "6005";

    // 导入导出错误码 (7000-7999)
    public static final String IMPORT_EXPORT_ERROR = "7000";
    public static final String IMPORT_ERROR = "7001";
    public static final String EXPORT_ERROR = "7002";
    public static final String IMPORT_VALIDATION_ERROR = "7003";
    public static final String EXPORT_VALIDATION_ERROR = "7004";
    public static final String IMPORT_DUPLICATE_ERROR = "7005";
    public static final String EXPORT_DUPLICATE_ERROR = "7006";
} 