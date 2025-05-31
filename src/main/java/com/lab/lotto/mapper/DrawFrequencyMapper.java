package com.lab.lotto.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lab.lotto.entity.DrawFrequency;
import org.apache.ibatis.annotations.*;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface DrawFrequencyMapper extends BaseMapper<DrawFrequency> {
    /**
     * 插入抽奖频率记录
     * @param frequency 频率记录
     * @return 影响行数
     */
    @Insert("INSERT INTO drawfrequency(uid, drawTime, drawCount, timeWindow, createTime, updateTime) " +
            "VALUES(#{uid}, #{drawTime}, #{drawCount}, #{timeWindow}, #{createTime}, #{updateTime})")
    int insert(DrawFrequency frequency);

    /**
     * 更新抽奖频率记录
     * @param frequency 频率记录
     * @return 影响行数
     */
    int update(DrawFrequency frequency);

    /**
     * 删除抽奖频率记录
     * @param fid 频率记录ID
     * @return 影响行数
     */
    int delete(Integer fid);

    /**
     * 根据ID查询抽奖频率记录
     * @param fid 频率记录ID
     * @return 频率记录
     */
    DrawFrequency selectById(Integer fid);

    /**
     * 查询所有抽奖频率记录
     * @return 频率记录列表
     */
    List<DrawFrequency> selectAll();

    /**
     * 根据用户ID查询抽奖频率记录
     * @param uid 用户ID
     * @return 频率记录列表
     */
    List<DrawFrequency> selectByUid(Integer uid);

    /**
     * 根据时间窗口查询抽奖频率记录
     * @param timeWindow 时间窗口
     * @return 频率记录列表
     */
    List<DrawFrequency> selectByTimeWindow(String timeWindow);

    /**
     * 根据时间范围查询抽奖频率记录
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 频率记录列表
     */
    List<DrawFrequency> selectByTimeRange(@Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime);

    /**
     * 统计用户在指定时间窗口内的抽奖次数
     * @param uid 用户ID
     * @param timeWindow 时间窗口
     * @param currentTime 当前时间
     * @return 抽奖次数
     */
    int countByUserAndTimeWindow(@Param("uid") Integer uid, @Param("timeWindow") String timeWindow, @Param("currentTime") LocalDateTime currentTime);

    /**
     * 统计用户的总抽奖次数
     * @param uid 用户ID
     * @return 抽奖次数
     */
    int countByUid(Integer uid);

    @Select("SELECT SUM(drawCount) FROM drawfrequency WHERE uid = #{uid} AND timeWindow = #{timeWindow}")
    int getCurrentCount(@Param("uid") Integer uid, @Param("timeWindow") String timeWindow);

    @Select("SELECT * FROM drawfrequency WHERE uid = #{uid} ORDER BY createTime DESC LIMIT 1")
    DrawFrequency selectLatestByUid(@Param("uid") Integer uid);

    @Select("SELECT * FROM drawfrequency WHERE uid = #{uid} AND timeWindow = #{timeWindow}")
    DrawFrequency selectByUidAndTimeWindow(@Param("uid") Integer uid, @Param("timeWindow") String timeWindow);

    @Update("UPDATE drawfrequency SET drawCount = drawCount + 1, updateTime = #{updateTime} WHERE fid = #{fid}")
    int incrementDrawCount(@Param("fid") Integer fid, @Param("updateTime") LocalDateTime updateTime);

    @Delete("DELETE FROM drawfrequency WHERE uid = #{uid} AND timeWindow < #{timeWindow}")
    int deleteExpired(@Param("uid") Integer uid, @Param("timeWindow") String timeWindow);
} 