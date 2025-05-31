package com.lab.lotto.dto.response;

import lombok.Data;

@Data
public class LoginResponse {
    private String token;      // JWT令牌
    private String uname;      // 用户名
    private String urole;      // 用户角色
} 