package com.lab.lotto.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;

@TableName("blacklist")
public class Blacklist {
    @TableId(type = IdType.AUTO)
    private Integer bid;           // 黑名单ID
    private Integer uid;           // 用户ID
    private String btype;          // 黑名单类型
    private String breason;        // 加入原因
    private Date createTime;       // 创建时间
    private Date expireTime;       // 过期时间

    // Getters and Setters
    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getBtype() {
        return btype;
    }

    public void setBtype(String btype) {
        this.btype = btype;
    }

    public String getBreason() {
        return breason;
    }

    public void setBreason(String breason) {
        this.breason = breason;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }
}