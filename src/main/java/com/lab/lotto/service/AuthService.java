package com.lab.lotto.service;

import com.lab.lotto.dto.request.LoginRequest;
import com.lab.lotto.dto.response.LoginResponse;

public interface AuthService {
    /**
     * 用户登录
     * @param request 登录请求
     * @return 登录响应
     */
    LoginResponse login(LoginRequest request);
} 