package com.lab.lotto.importexport.validation.impl;

import com.lab.lotto.importexport.validation.DataValidator;
import com.lab.lotto.importexport.validation.ValidationResult;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Map;

@Component
public class RecordDataValidator implements DataValidator<Map<String, String>> {
    
    private static final int[] VALID_DRAW_TYPES = {1, 2}; // 1-普通抽奖，2-活动抽奖
    private static final int[] VALID_DRAW_STATUS = {0, 1, 2}; // 0-未中奖，1-已中奖，2-已领取

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

        // 验证奖品ID
        String pid = data.get("pid");
        if (!StringUtils.hasText(pid)) {
            result.addError("奖品ID不能为空");
        } else {
            try {
                int prizeId = Integer.parseInt(pid);
                if (prizeId <= 0) {
                    result.addError("奖品ID必须大于0");
                }
            } catch (NumberFormatException e) {
                result.addError("奖品ID格式不正确，必须为整数");
            }
        }

        // 验证抽奖类型
        String dtype = data.get("dtype");
        if (!StringUtils.hasText(dtype)) {
            result.addError("抽奖类型不能为空");
        } else {
            try {
                int drawType = Integer.parseInt(dtype);
                boolean validType = false;
                for (int type : VALID_DRAW_TYPES) {
                    if (type == drawType) {
                        validType = true;
                        break;
                    }
                }
                if (!validType) {
                    result.addError("抽奖类型不正确，应为1(普通抽奖)或2(活动抽奖)");
                }
            } catch (NumberFormatException e) {
                result.addError("抽奖类型格式不正确，必须为整数");
            }
        }

        // 验证记录状态
        String dstatus = data.get("dstatus");
        if (!StringUtils.hasText(dstatus)) {
            result.addError("记录状态不能为空");
        } else {
            try {
                int status = Integer.parseInt(dstatus);
                boolean validStatus = false;
                for (int s : VALID_DRAW_STATUS) {
                    if (s == status) {
                        validStatus = true;
                        break;
                    }
                }
                if (!validStatus) {
                    result.addError("记录状态不正确，应为0(未中奖)、1(已中奖)或2(已领取)");
                }
            } catch (NumberFormatException e) {
                result.addError("记录状态格式不正确，必须为整数");
            }
        }

        return result;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Map.class.isAssignableFrom(clazz);
    }
} 