package com.lab.lotto.entity;

import java.util.Date;

public class DrawRecord {
    private Integer rid;           // 记录ID
    private Integer uid;           // 用户ID
    private Integer pid;           // 奖品ID
    private String pname;          // 奖品名称
    private Integer dtype;         // 抽奖类型
    private Date drawTime;         // 抽奖时间
    private String rstatus;        // 状态
    private String rremark;        // 备注

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

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public Integer getDtype() {
        return dtype;
    }

    public void setDtype(Integer dtype) {
        this.dtype = dtype;
    }

    public Date getDrawTime() {
        return drawTime;
    }

    public void setDrawTime(Date drawTime) {
        this.drawTime = drawTime;
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
} 