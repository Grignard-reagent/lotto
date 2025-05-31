package com.lab.lotto.mapper;

import com.lab.lotto.entity.DrawStrategy;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface DrawStrategyMapper {
    /**
     * 插入抽奖策略
     * @param strategy 策略信息
     * @return 影响行数
     */
    int insert(DrawStrategy strategy);

    /**
     * 更新抽奖策略
     * @param strategy 策略信息
     * @return 影响行数
     */
    int update(DrawStrategy strategy);

    /**
     * 删除抽奖策略
     * @param sid 策略ID
     * @return 影响行数
     */
    int delete(Integer sid);

    /**
     * 根据ID查询抽奖策略
     * @param sid 策略ID
     * @return 策略信息
     */
    DrawStrategy selectById(Integer sid);

    /**
     * 查询所有抽奖策略
     * @return 策略列表
     */
    List<DrawStrategy> selectAll();

    /**
     * 根据名称查询抽奖策略
     * @param sname 策略名称
     * @return 策略信息
     */
    DrawStrategy selectByName(String sname);

    /**
     * 根据类型查询抽奖策略
     * @param stype 策略类型
     * @return 策略列表
     */
    List<DrawStrategy> selectByType(Integer stype);
} 