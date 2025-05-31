package com.lab.lotto.importexport.validation.impl;

import com.lab.lotto.importexport.validation.DataValidator;
import com.lab.lotto.importexport.validation.ValidationResult;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Map;

@Component
public class SystemConfigValidator implements DataValidator<Map<String, String>> {
    
    private static final int[] VALID_CONFIG_TYPES = {1, 2, 3}; // 1-系统配置，2-业务配置，3-其他配置
    private static final int[] VALID_CONFIG_STATUS = {0, 1}; // 0-禁用，1-启用

    @Override
    public ValidationResult validate(Map<String, String> data) {
        ValidationResult result = new ValidationResult();

        // 验证配置键
        String ckey = data.get("ckey");
        if (!StringUtils.hasText(ckey)) {
            result.addError("配置键不能为空");
        } else if (ckey.length() > 50) {
            result.addError("配置键长度不能超过50个字符");
        }

        // 验证配置值
        String cvalue = data.get("cvalue");
        if (!StringUtils.hasText(cvalue)) {
            result.addError("配置值不能为空");
        } else if (cvalue.length() > 500) {
            result.addError("配置值长度不能超过500个字符");
        }

        // 验证配置类型
        String ctype = data.get("ctype");
        if (!StringUtils.hasText(ctype)) {
            result.addError("配置类型不能为空");
        } else {
            try {
                int type = Integer.parseInt(ctype);
                boolean validType = false;
                for (int t : VALID_CONFIG_TYPES) {
                    if (t == type) {
                        validType = true;
                        break;
                    }
                }
                if (!validType) {
                    result.addError("配置类型不正确，应为1(系统配置)、2(业务配置)或3(其他配置)");
                }
            } catch (NumberFormatException e) {
                result.addError("配置类型格式不正确，必须为整数");
            }
        }

        // 验证配置状态
        String cstatus = data.get("cstatus");
        if (!StringUtils.hasText(cstatus)) {
            result.addError("配置状态不能为空");
        } else {
            try {
                int status = Integer.parseInt(cstatus);
                boolean validStatus = false;
                for (int s : VALID_CONFIG_STATUS) {
                    if (s == status) {
                        validStatus = true;
                        break;
                    }
                }
                if (!validStatus) {
                    result.addError("配置状态不正确，应为0(禁用)或1(启用)");
                }
            } catch (NumberFormatException e) {
                result.addError("配置状态格式不正确，必须为整数");
            }
        }

        return result;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Map.class.isAssignableFrom(clazz);
    }
} 