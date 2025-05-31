package com.lab.lotto.dto.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class UserResponse {
    private Integer uid;        // 用户ID
    private String stuid;       // 学号
    private String uname;       // 用户名
    private String remark;      // 备注
    private Integer status;     // 状态
    private LocalDateTime ucreateTime;  // 创建时间
    private LocalDateTime uupdateTime;  // 更新时间
} 