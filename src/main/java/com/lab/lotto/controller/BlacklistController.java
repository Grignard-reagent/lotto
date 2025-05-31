package com.lab.lotto.controller;

import com.lab.lotto.dto.response.ResponseResult;
import com.lab.lotto.entity.Blacklist;
import com.lab.lotto.service.BlacklistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 黑名单管理控制器
 */
@RestController
@RequestMapping("/api/blacklists")
public class BlacklistController {

    @Autowired
    private BlacklistService blacklistService;

    /**
     * 创建黑名单
     * @param blacklist 黑名单信息
     * @return 创建的黑名单
     */
    @PostMapping
    public ResponseResult<Blacklist> createBlacklist(@RequestBody Blacklist blacklist) {
        return ResponseResult.success(blacklistService.createBlacklist(blacklist));
    }

    /**
     * 更新黑名单
     * @param bid 黑名单ID
     * @param blacklist 黑名单信息
     * @return 更新后的黑名单
     */
    @PutMapping("/{bid}")
    public ResponseResult<Blacklist> updateBlacklist(@PathVariable Integer bid, @RequestBody Blacklist blacklist) {
        blacklist.setBid(bid);
        return ResponseResult.success(blacklistService.updateBlacklist(blacklist));
    }

    /**
     * 删除黑名单
     * @param bid 黑名单ID
     * @return 操作结果
     */
    @DeleteMapping("/{bid}")
    public ResponseResult<Boolean> deleteBlacklist(@PathVariable Integer bid) {
        return ResponseResult.success(blacklistService.deleteBlacklist(bid));
    }

    /**
     * 获取黑名单
     * @param bid 黑名单ID
     * @return 黑名单信息
     */
    @GetMapping("/{bid}")
    public ResponseResult<Blacklist> getBlacklist(@PathVariable Integer bid) {
        return ResponseResult.success(blacklistService.getBlacklist(bid));
    }

    /**
     * 获取所有黑名单
     * @return 黑名单列表
     */
    @GetMapping
    public ResponseResult<List<Blacklist>> getAllBlacklists() {
        return ResponseResult.success(blacklistService.getAllBlacklists());
    }

    /**
     * 根据用户获取黑名单
     * @param uid 用户ID
     * @return 黑名单列表
     */
    @GetMapping("/user/{uid}")
    public ResponseResult<List<Blacklist>> getBlacklistsByUser(@PathVariable Integer uid) {
        return ResponseResult.success(blacklistService.getBlacklistsByUser(uid));
    }

    /**
     * 根据类型获取黑名单
     * @param btype 类型
     * @return 黑名单列表
     */
    @GetMapping("/type/{btype}")
    public ResponseResult<List<Blacklist>> getBlacklistsByType(@PathVariable String btype) {
        return ResponseResult.success(blacklistService.getBlacklistsByType(btype));
    }

    /**
     * 获取过期黑名单
     * @param currentTime 当前时间
     * @return 过期黑名单列表
     */
    @GetMapping("/expired")
    public ResponseResult<List<Blacklist>> getExpiredBlacklists(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date currentTime) {
        return ResponseResult.success(blacklistService.getExpiredBlacklists(currentTime));
    }
}