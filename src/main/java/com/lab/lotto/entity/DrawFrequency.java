package com.lab.lotto.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("drawfrequency")
public class DrawFrequency {
    
    @TableId(type = IdType.AUTO)
    private Integer fid;           // 频率记录ID
    
    private Integer uid;           // 用户ID
    
    private LocalDateTime drawTime;         // 抽奖时间
    
    private Integer drawCount;     // 抽奖次数
    
    private String timeWindow;     // 时间窗口标识
    
    private LocalDateTime createTime;       // 创建时间
    
    private LocalDateTime updateTime;       // 更新时间

    // Getters and Setters
    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public LocalDateTime getDrawTime() {
        return drawTime;
    }

    public void setDrawTime(LocalDateTime drawTime) {
        this.drawTime = drawTime;
    }

    public Integer getDrawCount() {
        return drawCount;
    }

    public void setDrawCount(Integer drawCount) {
        this.drawCount = drawCount;
    }

    public Integer getFcount() {
        return drawCount;
    }

    public Integer getFlimit() {
        return 0; // 如有 limit 字段请替换
    }

    public String getTimeWindow() {
        return timeWindow;
    }

    public void setTimeWindow(String timeWindow) {
        this.timeWindow = timeWindow;
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
} 