package com.lab.lotto.utils;

import com.lab.lotto.entity.Prize;
import com.lab.lotto.entity.StrategyPriceCooperation;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
public class DrawAlgorithmUtil {
    private final Random random = new Random();

    /**
     * 权重抽奖算法
     * @param cooperations 策略奖品关联列表
     * @return 中奖奖品
     */
    public Prize weightDraw(List<StrategyPriceCooperation> cooperations) {
        if (cooperations == null || cooperations.isEmpty()) {
            return null;
        }

        // 计算总权重
        double totalWeight = cooperations.stream()
                .filter(coop -> coop.getPweight() != null)
                .mapToDouble(StrategyPriceCooperation::getPweight)
                .sum();

        if (totalWeight <= 0) {
            return null;
        }

        // 生成随机数
        double randomValue = random.nextDouble() * totalWeight;
        double currentWeight = 0;

        // 按权重区间选择奖品
        for (StrategyPriceCooperation coop : cooperations) {
            if (coop.getPweight() == null) {
                continue;
            }
            currentWeight += coop.getPweight();
            if (randomValue <= currentWeight) {
                return coop.getPrize();
            }
        }

        return null;
    }

    /**
     * 普通抽奖算法
     * @param cooperations 策略奖品关联列表
     * @return 中奖奖品
     */
    public Prize probabilityDraw(List<StrategyPriceCooperation> cooperations) {
        if (cooperations == null || cooperations.isEmpty()) {
            return null;
        }

        // 生成随机数
        double randomValue = random.nextDouble();
        double currentProbability = 0;

        // 按概率区间选择奖品
        for (StrategyPriceCooperation coop : cooperations) {
            if (coop.getPprobability() == null) {
                continue;
            }
            currentProbability += coop.getPprobability();
            if (randomValue <= currentProbability) {
                return coop.getPrize();
            }
        }

        return null;
    }

    /**
     * 保底抽奖算法
     * @param cooperations 策略奖品关联列表
     * @return 保底奖品
     */
    public Prize unluckyDuckDraw(List<StrategyPriceCooperation> cooperations) {
        if (cooperations == null || cooperations.isEmpty()) {
            return null;
        }

        // 查找保底奖品
        return cooperations.stream()
                .filter(coop -> coop.getPrize() != null && coop.getPrize().getPunluckyduck() == 1)
                .findFirst()
                .map(StrategyPriceCooperation::getPrize)
                .orElse(null);
    }
} 