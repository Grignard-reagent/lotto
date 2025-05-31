package com.lab.lotto.utils;

import com.lab.lotto.entity.User;
import com.lab.lotto.entity.Prize;
import com.lab.lotto.service.UserService;
import com.lab.lotto.service.DrawFrequencyService;
import com.lab.lotto.service.ConfigsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DrawValidationUtil {
    @Autowired
    private UserService userService;

    @Autowired
    private DrawFrequencyService drawFrequencyService;

    @Autowired
    private ConfigsService configsService;

    /**
     * 验证用户是否可以抽奖
     * @param uid 用户ID
     * @return 验证结果
     */
    public ValidationResult validateUser(Integer uid) {
        // 检查用户是否存在
        User user = userService.getUser(uid);
        if (user == null) {
            return new ValidationResult(false, "用户不存在");
        }

        // 检查用户是否在黑名单中
        if (userService.isUserBlacklisted(uid)) {
            return new ValidationResult(false, "用户在黑名单中");
        }

        // 检查用户抽奖次数是否达到限制
        int drawCount = drawFrequencyService.getCurrentDrawCount(uid);
        var config = configsService.getConfigsByName("draw_limit");
        if (config != null && drawCount >= config.getDlimitQ()) {
            return new ValidationResult(false, "已达到抽奖次数限制");
        }

        return new ValidationResult(true, "验证通过");
    }

    /**
     * 验证奖品是否可用
     * @param prize 奖品
     * @return 验证结果
     */
    public ValidationResult validatePrize(Prize prize) {
        if (prize == null) {
            return new ValidationResult(false, "奖品不存在");
        }

        // 检查奖品状态
        if (!"1".equals(prize.getPstatus())) {
            return new ValidationResult(false, "奖品已下架");
        }

        // 检查奖品库存
        if (prize.getPnowQ() <= 0) {
            return new ValidationResult(false, "奖品库存不足");
        }

        return new ValidationResult(true, "验证通过");
    }

    /**
     * 验证结果类
     */
    public static class ValidationResult {
        private final boolean valid;
        private final String message;

        public ValidationResult(boolean valid, String message) {
            this.valid = valid;
            this.message = message;
        }

        public boolean isValid() {
            return valid;
        }

        public String getMessage() {
            return message;
        }
    }
} 