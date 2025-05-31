package com.lab.lotto.entity;

/**
 * 消息类型枚举
 */
public enum MessageType {
    DRAW_RESULT("抽奖结果"),
    PRIZE_DISTRIBUTION("奖品发放"),
    STATISTICS_UPDATE("统计更新"),
    USER_BLACKLIST("用户黑名单"),
    PRIZE_STOCK("奖品库存");

    private final String description;

    MessageType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
} 