package com.lab.lotto.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("user")
public class User {
    @TableId
    private Integer uid;           // 用户ID
    private String uname;          // 用户名
    private Integer stuid;         // 学号
    private String upassword;      // 密码
    private String urole;          // 角色
    private String status;         // 状态
    private Integer drawCount;     // 抽奖次数
    private Integer drawLimit;     // 抽奖限制
    
    @TableField("create_time")
    private LocalDateTime ucreateTime;      // 创建时间
    
    @TableField("update_time")
    private LocalDateTime uupdateTime;      // 更新时间

    // Getters and Setters
    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public Integer getStuid() {
        return stuid;
    }

    public void setStuid(Integer stuid) {
        this.stuid = stuid;
    }

    public String getUpassword() {
        return upassword;
    }

    public void setUpassword(String upassword) {
        this.upassword = upassword;
    }

    public String getUrole() {
        return urole;
    }

    public void setUrole(String urole) {
        this.urole = urole;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getDrawCount() {
        return drawCount;
    }

    public void setDrawCount(Integer drawCount) {
        this.drawCount = drawCount;
    }

    public Integer getDrawLimit() {
        return drawLimit;
    }

    public void setDrawLimit(Integer drawLimit) {
        this.drawLimit = drawLimit;
    }

    public LocalDateTime getUcreateTime() {
        return ucreateTime;
    }

    public void setUcreateTime(LocalDateTime ucreateTime) {
        this.ucreateTime = ucreateTime;
    }

    public LocalDateTime getUupdateTime() {
        return uupdateTime;
    }

    public void setUupdateTime(LocalDateTime uupdateTime) {
        this.uupdateTime = uupdateTime;
    }
}