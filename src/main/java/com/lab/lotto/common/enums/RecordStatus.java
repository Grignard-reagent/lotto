package com.lab.lotto.common.enums;

/**
 * 记录状态枚举
 */
public enum RecordStatus {
    SUCCESS("SUCCESS", "成功"),
    FAILED("FAILED", "失败"),
    PROCESSING("PROCESSING", "处理中"),
    CANCELLED("CANCELLED", "已取消");

    private final String code;
    private final String description;

    RecordStatus(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
} 