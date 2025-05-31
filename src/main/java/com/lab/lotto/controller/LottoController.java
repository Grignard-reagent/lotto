package com.lab.lotto.controller;

import com.lab.lotto.dto.response.ResponseResult;
import com.lab.lotto.entity.Prize;
import com.lab.lotto.entity.Record;
import com.lab.lotto.entity.User;
import com.lab.lotto.service.LottoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/lotto")
public class LottoController {

    @Autowired
    private LottoService lottoService;

    /**
     * 抽奖
     * @param uid 用户ID
     * @return 中奖记录
     */
    @PostMapping("/draw/{uid}")
    public ResponseResult<Record> draw(@PathVariable Integer uid) {
        return ResponseResult.success(lottoService.draw(uid));
    }

    /**
     * 检查用户是否可以抽奖
     * @param uid 用户ID
     * @return 是否可以抽奖
     */
    @GetMapping("/check/{uid}")
    public ResponseResult<Boolean> checkDrawable(@PathVariable Integer uid) {
        return ResponseResult.success(lottoService.checkDrawable(uid));
    }

    /**
     * 获取用户中奖记录
     * @param uid 用户ID
     * @return 中奖记录
     */
    @GetMapping("/record/{uid}")
    public ResponseResult<Record> getDrawRecord(@PathVariable Integer uid) {
        return ResponseResult.success(lottoService.getDrawRecord(uid));
    }

    /**
     * 获取中奖奖品
     * @param rid 记录ID
     * @return 奖品信息
     */
    @GetMapping("/prize/{rid}")
    public ResponseResult<Prize> getPrize(@PathVariable Integer rid) {
        return ResponseResult.success(lottoService.getPrize(rid));
    }

    /**
     * 获取中奖用户
     * @param rid 记录ID
     * @return 用户信息
     */
    @GetMapping("/user/{rid}")
    public ResponseResult<User> getUser(@PathVariable Integer rid) {
        return ResponseResult.success(lottoService.getUser(rid));
    }
} 