package com.lab.lotto.service;

import com.alibaba.fastjson2.JSON;
import com.lab.lotto.common.enums.MessageType;
import com.lab.lotto.entity.Message;
import com.lab.lotto.mapper.MessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MessageProducer {
    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private StringRedisTemplate redisTemplate;

    public void sendMessage(MessageType type, Object content) {
        Message message = new Message();
        message.setMtype(type.name());
        message.setMcontent(JSON.toJSONString(content));
        message.setMstatus((byte) 0);
        message.setMretry(0);
        message.setCreateTime(LocalDateTime.now());
        message.setUpdateTime(LocalDateTime.now());

        // 存储到数据库
        messageMapper.insert(message);

        // 发送到Redis队列
        redisTemplate.opsForList().rightPush("message:queue:" + type.name(), message.getMid().toString());
    }
} 