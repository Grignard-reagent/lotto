package com.lab.lotto.service;

import com.lab.lotto.entity.Prize;
import com.lab.lotto.entity.Record;
import com.lab.lotto.entity.User;

public interface LottoService {
    /**
     * 抽奖
     * @param uid 用户ID
     * @return 中奖记录
     */
    Record draw(Integer uid);

    /**
     * 检查用户是否可以抽奖
     * @param uid 用户ID
     * @return 是否可以抽奖
     */
    boolean checkDrawable(Integer uid);

    /**
     * 获取用户中奖记录
     * @param uid 用户ID
     * @return 中奖记录
     */
    Record getDrawRecord(Integer uid);

    /**
     * 获取中奖奖品
     * @param rid 记录ID
     * @return 奖品信息
     */
    Prize getPrize(Integer rid);

    /**
     * 获取中奖用户
     * @param rid 记录ID
     * @return 用户信息
     */
    User getUser(Integer rid);

    /**
     * 获取用户剩余抽奖次数
     * @param uid 用户ID
     * @return 剩余抽奖次数
     */
    int getRemainingDraws(Integer uid);
} 