package com.lab.lotto.service.impl;

import com.lab.lotto.dto.request.LoginRequest;
import com.lab.lotto.dto.response.LoginResponse;
import com.lab.lotto.entity.User;
import com.lab.lotto.service.AuthService;
import com.lab.lotto.service.UserService;
import com.lab.lotto.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public LoginResponse login(LoginRequest request) {
        // 根据学号查找用户
        User user = userService.getUserByStuid(Integer.parseInt(request.getStuid()));
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }

        // 验证密码
        if (!passwordEncoder.matches(request.getPassword(), user.getUpassword())) {
            throw new RuntimeException("密码错误");
        }

        // 生成token
        String token = jwtUtil.generateToken(request.getStuid(), user.getUrole());

        // 构建响应
        LoginResponse response = new LoginResponse();
        response.setToken(token);
        response.setUname(user.getUname());
        response.setUrole(user.getUrole());

        return response;
    }
} 