package com.lab.lotto.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lab.lotto.entity.Blacklist;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Mapper
public interface BlacklistMapper extends BaseMapper<Blacklist> {

    /**
     * 插入黑名单
     * @param blacklist 黑名单信息
     * @return 影响行数
     */
    @Insert("INSERT INTO blacklist(uid, breason, create_time) VALUES(#{uid}, #{breason}, #{createTime})")
    @Options(useGeneratedKeys = true, keyProperty = "bid")
    int insert(Blacklist blacklist);

    /**
     * 根据ID更新黑名单
     * @param blacklist 黑名单信息
     * @return 影响行数
     */
    @Update("UPDATE blacklist SET uid = #{uid}, breason = #{breason} WHERE bid = #{bid}")
    int updateById(Blacklist blacklist);

    /**
     * 根据ID删除黑名单
     * @param bid 黑名单ID
     * @return 影响行数
     */
    @Delete("DELETE FROM blacklist WHERE bid = #{bid}")
    int deleteById(@Param("bid") Integer bid);

    /**
     * 根据ID查询黑名单
     * @param bid 黑名单ID
     * @return 黑名单信息
     */
    @Select("SELECT * FROM blacklist WHERE bid = #{bid}")
    Blacklist selectById(@Param("bid") Integer bid);

    /**
     * 查询所有黑名单
     * @return 黑名单列表
     */
    @Select("SELECT * FROM blacklist")
    List<Blacklist> selectList(Object o);

    /**
     * 根据用户查询黑名单
     * @param uid 用户ID
     * @return 黑名单列表
     */
    @Select("SELECT * FROM blacklist WHERE uid = #{uid}")
    List<Blacklist> selectByUser(@Param("uid") Integer uid);

    /**
     * 根据类型查询黑名单
     * @param btype 黑名单类型
     * @return 黑名单列表
     */
    @Select("SELECT * FROM blacklist WHERE btype = #{btype}")
    List<Blacklist> selectByType(@Param("btype") String btype);

    /**
     * 查询过期黑名单
     * @param currentTime 当前时间
     * @return 黑名单列表
     */
    @Select("SELECT * FROM blacklist WHERE create_time < #{currentTime}")
    List<Blacklist> selectExpired(@Param("currentTime") Date currentTime);

    /**
     * 根据用户查询黑名单
     * @param uid 用户ID
     * @return 黑名单信息
     */
    @Select("SELECT * FROM blacklist WHERE uid = #{uid} AND (expireTime IS NULL OR expireTime > NOW())")
    Blacklist getByUid(@Param("uid") Integer uid);

    /**
     * 删除过期黑名单
     * @param expireTime 过期时间
     * @return 影响行数
     */
    @Update("DELETE FROM blacklist WHERE expireTime < #{expireTime}")
    int deleteExpired(@Param("expireTime") LocalDateTime expireTime);
    
    /**
     * 更新黑名单信息
     * @param id 黑名单ID
     * @param type 类型
     * @param reason 原因
     * @param expireTime 过期时间
     * @return 影响行数
     */
    @Update("UPDATE blacklist SET btype = #{type}, breason = #{reason}, expireTime = #{expireTime} WHERE bid = #{id}")
    int updateBlacklist(@Param("id") Integer id, @Param("type") String type, 
                       @Param("reason") String reason, @Param("expireTime") LocalDateTime expireTime);
}