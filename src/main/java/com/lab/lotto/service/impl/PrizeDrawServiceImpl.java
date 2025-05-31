package com.lab.lotto.service.impl;

import com.lab.lotto.entity.Record;
import com.lab.lotto.entity.Prize;
import com.lab.lotto.entity.DrawStrategy;
import com.lab.lotto.entity.StrategyPriceCooperation;
import com.lab.lotto.service.PrizeDrawService;
import com.lab.lotto.service.UserService;
import com.lab.lotto.service.DrawStrategyService;
import com.lab.lotto.service.DrawFrequencyService;
import com.lab.lotto.service.RecordService;
import com.lab.lotto.service.ConfigsService;
import com.lab.lotto.service.StrategyPriceCooperationService;
import com.lab.lotto.utils.DrawAlgorithmUtil;
import com.lab.lotto.utils.DrawValidationUtil;
import com.lab.lotto.service.MessageProducer;
import com.lab.lotto.common.enums.MessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PrizeDrawServiceImpl implements PrizeDrawService {

    @Autowired
    private UserService userService;

    @Autowired
    private DrawStrategyService drawStrategyService;

    @Autowired
    private DrawFrequencyService drawFrequencyService;

    @Autowired
    private RecordService recordService;

    @Autowired
    private ConfigsService configsService;

    @Autowired
    private StrategyPriceCooperationService cooperationService;

    @Autowired
    private DrawAlgorithmUtil drawAlgorithmUtil;

    @Autowired
    private DrawValidationUtil drawValidationUtil;

    @Autowired
    private MessageProducer messageProducer;

    @Override
    @Transactional
    public Record draw(Integer uid, Integer strategyId) {
        // 验证用户是否可以抽奖
        var userValidation = drawValidationUtil.validateUser(uid);
        if (!userValidation.isValid()) {
            throw new RuntimeException(userValidation.getMessage());
        }

        // 获取抽奖策略
        DrawStrategy strategy = drawStrategyService.getStrategy(strategyId);
        if (strategy == null) {
            throw new RuntimeException("抽奖策略不存在");
        }

        // 获取策略关联的奖品
        List<StrategyPriceCooperation> cooperations = cooperationService.getCooperationsByStrategy(strategyId);
        if (cooperations == null || cooperations.isEmpty()) {
            throw new RuntimeException("策略未配置奖品");
        }

        // 创建抽奖记录
        Record record = new Record();
        record.setUid(uid);
        record.setDtype(strategyId.byteValue());
        record.setDrawTime(LocalDateTime.now());
        record.setRstatus("0"); // 初始状态

        // 执行抽奖逻辑
        Prize prize = null;
        if (strategy.getStype() == 1) {
            // 权重抽奖
            prize = drawAlgorithmUtil.weightDraw(cooperations);
        } else if (strategy.getStype() == 2) {
            // 普通抽奖
            prize = drawAlgorithmUtil.probabilityDraw(cooperations);
        }

        // 验证奖品
        var prizeValidation = drawValidationUtil.validatePrize(prize);
        if (!prizeValidation.isValid()) {
            // 如果普通抽奖失败，尝试保底抽奖
            if (strategy.getStype() == 2) {
                prize = drawAlgorithmUtil.unluckyDuckDraw(cooperations);
                prizeValidation = drawValidationUtil.validatePrize(prize);
            }
            
            if (!prizeValidation.isValid()) {
                record.setRstatus("2"); // 未中奖
                record.setRremark("未中奖");
                recordService.createRecord(record);
                
                // 发送未中奖消息
                messageProducer.sendMessage(MessageType.DRAW_RESULT, record);
                return record;
            }
        }

        // 更新记录
        record.setPid(prize.getPid());
        record.setPname(prize.getPname());
        record.setRstatus("1"); // 中奖
        record.setRremark("抽奖中奖");

        // 更新抽奖频率
        drawFrequencyService.updateDrawFrequency(uid);

        // 保存记录
        recordService.createRecord(record);

        // 发送中奖消息
        messageProducer.sendMessage(MessageType.DRAW_RESULT, record);
        
        // 发送奖品发放消息
        messageProducer.sendMessage(MessageType.PRIZE_DISTRIBUTION, record);
        
        // 发送统计更新消息
        messageProducer.sendMessage(MessageType.STATISTICS_UPDATE, record);

        return record;
    }

    @Override
    public boolean canDraw(Integer uid) {
        var validation = drawValidationUtil.validateUser(uid);
        return validation.isValid();
    }

    @Override
    public int getDrawCount(Integer uid) {
        return drawFrequencyService.getCurrentDrawCount(uid);
    }
} 