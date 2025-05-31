package com.lab.lotto.service.impl;

import com.lab.lotto.entity.DrawFrequency;
import com.lab.lotto.mapper.DrawFrequencyMapper;
import com.lab.lotto.service.DrawFrequencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class DrawFrequencyServiceImpl implements DrawFrequencyService {
    @Autowired
    private DrawFrequencyMapper frequencyMapper;

    @Override
    public DrawFrequency createFrequency(DrawFrequency frequency) {
        frequencyMapper.insert(frequency);
        return frequency;
    }

    @Override
    public DrawFrequency updateFrequency(DrawFrequency frequency) {
        frequencyMapper.update(frequency);
        return frequency;
    }

    @Override
    public boolean deleteFrequency(Integer fid) {
        return frequencyMapper.delete(fid) > 0;
    }

    @Override
    public DrawFrequency getFrequency(Integer fid) {
        return frequencyMapper.selectById(fid);
    }

    @Override
    public List<DrawFrequency> getAllFrequencies() {
        return frequencyMapper.selectAll();
    }

    @Override
    public List<DrawFrequency> getFrequenciesByUser(Integer uid) {
        return frequencyMapper.selectByUid(uid);
    }

    @Override
    public List<DrawFrequency> getFrequenciesByTimeWindow(String timeWindow) {
        return frequencyMapper.selectByTimeWindow(timeWindow);
    }

    @Override
    public List<DrawFrequency> getFrequenciesByTimeRange(LocalDateTime startTime, LocalDateTime endTime) {
        return frequencyMapper.selectByTimeRange(startTime, endTime);
    }

    @Override
    public boolean checkUserLimit(Integer uid, String timeWindow, LocalDateTime currentTime) {
        int count = frequencyMapper.countByUserAndTimeWindow(uid, timeWindow, currentTime);
        return count > 0;
    }

    @Override
    public void updateDrawFrequency(Integer uid) {
        DrawFrequency frequency = new DrawFrequency();
        frequency.setUid(uid);
        frequency.setDrawTime(LocalDateTime.now());
        frequencyMapper.insert(frequency);
    }

    @Override
    public int getCurrentDrawCount(Integer uid) {
        return frequencyMapper.countByUid(uid);
    }
} 