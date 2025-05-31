package com.lab.lotto.entity;

import java.util.Date;

public class ActivityConfig {
    private Integer acid;          // 活动配置ID
    private Integer aid;           // 活动ID
    private String ackey;          // 配置键
    private String acvalue;        // 配置值
    private String acdescription;  // 配置描述
    private Date acreateTime;      // 创建时间
    private Date aupdateTime;      // 更新时间

    // Getters and Setters
    public Integer getAcid() {
        return acid;
    }

    public void setAcid(Integer acid) {
        this.acid = acid;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public String getAckey() {
        return ackey;
    }

    public void setAckey(String ackey) {
        this.ackey = ackey;
    }

    public String getAcvalue() {
        return acvalue;
    }

    public void setAcvalue(String acvalue) {
        this.acvalue = acvalue;
    }

    public String getAcdescription() {
        return acdescription;
    }

    public void setAcdescription(String acdescription) {
        this.acdescription = acdescription;
    }

    public Date getAcreateTime() {
        return acreateTime;
    }

    public void setAcreateTime(Date acreateTime) {
        this.acreateTime = acreateTime;
    }

    public Date getAupdateTime() {
        return aupdateTime;
    }

    public void setAupdateTime(Date aupdateTime) {
        this.aupdateTime = aupdateTime;
    }
} 