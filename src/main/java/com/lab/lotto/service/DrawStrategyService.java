package com.lab.lotto.service;

import com.lab.lotto.entity.DrawStrategy;
import com.lab.lotto.entity.StrategyPriceCooperation;
import java.util.List;

public interface DrawStrategyService {
    /**
     * 创建抽奖策略
     * @param strategy 策略信息
     * @return 创建的策略
     */
    DrawStrategy createStrategy(DrawStrategy strategy);

    /**
     * 更新抽奖策略
     * @param strategy 策略信息
     * @return 更新后的策略
     */
    DrawStrategy updateStrategy(DrawStrategy strategy);

    /**
     * 删除抽奖策略
     * @param sid 策略ID
     * @return 是否删除成功
     */
    boolean deleteStrategy(Integer sid);

    /**
     * 获取抽奖策略
     * @param strategyId 策略ID
     * @return 抽奖策略
     */
    DrawStrategy getStrategy(Integer strategyId);

    /**
     * 获取所有抽奖策略
     * @return 策略列表
     */
    List<DrawStrategy> getAllStrategies();

    /**
     * 根据名称获取策略
     * @param sname 策略名称
     * @return 策略信息
     */
    DrawStrategy getStrategyByName(String sname);

    /**
     * 根据类型获取策略
     * @param stype 策略类型
     * @return 策略列表
     */
    List<DrawStrategy> getStrategiesByType(Integer stype);

    /**
     * 获取策略关联的奖品
     * @param strategyId 策略ID
     * @return 策略奖品关联列表
     */
    List<StrategyPriceCooperation> getStrategyPrizes(Integer strategyId);
} 