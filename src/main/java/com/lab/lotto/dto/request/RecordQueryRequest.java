package com.lab.lotto.dto.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RecordQueryRequest {
    private Integer uid;           // 用户ID
    private Integer pid;           // 奖品ID
    private Byte dtype;           // 抽奖类型
    private Byte dstatus;         // 记录状态
    private LocalDateTime startTime;    // 开始时间
    private LocalDateTime endTime;      // 结束时间
    private Integer pageNum = 1;   // 页码
    private Integer pageSize = 10; // 每页大小
} 