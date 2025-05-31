package com.lab.lotto.service;

import com.lab.lotto.entity.Message;
import com.lab.lotto.mapper.MessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageRetryService {
    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Scheduled(fixedRate = 60000)
    public void retryFailedMessages() {
        List<Message> failedMessages = messageMapper.selectFailedMessages();
        for (Message message : failedMessages) {
            if (message.getMretry() < 3) {
                // 重新发送到队列
                redisTemplate.opsForList().rightPush(
                    "message:queue:" + message.getMtype(),
                    message.getMid().toString()
                );
            }
        }
    }
} 