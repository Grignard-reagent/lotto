package com.lab.lotto.dto.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String stuid;      // 学号
    private String password;   // 密码
} 