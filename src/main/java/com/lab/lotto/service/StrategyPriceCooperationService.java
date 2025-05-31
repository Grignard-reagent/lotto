package com.lab.lotto.service;

import com.lab.lotto.entity.StrategyPriceCooperation;
import java.util.List;

public interface StrategyPriceCooperationService {
    /**
     * 创建策略奖品关联
     * @param cooperation 关联信息
     * @return 创建的关联
     */
    StrategyPriceCooperation createCooperation(StrategyPriceCooperation cooperation);

    /**
     * 更新策略奖品关联
     * @param cooperation 关联信息
     * @return 更新后的关联
     */
    StrategyPriceCooperation updateCooperation(StrategyPriceCooperation cooperation);

    /**
     * 删除策略奖品关联
     * @param spcid 关联ID
     * @return 是否删除成功
     */
    boolean deleteCooperation(Integer spcid);

    /**
     * 获取策略奖品关联
     * @param spcid 关联ID
     * @return 关联信息
     */
    StrategyPriceCooperation getCooperation(Integer spcid);

    /**
     * 获取所有策略奖品关联
     * @return 关联列表
     */
    List<StrategyPriceCooperation> getAllCooperations();

    /**
     * 获取策略的奖品关联
     * @param strategyId 策略ID
     * @return 关联列表
     */
    List<StrategyPriceCooperation> getCooperationsByStrategy(Integer strategyId);

    /**
     * 获取奖品的策略关联
     * @param prizeId 奖品ID
     * @return 关联列表
     */
    List<StrategyPriceCooperation> getCooperationsByPrize(Integer prizeId);

    /**
     * 更新奖品权重
     * @param spcid 关联ID
     * @param weight 权重
     * @return 是否更新成功
     */
    boolean updatePrizeWeight(Integer spcid, Double weight);

    /**
     * 更新奖品概率
     * @param spcid 关联ID
     * @param probability 概率
     * @return 是否更新成功
     */
    boolean updatePrizeProbability(Integer spcid, Double probability);
} 