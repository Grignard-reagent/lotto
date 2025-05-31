package com.lab.lotto.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("messageprocesslog")
public class MessageProcessLog {
    @TableId(type = IdType.AUTO)
    private Long lid;           // 日志ID
    private Long mid;           // 消息ID
    private Byte lstatus;       // 处理状态（0 失败，1 成功）
    @TableField("process_time")
    private LocalDateTime processTime;   // 处理时间
    private String lresult;     // 处理结果
    private String lerror;      // 错误信息

    // Getters and Setters
    public Long getLid() {
        return lid;
    }

    public void setLid(Long lid) {
        this.lid = lid;
    }

    public Long getMid() {
        return mid;
    }

    public void setMid(Long mid) {
        this.mid = mid;
    }

    public Byte getLstatus() {
        return lstatus;
    }

    public void setLstatus(Byte lstatus) {
        this.lstatus = lstatus;
    }

    public LocalDateTime getProcessTime() {
        return processTime;
    }

    public void setProcessTime(LocalDateTime processTime) {
        this.processTime = processTime;
    }

    public String getLresult() {
        return lresult;
    }

    public void setLresult(String lresult) {
        this.lresult = lresult;
    }

    public String getLerror() {
        return lerror;
    }

    public void setLerror(String lerror) {
        this.lerror = lerror;
    }
} 