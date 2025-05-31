package com.lab.lotto.dto.request;

import lombok.Data;

@Data
public class RecordRequest {
    private Integer uid;        // 用户ID
    private Integer pid;        // 奖品ID
    private Integer status;     // 状态
    private String remark;      // 备注
} 