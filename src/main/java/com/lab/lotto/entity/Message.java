package com.lab.lotto.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("message")
public class Message {
    @TableId(type = IdType.AUTO)
    private Long mid;           // 消息ID
    
    private String mtype;       // 消息类型
    
    private String mcontent;    // 消息内容
    
    private Byte mstatus;       // 状态：0-待处理，1-处理中，2-处理成功，3-处理失败
    
    private Integer mretry;     // 重试次数
    
    @TableField("create_time")
    private LocalDateTime createTime;    // 创建时间
    
    @TableField("update_time")
    private LocalDateTime updateTime;    // 更新时间
    
    @TableField("next_retry_time")
    private LocalDateTime nextRetryTime; // 下次重试时间
    
    private String merror;      // 错误信息
} 