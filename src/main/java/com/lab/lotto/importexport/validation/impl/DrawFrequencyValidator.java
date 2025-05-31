package com.lab.lotto.importexport.validation.impl;

import com.lab.lotto.importexport.validation.DataValidator;
import com.lab.lotto.importexport.validation.ValidationResult;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Component
public class DrawFrequencyValidator implements DataValidator<Map<String, String>> {
    
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public ValidationResult validate(Map<String, String> data) {
        ValidationResult result = new ValidationResult();

        // 验证用户ID
        String uid = data.get("uid");
        if (!StringUtils.hasText(uid)) {
            result.addError("用户ID不能为空");
        } else {
            try {
                int userId = Integer.parseInt(uid);
                if (userId <= 0) {
                    result.addError("用户ID必须大于0");
                }
            } catch (NumberFormatException e) {
                result.addError("用户ID格式不正确，必须为整数");
            }
        }

        // 验证抽奖时间
        String drawTime = data.get("drawTime");
        if (!StringUtils.hasText(drawTime)) {
            result.addError("抽奖时间不能为空");
        } else {
            try {
                LocalDateTime time = LocalDateTime.parse(drawTime, DATE_TIME_FORMATTER);
                if (time.isAfter(LocalDateTime.now())) {
                    result.addError("抽奖时间不能超过当前时间");
                }
            } catch (Exception e) {
                result.addError("抽奖时间格式不正确，应为yyyy-MM-dd HH:mm:ss");
            }
        }

        // 验证抽奖次数
        String drawCount = data.get("drawCount");
        if (!StringUtils.hasText(drawCount)) {
            result.addError("抽奖次数不能为空");
        } else {
            try {
                int count = Integer.parseInt(drawCount);
                if (count < 0) {
                    result.addError("抽奖次数不能为负数");
                }
            } catch (NumberFormatException e) {
                result.addError("抽奖次数格式不正确，必须为整数");
            }
        }

        // 验证时间窗口
        String timeWindow = data.get("timeWindow");
        if (!StringUtils.hasText(timeWindow)) {
            result.addError("时间窗口不能为空");
        } else if (timeWindow.length() > 10) {
            result.addError("时间窗口长度不能超过10个字符");
        }

        return result;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Map.class.isAssignableFrom(clazz);
    }
} 