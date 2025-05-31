package com.lab.lotto.service;

import com.lab.lotto.entity.DrawFrequency;
import java.util.List;
import java.time.LocalDateTime;

public interface DrawFrequencyService {
    /**
     * 创建抽奖频率记录
     * @param frequency 频率信息
     * @return 创建的频率记录
     */
    DrawFrequency createFrequency(DrawFrequency frequency);

    /**
     * 更新抽奖频率记录
     * @param frequency 频率信息
     * @return 更新后的频率记录
     */
    DrawFrequency updateFrequency(DrawFrequency frequency);

    /**
     * 删除抽奖频率记录
     * @param fid 频率记录ID
     * @return 是否删除成功
     */
    boolean deleteFrequency(Integer fid);

    /**
     * 获取抽奖频率记录
     * @param fid 频率记录ID
     * @return 频率记录
     */
    DrawFrequency getFrequency(Integer fid);

    /**
     * 获取所有抽奖频率记录
     * @return 频率记录列表
     */
    List<DrawFrequency> getAllFrequencies();

    /**
     * 获取用户的抽奖频率记录
     * @param uid 用户ID
     * @return 频率记录列表
     */
    List<DrawFrequency> getFrequenciesByUser(Integer uid);

    /**
     * 根据时间窗口获取频率记录
     * @param timeWindow 时间窗口
     * @return 频率记录列表
     */
    List<DrawFrequency> getFrequenciesByTimeWindow(String timeWindow);

    /**
     * 根据时间范围获取频率记录
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 频率记录列表
     */
    List<DrawFrequency> getFrequenciesByTimeRange(LocalDateTime startTime, LocalDateTime endTime);

    /**
     * 检查用户是否达到抽奖限制
     * @param uid 用户ID
     * @param timeWindow 时间窗口
     * @param currentTime 当前时间
     * @return 是否达到限制
     */
    boolean checkUserLimit(Integer uid, String timeWindow, LocalDateTime currentTime);

    /**
     * 更新抽奖频率
     * @param uid 用户ID
     */
    void updateDrawFrequency(Integer uid);

    /**
     * 获取当前抽奖次数
     * @param uid 用户ID
     * @return 抽奖次数
     */
    int getCurrentDrawCount(Integer uid);
} 