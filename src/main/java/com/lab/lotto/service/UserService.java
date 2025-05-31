package com.lab.lotto.service;

import com.lab.lotto.entity.User;
import java.util.List;

public interface UserService {
    /**
     * 创建用户
     * @param user 用户信息
     * @return 创建的用户
     */
    User createUser(User user);

    /**
     * 更新用户
     * @param user 用户信息
     * @return 更新后的用户
     */
    User updateUser(User user);

    /**
     * 删除用户
     * @param uid 用户ID
     * @return 是否删除成功
     */
    boolean deleteUser(Integer uid);

    /**
     * 获取用户
     * @param uid 用户ID
     * @return 用户信息
     */
    User getUser(Integer uid);

    /**
     * 获取所有用户
     * @return 用户列表
     */
    List<User> getAllUsers();

    /**
     * 根据状态获取用户
     * @param status 状态
     * @return 用户列表
     */
    List<User> getUsersByStatus(Integer status);

    /**
     * 根据学号查询用户
     * @param stuid 学号
     * @return 用户信息
     */
    User getUserByStuid(Integer stuid);

    /**
     * 根据用户名查询用户
     * @param uname 用户名
     * @return 用户信息
     */
    User getUserByUname(String uname);

    /**
     * 检查用户是否在黑名单中
     * @param uid 用户ID
     * @return 是否在黑名单中
     */
    boolean isUserBlacklisted(Integer uid);

    /**
     * 更新用户状态
     * @param uid 用户ID
     * @param status 状态
     */
    void updateUserStatus(Integer uid, String status);

    /**
     * 检查用户是否达到抽奖限制
     * @param uid 用户ID
     * @return 是否达到限制
     */
    boolean hasReachedDrawLimit(Integer uid);
}