package com.lab.lotto.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lab.lotto.entity.Prize;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import java.util.List;

@Mapper
public interface PrizeMapper extends BaseMapper<Prize> {
    /**
     * 插入奖品
     * @param prize 奖品信息
     * @return 影响行数
     */
    int insert(Prize prize);

    /**
     * 根据ID更新奖品
     * @param prize 奖品信息
     * @return 影响行数
     */
    int updateById(Prize prize);

    /**
     * 根据ID删除奖品
     * @param pid 奖品ID
     * @return 影响行数
     */
    int deleteById(Integer pid);

    /**
     * 根据ID查询奖品
     * @param pid 奖品ID
     * @return 奖品信息
     */
    Prize selectById(Integer pid);

    /**
     * 查询所有奖品
     * @return 奖品列表
     */
    List<Prize> selectList(Object o);

    /**
     * 根据类型查询奖品
     * @param ptype 奖品类型
     * @return 奖品列表
     */
    List<Prize> selectByType(String ptype);

    /**
     * 根据状态查询奖品
     * @param pstatus 奖品状态
     * @return 奖品列表
     */
    List<Prize> selectByStatus(String pstatus);

    /**
     * 减少库存
     * @param pid 奖品ID
     * @return 影响行数
     */
    int decreaseStock(Integer pid);

    /**
     * 增加中奖次数
     * @param pid 奖品ID
     * @return 影响行数
     */
    @Update("UPDATE prize SET win_count = win_count + 1 WHERE pid = #{pid}")
    int increaseWinCount(Integer pid);

    @Update("UPDATE prize SET pnowQ = ptotalQ, updateTime = NOW() WHERE pnowQ < ptotalQ")
    int updateStatistics();
    
    @Update("UPDATE prize SET pnowQ = pnowQ - 1, updateTime = NOW() WHERE pid = #{id} AND pnowQ > 0")
    int decreaseQuantity(@Param("id") Integer id);
    
    @Update("UPDATE prize SET pnowQ = pnowQ + 1, updateTime = NOW() WHERE pid = #{id}")
    int increaseQuantity(@Param("id") Integer id);
}