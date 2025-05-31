package com.lab.lotto.service.impl;

import com.lab.lotto.entity.StrategyPriceCooperation;
import com.lab.lotto.mapper.StrategyPriceCooperationMapper;
import com.lab.lotto.service.StrategyPriceCooperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StrategyPriceCooperationServiceImpl implements StrategyPriceCooperationService {
    @Autowired
    private StrategyPriceCooperationMapper strategyPriceCooperationMapper;

    @Override
    public StrategyPriceCooperation createCooperation(StrategyPriceCooperation cooperation) {
        strategyPriceCooperationMapper.insert(cooperation);
        return cooperation;
    }

    @Override
    public StrategyPriceCooperation updateCooperation(StrategyPriceCooperation cooperation) {
        strategyPriceCooperationMapper.update(cooperation);
        return cooperation;
    }

    @Override
    public boolean deleteCooperation(Integer spcid) {
        return strategyPriceCooperationMapper.delete(spcid) > 0;
    }

    @Override
    public StrategyPriceCooperation getCooperation(Integer spcid) {
        return strategyPriceCooperationMapper.selectById(spcid);
    }

    @Override
    public List<StrategyPriceCooperation> getAllCooperations() {
        return strategyPriceCooperationMapper.selectAll();
    }

    @Override
    public List<StrategyPriceCooperation> getCooperationsByStrategy(Integer strategyId) {
        return strategyPriceCooperationMapper.selectByStrategyId(strategyId);
    }

    @Override
    public List<StrategyPriceCooperation> getCooperationsByPrize(Integer prizeId) {
        return strategyPriceCooperationMapper.selectByPrizeId(prizeId);
    }

    @Override
    public boolean updatePrizeWeight(Integer spcid, Double weight) {
        StrategyPriceCooperation cooperation = strategyPriceCooperationMapper.selectById(spcid);
        if (cooperation != null) {
            cooperation.setPweight(weight);
            return strategyPriceCooperationMapper.update(cooperation) > 0;
        }
        return false;
    }

    @Override
    public boolean updatePrizeProbability(Integer spcid, Double probability) {
        StrategyPriceCooperation cooperation = strategyPriceCooperationMapper.selectById(spcid);
        if (cooperation != null) {
            cooperation.setPprobability(probability);
            return strategyPriceCooperationMapper.update(cooperation) > 0;
        }
        return false;
    }
} 