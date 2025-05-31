package com.lab.lotto.service.impl;

import com.lab.lotto.common.enums.PrizeStatus;
import com.lab.lotto.entity.Prize;
import com.lab.lotto.entity.Record;
import com.lab.lotto.entity.User;
import com.lab.lotto.service.LottoService;
import com.lab.lotto.service.PrizeService;
import com.lab.lotto.service.RecordService;
import com.lab.lotto.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
public class LottoServiceImpl implements LottoService {

    @Autowired
    private UserService userService;

    @Autowired
    private PrizeService prizeService;

    @Autowired
    private RecordService recordService;

    private final Random random = new Random();

    @Override
    @Transactional
    public Record draw(Integer uid) {
        // 检查用户是否存在
        User user = userService.getUser(uid);
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }

        // 检查用户是否可以抽奖
        if (!checkDrawable(uid)) {
            throw new RuntimeException("用户不能抽奖");
        }

        // 获取可用奖品
        List<Prize> availablePrizes = prizeService.getPrizesByStatus(PrizeStatus.IN_PROGRESS.getCode());
        if (availablePrizes.isEmpty()) {
            throw new RuntimeException("没有可用奖品");
        }

        // 随机选择奖品
        Prize prize = availablePrizes.get(random.nextInt(availablePrizes.size()));

        // 创建中奖记录
        Record record = new Record();
        record.setUid(uid);
        record.setPid(prize.getPid());
        record.setRstatus("1");
        record.setDrawTime(LocalDateTime.now());
        record.setPname(prize.getPname());
        record.setRremark("抽奖中奖");

        // 保存记录
        recordService.createRecord(record);

        // 更新奖品库存和中奖数量
        prizeService.decreaseStock(prize.getPid());
        prizeService.increaseWinCount(prize.getPid());

        return record;
    }

    @Override
    public boolean checkDrawable(Integer uid) {
        // 检查用户是否存在
        User user = userService.getUser(uid);
        if (user == null) {
            return false;
        }

        // 检查用户是否已中奖
        Record record = recordService.getRecord(uid);
        if (record != null) {
            return false;
        }

        return true;
    }

    @Override
    public Record getDrawRecord(Integer uid) {
        return recordService.getRecord(uid);
    }

    @Override
    public Prize getPrize(Integer rid) {
        Record record = recordService.getRecord(rid);
        if (record == null) {
            return null;
        }
        return prizeService.getPrize(record.getPid());
    }

    @Override
    public User getUser(Integer rid) {
        Record record = recordService.getRecord(rid);
        if (record == null) {
            return null;
        }
        return userService.getUser(record.getUid());
    }

    @Override
    public int getRemainingDraws(Integer uid) {
        // 检查用户是否存在
        User user = userService.getUser(uid);
        if (user == null) {
            return 0;
        }

        // 检查用户是否已中奖
        Record record = recordService.getRecord(uid);
        if (record != null) {
            return 0;
        }

        // 默认每个用户只有一次抽奖机会
        return 1;
    }
} 