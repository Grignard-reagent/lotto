package com.lab.lotto.service.impl;

import com.lab.lotto.entity.Prize;
import com.lab.lotto.mapper.PrizeMapper;
import com.lab.lotto.service.PrizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PrizeServiceImpl implements PrizeService {

    @Autowired
    private PrizeMapper prizeMapper;

    @Override
    @Transactional
    public Prize createPrize(Prize prize) {
        prize.setPcreateTime(LocalDateTime.now());
        prize.setPupdateTime(LocalDateTime.now());
        prizeMapper.insert(prize);
        return prize;
    }

    @Override
    @Transactional
    public Prize updatePrize(Prize prize) {
        prize.setPupdateTime(LocalDateTime.now());
        prizeMapper.updateById(prize);
        return prize;
    }

    @Override
    @Transactional
    public boolean deletePrize(Integer pid) {
        return prizeMapper.deleteById(pid) > 0;
    }

    @Override
    public Prize getPrize(Integer pid) {
        return prizeMapper.selectById(pid);
    }

    @Override
    public List<Prize> getAllPrizes() {
        return prizeMapper.selectList(null);
    }

    @Override
    public List<Prize> getPrizesByType(Integer ptype) {
        return prizeMapper.selectByType(ptype.toString());
    }

    @Override
    public List<Prize> getPrizesByStatus(Integer pstatus) {
        return prizeMapper.selectByStatus(pstatus.toString());
    }

    @Override
    @Transactional
    public boolean decreaseStock(Integer pid) {
        return prizeMapper.decreaseStock(pid) > 0;
    }

    @Override
    @Transactional
    public boolean increaseWinCount(Integer pid) {
        return prizeMapper.increaseWinCount(pid) > 0;
    }

    @Override
    @Transactional
    public void updateStock(Integer pid, int quantity) {
        Prize prize = getPrize(pid);
        if (prize != null) {
            prize.setPnowQ(prize.getPnowQ() + quantity);
            updatePrize(prize);
        }
    }
}