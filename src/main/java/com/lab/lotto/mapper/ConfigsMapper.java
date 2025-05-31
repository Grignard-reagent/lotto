package com.lab.lotto.mapper;

import com.lab.lotto.entity.Configs;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface ConfigsMapper {
    /**
     * 插入配置
     * @param config 配置信息
     * @return 影响行数
     */
    @Insert("INSERT INTO lottery_configs (cname, cdetails, cstatus, ccreate_time, cupdate_time) " +
            "VALUES (#{cname}, #{cdetails}, #{cstatus}, #{ccreateTime}, #{cupdateTime})")
    @Options(useGeneratedKeys = true, keyProperty = "cid")
    int insert(Configs config);

    /**
     * 更新配置
     * @param config 配置信息
     * @return 影响行数
     */
    @Update("UPDATE lottery_configs SET cname = #{cname}, cdetails = #{cdetails}, " +
            "cstatus = #{cstatus}, cupdate_time = #{cupdateTime} WHERE cid = #{cid}")
    int update(Configs config);

    /**
     * 根据ID删除配置
     * @param cid 配置ID
     * @return 影响行数
     */
    @Delete("DELETE FROM lottery_configs WHERE cid = #{cid}")
    int deleteById(@Param("cid") Integer cid);

    /**
     * 根据ID查询配置
     * @param cid 配置ID
     * @return 配置信息
     */
    @Select("SELECT * FROM lottery_configs WHERE cid = #{cid}")
    Configs findById(@Param("cid") Integer cid);

    /**
     * 查询所有配置
     * @return 配置列表
     */
    @Select("SELECT * FROM lottery_configs")
    List<Configs> findAll();

    /**
     * 根据状态查询配置
     * @param status 状态
     * @return 配置列表
     */
    @Select("SELECT * FROM lottery_configs WHERE cstatus = #{status}")
    List<Configs> findByStatus(@Param("status") Integer status);

    /**
     * 更新配置状态
     * @param cid 配置ID
     * @param cstatus 状态
     * @return 影响行数
     */
    @Update("UPDATE lottery_configs SET cstatus = #{cstatus}, cupdate_time = NOW() WHERE cid = #{cid}")
    int updateStatus(@Param("cid") Integer cid, @Param("cstatus") Integer cstatus);

    @Select("SELECT * FROM lottery_configs WHERE cname = #{cname}")
    Configs findByName(String cname);

    /**
     * 根据ID查询配置
     * @param cid 配置ID
     * @return 配置信息
     */
    Configs selectById(@Param("cid") Integer cid);

    /**
     * 根据名称查询配置
     * @param cname 配置名称
     * @return 配置信息
     */
    Configs selectByName(@Param("cname") String cname);

    /**
     * 根据状态查询配置
     * @param cstatus 配置状态
     * @return 配置列表
     */
    List<Configs> selectByStatus(@Param("cstatus") Integer cstatus);

    /**
     * 查询所有配置
     * @return 配置列表
     */
    List<Configs> selectAll();

    /**
     * 删除配置
     * @param cid 配置ID
     * @return 影响行数
     */
    int delete(@Param("cid") Integer cid);

    @Select("SELECT * FROM configs WHERE cname = #{cname}")
    Configs selectByCname(String cname);
}