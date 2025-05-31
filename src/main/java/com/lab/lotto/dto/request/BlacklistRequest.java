package com.lab.lotto.dto.request;

import lombok.Data;

@Data
public class BlacklistRequest {
    private Integer uid;        // 用户ID
    private String reason;      // 原因
    private String remark;      // 备注
} 