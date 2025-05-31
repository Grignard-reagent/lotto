package com.lab.lotto.dto.request;

import lombok.Data;

@Data
public class UserRequest {
    private String stuid;       // 学号
    private String uname;       // 用户名
    private String password;    // 密码
    private String remark;      // 备注
} 