package com.lab.lotto.controller;

import com.lab.lotto.dto.response.ResponseResult;
import com.lab.lotto.entity.Prize;
import com.lab.lotto.service.PrizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prizes")
public class PrizeController {

    @Autowired
    private PrizeService prizeService;

    /**
     * 创建奖品
     * @param prize 奖品信息
     * @return 创建的奖品
     */
    @PostMapping
    public ResponseResult<Prize> createPrize(@RequestBody Prize prize) {
        return ResponseResult.success(prizeService.createPrize(prize));
    }

    /**
     * 更新奖品
     * @param pid 奖品ID
     * @param prize 奖品信息
     * @return 更新后的奖品
     */
    @PutMapping("/{pid}")
    public ResponseResult<Prize> updatePrize(@PathVariable Integer pid, @RequestBody Prize prize) {
        prize.setPid(pid);
        return ResponseResult.success(prizeService.updatePrize(prize));
    }

    /**
     * 删除奖品
     * @param pid 奖品ID
     * @return 操作结果
     */
    @DeleteMapping("/{pid}")
    public ResponseResult<Boolean> deletePrize(@PathVariable Integer pid) {
        return ResponseResult.success(prizeService.deletePrize(pid));
    }

    /**
     * 获取奖品
     * @param pid 奖品ID
     * @return 奖品信息
     */
    @GetMapping("/{pid}")
    public ResponseResult<Prize> getPrize(@PathVariable Integer pid) {
        return ResponseResult.success(prizeService.getPrize(pid));
    }

    /**
     * 获取所有奖品
     * @return 奖品列表
     */
    @GetMapping
    public ResponseResult<List<Prize>> getAllPrizes() {
        return ResponseResult.success(prizeService.getAllPrizes());
    }

    /**
     * 根据类型获取奖品
     * @param ptype 类型
     * @return 奖品列表
     */
    @GetMapping("/type/{ptype}")
    public ResponseResult<List<Prize>> getPrizesByType(@PathVariable Integer ptype) {
        return ResponseResult.success(prizeService.getPrizesByType(ptype));
    }

    /**
     * 根据状态获取奖品
     * @param pstatus 状态
     * @return 奖品列表
     */
    @GetMapping("/status/{pstatus}")
    public ResponseResult<List<Prize>> getPrizesByStatus(@PathVariable Integer pstatus) {
        return ResponseResult.success(prizeService.getPrizesByStatus(pstatus));
    }

    /**
     * 扣减库存
     * @param pid 奖品ID
     * @return 操作结果
     */
    @PostMapping("/{pid}/decrease-stock")
    public ResponseResult<Boolean> decreaseStock(@PathVariable Integer pid) {
        return ResponseResult.success(prizeService.decreaseStock(pid));
    }

    /**
     * 增加中奖数量
     * @param pid 奖品ID
     * @return 操作结果
     */
    @PostMapping("/{pid}/increase-win-count")
    public ResponseResult<Boolean> increaseWinCount(@PathVariable Integer pid) {
        return ResponseResult.success(prizeService.increaseWinCount(pid));
    }
}