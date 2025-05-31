package com.lab.lotto.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lab.lotto.entity.MessageProcessLog;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface MessageProcessLogMapper extends BaseMapper<MessageProcessLog> {
    
    @Select("SELECT * FROM messageprocesslog WHERE mid = #{mid} ORDER BY process_time DESC")
    List<MessageProcessLog> selectByMessageId(@Param("mid") Long mid);
    
    @Select("SELECT * FROM messageprocesslog WHERE lstatus = 0 AND process_time < #{expireTime}")
    List<MessageProcessLog> selectFailedLogs(@Param("expireTime") LocalDateTime expireTime);
    
    @Update("DELETE FROM messageprocesslog WHERE process_time < #{expireTime}")
    int deleteExpired(@Param("expireTime") LocalDateTime expireTime);
    
    @Update("UPDATE messageprocesslog SET lstatus = #{status}, lresult = #{result}, lerror = #{error}, process_time = #{processTime} WHERE lid = #{lid}")
    int updateLog(@Param("lid") Long lid, @Param("status") Byte status, @Param("result") String result, 
                 @Param("error") String error, @Param("processTime") LocalDateTime processTime);
} 