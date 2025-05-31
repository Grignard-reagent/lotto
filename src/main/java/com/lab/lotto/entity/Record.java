package com.lab.lotto.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("record")
public class Record {
    
    @TableId(type = IdType.AUTO)
    private Integer rid;        // 记录ID
    
    private Integer uid;        // 用户ID
    
    private Integer pid;        // 奖品ID
    
    private Byte dtype;         // 抽奖类型（1 普通抽奖，2 活动抽奖）
    
    private Byte dstatus;       // 记录状态（0 未中奖，1 已中奖，2 已领取）
    
    @TableField("create_time")
    private LocalDateTime createTime;    // 创建时间
    
    @TableField("update_time")
    private LocalDateTime updateTime;    // 更新时间
    
    @TableField("draw_time")
    private LocalDateTime drawTime;      // 抽奖时间
    
    @TableField("pname")
    private String pname;               // 奖品名称
    
    @TableField("rstatus")
    private String rstatus;             // 记录状态（字符串类型）
    
    @TableField("rremark")
    private String rremark;             // 备注

    // 关联字段（非数据库字段）
    @TableField(exist = false)
    private User user;             // 关联用户信息
    
    @TableField(exist = false)
    private Prize prize;           // 关联奖品信息

    // Getters and Setters
    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Byte getDtype() {
        return dtype;
    }

    public void setDtype(Byte dtype) {
        this.dtype = dtype;
    }

    public Byte getDstatus() {
        return dstatus;
    }

    public void setDstatus(Byte dstatus) {
        this.dstatus = dstatus;
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

    public LocalDateTime getDrawTime() {
        return drawTime;
    }

    public void setDrawTime(LocalDateTime drawTime) {
        this.drawTime = drawTime;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getRstatus() {
        return rstatus;
    }

    public void setRstatus(String rstatus) {
        this.rstatus = rstatus;
    }

    public String getRremark() {
        return rremark;
    }

    public void setRremark(String rremark) {
        this.rremark = rremark;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Prize getPrize() {
        return prize;
    }

    public void setPrize(Prize prize) {
        this.prize = prize;
    }
}