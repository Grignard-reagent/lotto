package com.lab.lotto.dto.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class PrizeResponse {
    private Integer pid;        // 奖品ID
    private String pname;       // 奖品名称
    private String pdesc;       // 奖品描述
    private Integer pquantity;  // 奖品数量
    private Integer status;     // 状态
    private String remark;      // 备注
    private LocalDateTime pcreateTime;  // 创建时间
    private LocalDateTime pupdateTime;  // 更新时间
} 