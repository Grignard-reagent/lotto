package com.lab.lotto.service;

import com.lab.lotto.dto.DrawResult;

public interface DrawService {
    /**
     * 执行抽奖
     * @param uid 用户ID
     * @param strategyId 策略ID
     * @return 抽奖结果
     */
    DrawResult draw(Integer uid, Integer strategyId);
} 