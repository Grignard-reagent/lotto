package com.lab.lotto.service;

import com.lab.lotto.entity.Record;
import com.lab.lotto.entity.Prize;

public interface PrizeDrawService {
    /**
     * 执行抽奖
     * @param uid 用户ID
     * @param strategyId 抽奖策略ID
     * @return 抽奖结果
     */
    Record draw(Integer uid, Integer strategyId);

    /**
     * 检查用户是否可以抽奖
     * @param uid 用户ID
     * @return 是否可以抽奖
     */
    boolean canDraw(Integer uid);

    /**
     * 获取用户抽奖次数
     * @param uid 用户ID
     * @return 抽奖次数
     */
    int getDrawCount(Integer uid);
} 