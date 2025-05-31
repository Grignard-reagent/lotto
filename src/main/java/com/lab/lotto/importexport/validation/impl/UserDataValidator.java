package com.lab.lotto.importexport.validation.impl;

import com.lab.lotto.importexport.validation.DataValidator;
import com.lab.lotto.importexport.validation.ValidationResult;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.regex.Pattern;

@Component
public class UserDataValidator implements DataValidator<Map<String, String>> {
    
    private static final Pattern STUDENT_ID_PATTERN = Pattern.compile("^\\d{8,12}$");
    private static final Pattern USERNAME_PATTERN = Pattern.compile("^[a-zA-Z0-9_\\u4e00-\\u9fa5]{2,50}$");
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d@$!%*#?&]{8,}$");
    private static final String[] VALID_ROLES = {"ADMIN", "USER"};

    @Override
    public ValidationResult validate(Map<String, String> data) {
        ValidationResult result = new ValidationResult();

        // 验证学号
        String stuid = data.get("stuid");
        if (!StringUtils.hasText(stuid)) {
            result.addError("学号不能为空");
        } else if (!STUDENT_ID_PATTERN.matcher(stuid).matches()) {
            result.addError("学号格式不正确，应为8-12位数字");
        }

        // 验证用户名
        String uname = data.get("uname");
        if (!StringUtils.hasText(uname)) {
            result.addError("用户名不能为空");
        } else if (!USERNAME_PATTERN.matcher(uname).matches()) {
            result.addError("用户名格式不正确，应为2-50位字母、数字、下划线或中文");
        }

        // 验证密码
        String upassword = data.get("upassword");
        if (!StringUtils.hasText(upassword)) {
            result.addError("密码不能为空");
        } else if (!PASSWORD_PATTERN.matcher(upassword).matches()) {
            result.addError("密码格式不正确，至少8位，必须包含字母和数字");
        }

        // 验证角色
        String urole = data.get("urole");
        if (!StringUtils.hasText(urole)) {
            result.addError("角色不能为空");
        } else {
            boolean validRole = false;
            for (String role : VALID_ROLES) {
                if (role.equals(urole)) {
                    validRole = true;
                    break;
                }
            }
            if (!validRole) {
                result.addError("角色值不正确，应为ADMIN或USER");
            }
        }

        return result;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Map.class.isAssignableFrom(clazz);
    }
} 