package com.lab.lotto.common.enums;

public enum MessageType {
    DRAW_RESULT("抽奖结果通知"),
    PRIZE_DISTRIBUTION("奖品发放"),
    SYSTEM_LOG("系统日志"),
    STATISTICS_UPDATE("数据统计");

    private final String description;

    MessageType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
} 