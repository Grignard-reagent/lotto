package com.lab.lotto.service.impl;

import com.lab.lotto.common.enums.StrategyType;
import com.lab.lotto.dto.DrawResult;
import com.lab.lotto.entity.DrawStrategy;
import com.lab.lotto.entity.Prize;
import com.lab.lotto.entity.Record;
import com.lab.lotto.entity.StrategyPriceCooperation;
import com.lab.lotto.mapper.RecordMapper;
import com.lab.lotto.mapper.StrategyPriceCooperationMapper;
import com.lab.lotto.service.DrawLimitService;
import com.lab.lotto.service.DrawService;
import com.lab.lotto.service.DrawStrategyService;
import com.lab.lotto.service.PrizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DrawServiceImpl implements DrawService {
    
    @Autowired
    private DrawLimitService drawLimitService;
    
    @Autowired
    private DrawStrategyService drawStrategyService;
    
    @Autowired
    private PrizeService prizeService;
    
    @Autowired
    private RecordMapper recordMapper;
    
    @Autowired
    private StrategyPriceCooperationMapper cooperationMapper;
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public DrawResult draw(Integer uid, Integer strategyId) {
        // 1. 检查用户限制
        if (!drawLimitService.checkDrawFrequency(uid)) {
            return DrawResult.fail("超过抽奖频率限制");
        }
        if (!drawLimitService.checkBlacklist(uid)) {
            return DrawResult.fail("用户在黑名单中");
        }
        
        // 2. 获取抽奖策略
        DrawStrategy strategy = drawStrategyService.getStrategy(strategyId);
        if (strategy == null) {
            return DrawResult.fail("抽奖策略不存在");
        }
        
        // 3. 获取策略关联的奖品
        List<StrategyPriceCooperation> cooperations = 
            cooperationMapper.selectByStrategyId(strategyId);
        List<Prize> prizes = cooperations.stream()
            .map(coop -> prizeService.getPrize(coop.getPid()))
            .collect(Collectors.toList());
        
        // 4. 执行抽奖算法
        Prize prize = null;
        if (strategy.getStype().equals(StrategyType.WEIGHT.getCode())) {
            prize = weightDraw(prizes);
        } else {
            prize = normalDraw(prizes);
        }
        
        // 5. 记录抽奖结果
        Record record = new Record();
        record.setUid(uid);
        record.setPid(prize != null ? prize.getPid() : null);
        record.setDtype((byte)1); // 普通抽奖
        record.setDstatus(prize != null ? (byte)1 : (byte)0); // 1-中奖，0-未中奖
        record.setCreateTime(LocalDateTime.now());
        record.setUpdateTime(LocalDateTime.now());
        recordMapper.insert(record);
        
        // 6. 更新奖品库存
        if (prize != null) {
            prizeService.updateStock(prize.getPid(), -1);
        }
        
        // 7. 记录抽奖频率
        drawLimitService.recordDrawFrequency(uid);
        
        return DrawResult.success(prize, record);
    }
    
    // 权重抽奖算法
    private Prize weightDraw(List<Prize> prizes) {
        double totalWeight = prizes.stream()
            .mapToDouble(p -> p.getPweight() != null ? p.getPweight().doubleValue() : 0.0)
            .sum();
        
        double random = Math.random() * totalWeight;
        double currentWeight = 0;
        
        for (Prize prize : prizes) {
            currentWeight += prize.getPweight() != null ? prize.getPweight().doubleValue() : 0.0;
            if (random <= currentWeight) {
                return prize;
            }
        }
        return null;
    }
    
    // 普通抽奖算法
    private Prize normalDraw(List<Prize> prizes) {
        double totalProbability = prizes.stream()
            .mapToDouble(p -> p.getPwinP() != null ? p.getPwinP().doubleValue() : 0.0)
            .sum();
        
        double random = Math.random() * totalProbability;
        double currentProbability = 0;
        
        for (Prize prize : prizes) {
            currentProbability += prize.getPwinP() != null ? prize.getPwinP().doubleValue() : 0.0;
            if (random <= currentProbability) {
                return prize;
            }
        }
        return null;
    }
} 