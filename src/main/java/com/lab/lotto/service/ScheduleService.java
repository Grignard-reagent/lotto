package com.lab.lotto.service;

import com.lab.lotto.mapper.BlacklistMapper;
import com.lab.lotto.mapper.MessageQueueMapper;
import com.lab.lotto.mapper.MessageProcessLogMapper;
import com.lab.lotto.mapper.PrizeMapper;
import com.lab.lotto.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Slf4j
@Service
public class ScheduleService {

    private final BlacklistMapper blacklistMapper;
    private final MessageQueueMapper messageQueueMapper;
    private final MessageProcessLogMapper messageProcessLogMapper;
    private final PrizeMapper prizeMapper;
    private final UserMapper userMapper;

    public ScheduleService(BlacklistMapper blacklistMapper,
                          MessageQueueMapper messageQueueMapper,
                          MessageProcessLogMapper messageProcessLogMapper,
                          PrizeMapper prizeMapper,
                          UserMapper userMapper) {
        this.blacklistMapper = blacklistMapper;
        this.messageQueueMapper = messageQueueMapper;
        this.messageProcessLogMapper = messageProcessLogMapper;
        this.prizeMapper = prizeMapper;
        this.userMapper = userMapper;
    }

    @Scheduled(cron = "0 0 0 * * ?")
    @Transactional
    public void cleanExpiredData() {
        LocalDateTime now = LocalDateTime.now();
        try {
            // 清理过期黑名单
            int blacklistCount = blacklistMapper.deleteExpired(now);
            log.info("清理过期黑名单: {} 条", blacklistCount);

            // 清理过期消息
            int messageCount = messageQueueMapper.deleteExpired(now);
            log.info("清理过期消息: {} 条", messageCount);

            // 清理过期日志
            int logCount = messageProcessLogMapper.deleteExpired(now);
            log.info("清理过期日志: {} 条", logCount);
        } catch (Exception e) {
            log.error("清理过期数据失败", e);
        }
    }

    @Scheduled(cron = "0 0 1 * * ?")
    @Transactional
    public void updateStatistics() {
        try {
            // 更新奖品统计
            int prizeCount = prizeMapper.updateStatistics();
            log.info("更新奖品统计: {} 条", prizeCount);

            // 更新用户统计
            int userCount = userMapper.updateStatistics();
            log.info("更新用户统计: {} 条", userCount);
        } catch (Exception e) {
            log.error("更新统计数据失败", e);
        }
    }
} 