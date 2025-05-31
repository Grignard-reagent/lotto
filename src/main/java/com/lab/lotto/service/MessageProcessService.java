package com.lab.lotto.service;

import com.lab.lotto.common.exception.ErrorCode;
import com.lab.lotto.common.exception.business.BusinessException;
import com.lab.lotto.common.exception.system.SystemException;
import com.lab.lotto.entity.MessageQueue;
import com.lab.lotto.entity.MessageProcessLog;
import com.lab.lotto.mapper.MessageProcessLogMapper;
import com.lab.lotto.mapper.MessageQueueMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class MessageProcessService {

    private final MessageQueueMapper messageQueueMapper;
    private final MessageProcessLogMapper messageProcessLogMapper;

    public MessageProcessService(MessageQueueMapper messageQueueMapper,
                               MessageProcessLogMapper messageProcessLogMapper) {
        this.messageQueueMapper = messageQueueMapper;
        this.messageProcessLogMapper = messageProcessLogMapper;
    }

    @Async("messageExecutor")
    @Transactional
    public void processMessageAsync(MessageQueue message) {
        try {
            // 更新消息状态为处理中
            messageQueueMapper.updateStatus(message.getMid(), (byte)1, LocalDateTime.now());

            // 处理消息
            processMessage(message);

            // 更新消息状态为已完成
            messageQueueMapper.updateStatus(message.getMid(), (byte)2, LocalDateTime.now());

            // 创建处理日志
            createProcessLog(message, true, null);
        } catch (Exception e) {
            log.error("处理消息失败: {}", message.getMid(), e);
            // 更新消息状态为失败
            messageQueueMapper.updateStatus(message.getMid(), (byte)3, LocalDateTime.now());
            // 创建处理日志
            createProcessLog(message, false, e.getMessage());
            throw new SystemException(ErrorCode.SYSTEM_ERROR, "处理消息失败");
        }
    }

    @Async("messageExecutor")
    @Transactional
    public void batchProcessMessages(List<MessageQueue> messages) {
        for (MessageQueue message : messages) {
            try {
                processMessageAsync(message);
            } catch (Exception e) {
                log.error("批量处理消息失败: {}", message.getMid(), e);
            }
        }
    }

    private void processMessage(MessageQueue message) {
        // 根据消息类型处理不同的业务逻辑
        switch (message.getMtype()) {
            case "PRIZE":
                processPrizeMessage(message);
                break;
            case "USER":
                processUserMessage(message);
                break;
            default:
                throw new BusinessException(ErrorCode.BUSINESS_ERROR, "未知的消息类型");
        }
    }

    private void processPrizeMessage(MessageQueue message) {
        // 处理奖品相关消息
        log.info("处理奖品消息: {}", message.getMid());
    }

    private void processUserMessage(MessageQueue message) {
        // 处理用户相关消息
        log.info("处理用户消息: {}", message.getMid());
    }

    private void createProcessLog(MessageQueue message, boolean success, String errorMessage) {
        MessageProcessLog log = new MessageProcessLog();
        log.setMid(message.getMid());
        log.setLstatus(success ? (byte)1 : (byte)0);
        log.setProcessTime(LocalDateTime.now());
        log.setLresult(success ? "处理成功" : "处理失败");
        log.setLerror(errorMessage);
        messageProcessLogMapper.insert(log);
    }
} 