package com.lab.lotto.dto.request;

import lombok.Data;

@Data
public class PrizeRequest {
    private String pname;       // 奖品名称
    private String pdesc;       // 奖品描述
    private Integer pquantity;  // 奖品数量
    private Integer status;     // 状态
    private String remark;      // 备注
} 