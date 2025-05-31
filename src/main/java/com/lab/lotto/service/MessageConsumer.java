package com.lab.lotto.service;

import com.alibaba.fastjson2.JSON;
import com.lab.lotto.common.enums.MessageType;
import com.lab.lotto.entity.Message;
import com.lab.lotto.entity.MessageProcessLog;
import com.lab.lotto.entity.Record;
import com.lab.lotto.mapper.MessageMapper;
import com.lab.lotto.mapper.MessageProcessLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class MessageConsumer {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private MessageProcessLogMapper processLogMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private RecordService recordService;

    @Scheduled(fixedRate = 1000)
    public void consumeMessages() {
        for (MessageType type : MessageType.values()) {
            String messageId = redisTemplate.opsForList().leftPop("message:queue:" + type.name());
            if (messageId != null) {
                processMessage(Long.parseLong(messageId));
            }
        }
    }

    private void processMessage(Long messageId) {
        Message message = messageMapper.selectById(messageId);
        if (message != null && message.getMstatus() == 0) {
            try {
                // 更新消息状态为处理中
                message.setMstatus((byte)1);
                message.setUpdateTime(LocalDateTime.now());
                messageMapper.updateStatus(message);

                // 处理消息
                switch (MessageType.valueOf(message.getMtype())) {
                    case DRAW_RESULT:
                        handleDrawResult(message);
                        break;
                    case PRIZE_DISTRIBUTION:
                        handlePrizeDistribution(message);
                        break;
                    case SYSTEM_LOG:
                        handleSystemLog(message);
                        break;
                    case STATISTICS_UPDATE:
                        handleStatisticsUpdate(message);
                        break;
                }

                // 更新消息状态为处理成功
                message.setMstatus((byte)2);
                message.setUpdateTime(LocalDateTime.now());
                messageMapper.updateStatus(message);

                // 记录处理日志
                saveProcessLog(message, true, null);

            } catch (Exception e) {
                handleError(message, e);
            }
        }
    }

    private void handleDrawResult(Message message) {
        Record record = JSON.parseObject(message.getMcontent(), Record.class);
        // 发送通知给用户
        userService.updateUserStatus(record.getUid(), "已中奖");
    }

    private void handlePrizeDistribution(Message message) {
        Record record = JSON.parseObject(message.getMcontent(), Record.class);
        // 更新记录状态为已发放
        record.setRstatus("3"); // 已发放
        recordService.updateRecord(record);
    }

    private void handleSystemLog(Message message) {
        // 处理系统日志
    }

    private void handleStatisticsUpdate(Message message) {
        // 处理统计数据更新
    }

    private void handleError(Message message, Exception e) {
        message.setMstatus((byte)3);
        message.setMretry(message.getMretry() + 1);
        message.setMerror(e.getMessage());
        message.setUpdateTime(LocalDateTime.now());

        // 设置下次重试时间（5分钟后）
        message.setNextRetryTime(LocalDateTime.now().plus(5, ChronoUnit.MINUTES));

        messageMapper.updateStatus(message);
        saveProcessLog(message, false, e.getMessage());
    }

    private void saveProcessLog(Message message, boolean success, String error) {
        MessageProcessLog log = new MessageProcessLog();
        log.setMid(message.getMid());
        log.setLstatus(success ? (byte)1 : (byte)0);
        log.setProcessTime(LocalDateTime.now());
        log.setLresult(success ? "处理成功" : "处理失败");
        log.setLerror(error);
        processLogMapper.insert(log);
    }
} 