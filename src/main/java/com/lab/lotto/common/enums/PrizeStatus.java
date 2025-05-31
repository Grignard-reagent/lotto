package com.lab.lotto.common.enums;

/**
 * 奖品状态枚举
 */
public enum PrizeStatus {
    /**
     * 未开始
     */
    NOT_STARTED(0, "未开始"),

    /**
     * 进行中
     */
    IN_PROGRESS(1, "进行中"),

    /**
     * 已结束
     */
    ENDED(2, "已结束");

    private final Integer code;
    private final String message;

    PrizeStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
} 