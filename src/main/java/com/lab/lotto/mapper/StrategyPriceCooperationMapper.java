package com.lab.lotto.mapper;

import com.lab.lotto.entity.StrategyPriceCooperation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface StrategyPriceCooperationMapper {
    /**
     * 插入策略奖品关联
     * @param cooperation 关联信息
     * @return 影响行数
     */
    int insert(StrategyPriceCooperation cooperation);

    /**
     * 更新策略奖品关联
     * @param cooperation 关联信息
     * @return 影响行数
     */
    int update(StrategyPriceCooperation cooperation);

    /**
     * 删除策略奖品关联
     * @param spcid 关联ID
     * @return 影响行数
     */
    int delete(Integer spcid);

    /**
     * 根据ID查询策略奖品关联
     * @param spcid 关联ID
     * @return 关联信息
     */
    StrategyPriceCooperation selectById(Integer spcid);

    /**
     * 查询所有策略奖品关联
     * @return 关联列表
     */
    List<StrategyPriceCooperation> selectAll();

    /**
     * 根据策略ID查询关联
     * @param strategyId 策略ID
     * @return 关联列表
     */
    @Select("SELECT * FROM strategyprize_cooperation WHERE sid = #{strategyId}")
    List<StrategyPriceCooperation> selectByStrategyId(@Param("strategyId") Integer strategyId);

    /**
     * 根据奖品ID查询关联
     * @param prizeId 奖品ID
     * @return 关联列表
     */
    List<StrategyPriceCooperation> selectByPrizeId(Integer prizeId);
} 