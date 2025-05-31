package com.lab.lotto.common.enums;

/**
 * 抽奖策略类型枚举
 */
public enum StrategyType {
    WEIGHT(1, "权重抽奖"),
    NORMAL(2, "普通抽奖");
    
    private final Integer code;
    private final String desc;
    
    StrategyType(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
    
    public Integer getCode() {
        return code;
    }
    
    public String getDesc() {
        return desc;
    }
} 