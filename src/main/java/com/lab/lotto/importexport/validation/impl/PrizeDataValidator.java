package com.lab.lotto.importexport.validation.impl;

import com.lab.lotto.importexport.validation.DataValidator;
import com.lab.lotto.importexport.validation.ValidationResult;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.Map;

@Component
public class PrizeDataValidator implements DataValidator<Map<String, String>> {
    
    private static final String[] VALID_PRIZE_TYPES = {"PHYSICAL", "VIRTUAL", "COUPON"};
    private static final String[] VALID_PRIZE_STATUS = {"ACTIVE", "INACTIVE", "DEPLETED"};
    private static final BigDecimal MIN_PROBABILITY = BigDecimal.ZERO;
    private static final BigDecimal MAX_PROBABILITY = BigDecimal.ONE;

    @Override
    public ValidationResult validate(Map<String, String> data) {
        ValidationResult result = new ValidationResult();

        // 验证奖品名称
        String pname = data.get("pname");
        if (!StringUtils.hasText(pname)) {
            result.addError("奖品名称不能为空");
        } else if (pname.length() > 100) {
            result.addError("奖品名称长度不能超过100个字符");
        }

        // 验证奖品类型
        String ptype = data.get("ptype");
        if (!StringUtils.hasText(ptype)) {
            result.addError("奖品类型不能为空");
        } else {
            boolean validType = false;
            for (String type : VALID_PRIZE_TYPES) {
                if (type.equals(ptype)) {
                    validType = true;
                    break;
                }
            }
            if (!validType) {
                result.addError("奖品类型不正确，应为PHYSICAL、VIRTUAL或COUPON");
            }
        }

        // 验证数量
        try {
            int totalQ = Integer.parseInt(data.get("ptotalQ"));
            int nowQ = Integer.parseInt(data.get("pnowQ"));
            
            if (totalQ < 0) {
                result.addError("奖品总数量不能为负数");
            }
            if (nowQ < 0) {
                result.addError("当前可用数量不能为负数");
            }
            if (nowQ > totalQ) {
                result.addError("当前可用数量不能大于总数量");
            }
        } catch (NumberFormatException e) {
            result.addError("数量格式不正确，必须为整数");
        }

        // 验证中奖概率
        try {
            BigDecimal probability = new BigDecimal(data.get("pwinP"));
            if (probability.compareTo(MIN_PROBABILITY) < 0 || probability.compareTo(MAX_PROBABILITY) > 0) {
                result.addError("中奖概率必须在0到1之间");
            }
        } catch (NumberFormatException e) {
            result.addError("中奖概率格式不正确，必须为数字");
        }

        // 验证奖品状态
        String pstatus = data.get("pstatus");
        if (!StringUtils.hasText(pstatus)) {
            result.addError("奖品状态不能为空");
        } else {
            boolean validStatus = false;
            for (String status : VALID_PRIZE_STATUS) {
                if (status.equals(pstatus)) {
                    validStatus = true;
                    break;
                }
            }
            if (!validStatus) {
                result.addError("奖品状态不正确，应为ACTIVE、INACTIVE或DEPLETED");
            }
        }

        // 验证权重（如果存在）
        String pweight = data.get("pweight");
        if (StringUtils.hasText(pweight)) {
            try {
                double weight = Double.parseDouble(pweight);
                if (weight < 0) {
                    result.addError("权重不能为负数");
                }
            } catch (NumberFormatException e) {
                result.addError("权重格式不正确，必须为数字");
            }
        }

        return result;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Map.class.isAssignableFrom(clazz);
    }
} 