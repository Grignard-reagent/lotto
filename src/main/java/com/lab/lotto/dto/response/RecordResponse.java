package com.lab.lotto.dto.response;

import lombok.Data;
import java.util.Date;

@Data
public class RecordResponse {
    private Integer rid;           // 记录ID
    private Integer uid;           // 用户ID
    private String uname;          // 用户名称
    private Integer pid;           // 奖品ID
    private String pname;          // 奖品名称
    private String ptype;          // 奖品类型
    private Integer dtype;         // 抽奖类型
    private String dtypeDesc;      // 抽奖类型描述
    private Date drawTime;         // 抽奖时间
    private String rstatus;        // 状态
    private String rremark;        // 备注
    private Date createTime;       // 创建时间
    private Date updateTime;       // 更新时间
} 