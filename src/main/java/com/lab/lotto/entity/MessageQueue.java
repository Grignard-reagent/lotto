package com.lab.lotto.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("messagequeue")
public class MessageQueue {
    
    @TableId(type = IdType.AUTO)
    private Long mid;           // 消息ID
    
    private String mtype;       // 消息类型
    
    private String mcontent;    // 消息内容
    
    private Byte mstatus;       // 状态（0 待处理，1 处理中，2 处理成功，3 处理失败）
    
    private Integer mretry;     // 重试次数
    
    @TableField("create_time")
    private LocalDateTime createTime;    // 创建时间
    
    @TableField("update_time")
    private LocalDateTime updateTime;    // 更新时间
    
    @TableField("next_retry_time")
    private LocalDateTime nextRetryTime; // 下次重试时间
    
    private String merror;      // 错误信息

    // Getters and Setters
    public Long getMid() {
        return mid;
    }

    public void setMid(Long mid) {
        this.mid = mid;
    }

    public String getMtype() {
        return mtype;
    }

    public void setMtype(String mtype) {
        this.mtype = mtype;
    }

    public String getMcontent() {
        return mcontent;
    }

    public void setMcontent(String mcontent) {
        this.mcontent = mcontent;
    }

    public Byte getMstatus() {
        return mstatus;
    }

    public void setMstatus(Byte mstatus) {
        this.mstatus = mstatus;
    }

    public Integer getMretry() {
        return mretry;
    }

    public void setMretry(Integer mretry) {
        this.mretry = mretry;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public LocalDateTime getNextRetryTime() {
        return nextRetryTime;
    }

    public void setNextRetryTime(LocalDateTime nextRetryTime) {
        this.nextRetryTime = nextRetryTime;
    }

    public String getMerror() {
        return merror;
    }

    public void setMerror(String merror) {
        this.merror = merror;
    }
} 