package com.lab.lotto.service;

public interface DrawLimitService {
    /**
     * 检查用户抽奖频率
     * @param uid 用户ID
     * @return 是否可以抽奖
     */
    boolean checkDrawFrequency(Integer uid);
    
    /**
     * 检查用户黑名单
     * @param uid 用户ID
     * @return 是否在黑名单中
     */
    boolean checkBlacklist(Integer uid);
    
    /**
     * 记录抽奖频率
     * @param uid 用户ID
     */
    void recordDrawFrequency(Integer uid);
} 