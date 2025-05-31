package com.lab.lotto.service;

import com.lab.lotto.entity.Blacklist;
import java.util.List;
import java.util.Date;

/**
 * 黑名单服务接口
 */
public interface BlacklistService {

    /**
     * 添加黑名单
     * @param blacklist 黑名单信息
     * @return 添加的黑名单
     */
    Blacklist addBlacklist(Blacklist blacklist);

    /**
     * 更新黑名单
     * @param blacklist 黑名单信息
     * @return 更新后的黑名单
     */
    Blacklist updateBlacklist(Blacklist blacklist);

    /**
     * 删除黑名单
     * @param bid 黑名单ID
     * @return 是否删除成功
     */
    boolean deleteBlacklist(Integer bid);

    /**
     * 获取黑名单
     * @param bid 黑名单ID
     * @return 黑名单信息
     */
    Blacklist getBlacklist(Integer bid);

    /**
     * 获取所有黑名单
     * @return 黑名单列表
     */
    List<Blacklist> getAllBlacklists();

    /**
     * 获取用户的黑名单
     * @param uid 用户ID
     * @return 黑名单列表
     */
    List<Blacklist> getBlacklistsByUser(Integer uid);

    /**
     * 根据类型获取黑名单
     * @param btype 黑名单类型
     * @return 黑名单列表
     */
    List<Blacklist> getBlacklistsByType(String btype);

    /**
     * 检查用户是否在黑名单中
     * @param uid 用户ID
     * @return 是否在黑名单中
     */
    boolean isUserBlacklisted(Integer uid);

    /**
     * 获取过期的黑名单
     * @param currentTime 当前时间
     * @return 黑名单列表
     */
    List<Blacklist> getExpiredBlacklists(Date currentTime);

    /**
     * 创建黑名单
     * @param blacklist 黑名单信息
     * @return 创建的黑名单
     */
    Blacklist createBlacklist(Blacklist blacklist);
}