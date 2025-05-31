package com.lab.lotto.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("prize")
public class Prize {
    
    @TableId(type = IdType.AUTO)
    private Integer pid;           // 奖品ID
    
    private String pname;          // 奖品名称
    
    private String ptype;          // 奖品类型
    
    private Integer ptotalQ;        // 总数量
    
    private Integer pnowQ;          // 当前数量
    
    private BigDecimal pwinP;        // 中奖概率
    
    private String pstatus;         // 奖品状态
    
    private Double pweight;         // 权重
    
    @TableField("create_time")
    private LocalDateTime pcreateTime; // 创建时间
    
    @TableField("update_time")
    private LocalDateTime pupdateTime; // 更新时间
    
    @TableField("punluckyduck")
    private Byte punluckyduck;     // 是否保底奖品
    
    private String pdesc;           // 奖品描述
    
    private String pimage;          // 奖品图片
}

