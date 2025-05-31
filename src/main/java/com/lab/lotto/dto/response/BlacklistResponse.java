package com.lab.lotto.dto.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class BlacklistResponse {
    private Integer bid;        // 黑名单ID
    private Integer uid;        // 用户ID
    private String reason;      // 原因
    private String remark;      // 备注
    private LocalDateTime bcreateTime;  // 创建时间
    private LocalDateTime bupdateTime;  // 更新时间
} 