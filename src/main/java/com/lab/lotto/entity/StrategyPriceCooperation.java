package com.lab.lotto.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;

@TableName("strategypricecooperation")
public class StrategyPriceCooperation {
    @TableId
    private Integer coid;         // 关联ID
    private Integer sid;          // 策略ID
    private Integer pid;          // 奖品ID
    private Double pweight;       // 权重
    private Double pprobability;  // 中奖概率
    @TableField("create_time")
    private LocalDateTime createTime;      // 创建时间
    @TableField("update_time")
    private LocalDateTime updateTime;      // 更新时间
    
    // 非数据库字段
    @TableField(exist = false)
    private Prize prize;          // 关联的奖品

    // Getters and Setters
    public Integer getCoid() {
        return coid;
    }

    public void setCoid(Integer coid) {
        this.coid = coid;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Double getPweight() {
        return pweight;
    }

    public void setPweight(Double pweight) {
        this.pweight = pweight;
    }

    public Double getPprobability() {
        return pprobability;
    }

    public void setPprobability(Double pprobability) {
        this.pprobability = pprobability;
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

    public Prize getPrize() {
        return prize;
    }

    public void setPrize(Prize prize) {
        this.prize = prize;
    }

    public void setCid(Integer coid) {
        this.coid = coid;
    }

    public void setWeight(Double pweight) {
        this.pweight = pweight;
    }

    public void setProbability(Double pprobability) {
        this.pprobability = pprobability;
    }
} 