package com.lab.lotto.service;

import com.lab.lotto.entity.Prize;
import java.util.List;

public interface PrizeService {
    /**
     * 创建奖品
     * @param prize 奖品信息
     * @return 奖品信息
     */
    Prize createPrize(Prize prize);

    /**
     * 更新奖品
     * @param prize 奖品信息
     * @return 更新后的奖品信息
     */
    Prize updatePrize(Prize prize);

    /**
     * 删除奖品
     * @param pid 奖品ID
     * @return 是否删除成功
     */
    boolean deletePrize(Integer pid);

    /**
     * 查询奖品
     * @param pid 奖品ID
     * @return 奖品信息
     */
    Prize getPrize(Integer pid);

    /**
     * 获取所有奖品
     * @return 奖品列表
     */
    List<Prize> getAllPrizes();

    /**
     * 根据状态获取奖品
     * @param status 状态
     * @return 奖品列表
     */
    List<Prize> getPrizesByStatus(Integer status);

    /**
     * 更新库存
     * @param pid 奖品ID
     * @param quantity 数量变化（正数增加，负数减少）
     */
    void updateStock(Integer pid, int quantity);

    /**
     * 扣减库存
     * @param pid 奖品ID
     * @return 是否扣减成功
     */
    boolean decreaseStock(Integer pid);

    /**
     * 增加中奖数量
     * @param pid 奖品ID
     * @return 是否增加成功
     */
    boolean increaseWinCount(Integer pid);

    /**
     * 根据类型获取奖品
     * @param ptype 类型
     * @return 奖品列表
     */
    List<Prize> getPrizesByType(Integer ptype);
}