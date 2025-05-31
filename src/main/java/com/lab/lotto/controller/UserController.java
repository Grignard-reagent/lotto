package com.lab.lotto.controller;

import com.lab.lotto.dto.response.ResponseResult;
import com.lab.lotto.entity.User;
import com.lab.lotto.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户管理控制器
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 创建用户
     * @param user 用户信息
     * @return 创建的用户
     */
    @PostMapping
    public ResponseResult<User> createUser(@RequestBody User user) {
        return ResponseResult.success(userService.createUser(user));
    }

    /**
     * 更新用户
     * @param uid 用户ID
     * @param user 用户信息
     * @return 更新后的用户
     */
    @PutMapping("/{uid}")
    public ResponseResult<User> updateUser(@PathVariable Integer uid, @RequestBody User user) {
        user.setUid(uid);
        return ResponseResult.success(userService.updateUser(user));
    }

    /**
     * 删除用户
     * @param uid 用户ID
     * @return 操作结果
     */
    @DeleteMapping("/{uid}")
    public ResponseResult<Boolean> deleteUser(@PathVariable Integer uid) {
        return ResponseResult.success(userService.deleteUser(uid));
    }

    /**
     * 获取用户
     * @param uid 用户ID
     * @return 用户信息
     */
    @GetMapping("/{uid}")
    public ResponseResult<User> getUser(@PathVariable Integer uid) {
        return ResponseResult.success(userService.getUser(uid));
    }

    /**
     * 获取所有用户
     * @return 用户列表
     */
    @GetMapping
    public ResponseResult<List<User>> getAllUsers() {
        return ResponseResult.success(userService.getAllUsers());
    }

    /**
     * 根据学号获取用户
     * @param stuid 学号
     * @return 用户信息
     */
    @GetMapping("/stuid/{stuid}")
    public ResponseResult<User> getUserByStuid(@PathVariable Integer stuid) {
        return ResponseResult.success(userService.getUserByStuid(stuid));
    }

    /**
     * 根据用户名获取用户
     * @param uname 用户名
     * @return 用户信息
     */
    @GetMapping("/uname/{uname}")
    public ResponseResult<User> getUserByUname(@PathVariable String uname) {
        return ResponseResult.success(userService.getUserByUname(uname));
    }
}