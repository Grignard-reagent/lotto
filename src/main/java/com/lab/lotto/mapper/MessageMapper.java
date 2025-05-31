package com.lab.lotto.mapper;

import com.lab.lotto.entity.Message;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MessageMapper {
    @Insert("INSERT INTO messagequeue (mtype, mcontent, mstatus, mretry, create_time, update_time) " +
            "VALUES (#{mtype}, #{mcontent}, #{mstatus}, #{mretry}, #{createTime}, #{updateTime})")
    @Options(useGeneratedKeys = true, keyProperty = "mid")
    int insert(Message message);

    @Select("SELECT * FROM messagequeue WHERE mid = #{mid}")
    Message selectById(Long mid);

    @Select("SELECT * FROM messagequeue WHERE mstatus = 0")
    List<Message> selectPendingMessages();

    @Select("SELECT * FROM messagequeue WHERE mstatus = 3 AND mretry < 3 AND next_retry_time <= NOW()")
    List<Message> selectFailedMessages();

    @Update("UPDATE messagequeue SET mstatus = #{mstatus}, mretry = #{mretry}, " +
            "update_time = #{updateTime}, next_retry_time = #{nextRetryTime}, merror = #{merror} " +
            "WHERE mid = #{mid}")
    int updateStatus(Message message);
} 