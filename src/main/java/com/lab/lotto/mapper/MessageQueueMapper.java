package com.lab.lotto.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lab.lotto.entity.MessageQueue;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface MessageQueueMapper extends BaseMapper<MessageQueue> {
    
    @Select("SELECT * FROM messagequeue WHERE mstatus = 0 AND next_retry_time <= #{currentTime}")
    List<MessageQueue> selectPendingMessages(@Param("currentTime") LocalDateTime currentTime);
    
    @Select("SELECT * FROM messagequeue WHERE mstatus = 3 AND mretry < 3 AND next_retry_time <= #{currentTime}")
    List<MessageQueue> selectRetryMessages(@Param("currentTime") LocalDateTime currentTime);
    
    @Update("DELETE FROM messagequeue WHERE create_time < #{expireTime}")
    int deleteExpired(@Param("expireTime") LocalDateTime expireTime);
    
    @Update("UPDATE messagequeue SET mstatus = #{status}, update_time = #{updateTime} WHERE mid = #{mid}")
    int updateStatus(@Param("mid") Long mid, @Param("status") Byte status, @Param("updateTime") LocalDateTime updateTime);
    
    @Update("UPDATE messagequeue SET mretry = mretry + 1, next_retry_time = #{nextRetryTime}, update_time = #{updateTime} WHERE mid = #{mid}")
    int updateRetry(@Param("mid") Long mid, @Param("nextRetryTime") LocalDateTime nextRetryTime, @Param("updateTime") LocalDateTime updateTime);
} 