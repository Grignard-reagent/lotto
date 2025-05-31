package com.lab.lotto.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lab.lotto.entity.Record;
import com.lab.lotto.dto.request.RecordQueryRequest;
import org.apache.ibatis.annotations.*;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface RecordMapper extends BaseMapper<Record> {
    
    @Select("SELECT * FROM record WHERE uid = #{uid}")
    List<Record> selectByUser(@Param("uid") Integer uid);
    
    @Select("SELECT * FROM record WHERE pid = #{pid}")
    List<Record> selectByPrize(@Param("pid") Integer pid);
    
    @Select("SELECT * FROM record WHERE dtype = #{type}")
    List<Record> selectByType(@Param("type") Byte type);
    
    @Select("SELECT * FROM record WHERE create_time BETWEEN #{startTime} AND #{endTime}")
    List<Record> selectByTimeRange(@Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime);
    
    @Select("SELECT * FROM record WHERE dstatus = #{status}")
    List<Record> selectByStatus(@Param("status") String status);
    
    @Select("SELECT * FROM record WHERE uid = #{uid}")
    List<Record> selectByUserId(@Param("uid") Integer uid);
    
    @Select("SELECT * FROM record WHERE pid = #{pid}")
    List<Record> selectByPrizeId(@Param("pid") Integer pid);
    
    @Select("<script>" +
            "SELECT * FROM record " +
            "<where>" +
            "<if test='request.uid != null'>AND uid = #{request.uid}</if>" +
            "<if test='request.pid != null'>AND pid = #{request.pid}</if>" +
            "<if test='request.type != null'>AND dtype = #{request.type}</if>" +
            "<if test='request.status != null'>AND dstatus = #{request.status}</if>" +
            "<if test='request.startTime != null'>AND create_time &gt;= #{request.startTime}</if>" +
            "<if test='request.endTime != null'>AND create_time &lt;= #{request.endTime}</if>" +
            "</where>" +
            "ORDER BY create_time DESC" +
            "</script>")
    IPage<Record> selectByCondition(Page<Record> page, @Param("request") RecordQueryRequest request);
    
    @Select("<script>" +
            "SELECT COUNT(*) FROM record " +
            "<where>" +
            "<if test='request.uid != null'>AND uid = #{request.uid}</if>" +
            "<if test='request.pid != null'>AND pid = #{request.pid}</if>" +
            "<if test='request.type != null'>AND dtype = #{request.type}</if>" +
            "<if test='request.status != null'>AND dstatus = #{request.status}</if>" +
            "<if test='request.startTime != null'>AND create_time &gt;= #{request.startTime}</if>" +
            "<if test='request.endTime != null'>AND create_time &lt;= #{request.endTime}</if>" +
            "</where>" +
            "</script>")
    int countByCondition(@Param("request") RecordQueryRequest request);
    
    @Select("SELECT * FROM record WHERE uid = #{uid} AND dtype = #{dtype} AND dstatus = #{dstatus}")
    List<Record> selectByUidAndTypeAndStatus(@Param("uid") Integer uid, @Param("dtype") Integer dtype, @Param("dstatus") Integer dstatus);
    
    @Select("SELECT * FROM record WHERE pid = #{pid} AND dstatus = #{dstatus}")
    List<Record> selectByPrizeAndStatus(@Param("pid") Integer pid, @Param("dstatus") Integer dstatus);
    
    @Select("SELECT * FROM record WHERE uid = #{uid} AND createTime BETWEEN #{startTime} AND #{endTime}")
    List<Record> selectByUidAndTimeRange(@Param("uid") Integer uid, @Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime);
    
    @Select("SELECT COUNT(*) FROM record WHERE uid = #{uid} AND dstatus = #{dstatus}")
    int countByUidAndStatus(@Param("uid") Integer uid, @Param("dstatus") Integer dstatus);
    
    @Select("SELECT COUNT(*) FROM record WHERE pid = #{pid} AND dstatus = #{dstatus}")
    int countByPrizeAndStatus(@Param("pid") Integer pid, @Param("dstatus") Integer dstatus);
    
    @Update("UPDATE record SET dstatus = #{dstatus}, updateTime = #{updateTime} WHERE rid = #{rid}")
    int updateStatus(@Param("rid") Integer rid, @Param("dstatus") Integer dstatus, @Param("updateTime") LocalDateTime updateTime);
    
    @Delete("DELETE FROM record WHERE uid = #{uid} AND createTime < #{time}")
    int deleteExpired(@Param("uid") Integer uid, @Param("time") LocalDateTime time);
    
    @Select("SELECT * FROM record WHERE uid = #{uid} AND pid = #{pid} AND dstatus = 1 LIMIT 1")
    Record selectWinningRecord(@Param("uid") Integer uid, @Param("pid") Integer pid);
}