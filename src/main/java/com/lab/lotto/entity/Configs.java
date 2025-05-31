package com.lab.lotto.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;

@TableName("configs")
public class Configs {
    @TableId
    private Integer cid;           // 配置ID
    private String cname;          // 配置名称
    private String cdetails;       // 配置详情
    private Integer cstatus;       // 配置状态：0-禁用，1-启用
    @TableField("create_time")
    private LocalDateTime ccreateTime;      // 创建时间
    @TableField("update_time")
    private LocalDateTime cupdateTime;      // 更新时间
    private Integer dlimitQ;       // 单时间窗口最大抽奖次数
    private String dleast;         // 保底奖触发条件

    // Getters and Setters
    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCdetails() {
        return cdetails;
    }

    public void setCdetails(String cdetails) {
        this.cdetails = cdetails;
    }

    public Integer getCstatus() {
        return cstatus;
    }

    public void setCstatus(Integer cstatus) {
        this.cstatus = cstatus;
    }

    public LocalDateTime getCcreateTime() {
        return ccreateTime;
    }

    public void setCcreateTime(LocalDateTime ccreateTime) {
        this.ccreateTime = ccreateTime;
    }

    public LocalDateTime getCupdateTime() {
        return cupdateTime;
    }

    public void setCupdateTime(LocalDateTime cupdateTime) {
        this.cupdateTime = cupdateTime;
    }

    public Integer getDlimitQ() {
        return dlimitQ;
    }

    public void setDlimitQ(Integer dlimitQ) {
        this.dlimitQ = dlimitQ;
    }

    public String getDleast() {
        return dleast;
    }

    public void setDleast(String dleast) {
        this.dleast = dleast;
    }
}