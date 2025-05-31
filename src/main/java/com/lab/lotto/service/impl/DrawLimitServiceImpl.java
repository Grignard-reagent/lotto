package com.lab.lotto.service.impl;

import com.lab.lotto.entity.DrawFrequency;
import com.lab.lotto.entity.User;
import com.lab.lotto.mapper.DrawFrequencyMapper;
import com.lab.lotto.service.DrawLimitService;
import com.lab.lotto.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class DrawLimitServiceImpl implements DrawLimitService {

    @Autowired
    private DrawFrequencyMapper drawFrequencyMapper;

    @Autowired
    private UserService userService;

    @Override
    public boolean checkDrawFrequency(Integer uid) {
        // 获取用户当前时间窗口内的抽奖次数
        int count = drawFrequencyMapper.countByUserAndTimeWindow(uid, "1h", LocalDateTime.now());
        // 假设每小时最多抽奖3次
        return count < 3;
    }

    @Override
    public boolean checkBlacklist(Integer uid) {
        // 检查用户是否在黑名单中
        User user = userService.getUser(uid);
        return user != null && !user.getStatus().equals("2"); // 假设状态2表示黑名单
    }

    @Override
    public void recordDrawFrequency(Integer uid) {
        // 记录用户抽奖频率
        DrawFrequency frequency = new DrawFrequency();
        frequency.setUid(uid);
        frequency.setDrawTime(LocalDateTime.now());
        drawFrequencyMapper.insert(frequency);
    }
} 