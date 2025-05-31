package com.lab.lotto.service.impl;

import com.lab.lotto.entity.DrawStrategy;
import com.lab.lotto.entity.StrategyPriceCooperation;
import com.lab.lotto.mapper.DrawStrategyMapper;
import com.lab.lotto.mapper.StrategyPriceCooperationMapper;
import com.lab.lotto.service.DrawStrategyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DrawStrategyServiceImpl implements DrawStrategyService {
    @Autowired
    private DrawStrategyMapper strategyMapper;

    @Autowired
    private StrategyPriceCooperationMapper strategyPriceCooperationMapper;

    @Override
    public DrawStrategy createStrategy(DrawStrategy strategy) {
        strategyMapper.insert(strategy);
        return strategy;
    }

    @Override
    public DrawStrategy updateStrategy(DrawStrategy strategy) {
        strategyMapper.update(strategy);
        return strategy;
    }

    @Override
    public boolean deleteStrategy(Integer sid) {
        return strategyMapper.delete(sid) > 0;
    }

    @Override
    public DrawStrategy getStrategy(Integer strategyId) {
        return strategyMapper.selectById(strategyId);
    }

    @Override
    public List<DrawStrategy> getAllStrategies() {
        return strategyMapper.selectAll();
    }

    @Override
    public DrawStrategy getStrategyByName(String sname) {
        return strategyMapper.selectByName(sname);
    }

    @Override
    public List<DrawStrategy> getStrategiesByType(Integer stype) {
        return strategyMapper.selectByType(stype);
    }

    @Override
    public List<StrategyPriceCooperation> getStrategyPrizes(Integer strategyId) {
        return strategyPriceCooperationMapper.selectByStrategyId(strategyId);
    }
} 