package com.lab.lotto.controller;

import com.lab.lotto.dto.response.ResponseResult;
import com.lab.lotto.entity.StrategyPriceCooperation;
import com.lab.lotto.service.StrategyPriceCooperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cooperations")
public class StrategyPriceCooperationController {
    @Autowired
    private StrategyPriceCooperationService cooperationService;

    @PostMapping
    public ResponseResult<StrategyPriceCooperation> createCooperation(@RequestBody StrategyPriceCooperation cooperation) {
        return ResponseResult.success(cooperationService.createCooperation(cooperation));
    }

    @PutMapping("/{cid}")
    public ResponseResult<StrategyPriceCooperation> updateCooperation(@PathVariable Integer cid, @RequestBody StrategyPriceCooperation cooperation) {
        cooperation.setCid(cid);
        return ResponseResult.success(cooperationService.updateCooperation(cooperation));
    }

    @DeleteMapping("/{cid}")
    public ResponseResult<Boolean> deleteCooperation(@PathVariable Integer cid) {
        return ResponseResult.success(cooperationService.deleteCooperation(cid));
    }

    @GetMapping("/{cid}")
    public ResponseResult<StrategyPriceCooperation> getCooperation(@PathVariable Integer cid) {
        return ResponseResult.success(cooperationService.getCooperation(cid));
    }

    @GetMapping
    public ResponseResult<List<StrategyPriceCooperation>> getAllCooperations() {
        return ResponseResult.success(cooperationService.getAllCooperations());
    }

    @GetMapping("/strategy/{sid}")
    public ResponseResult<List<StrategyPriceCooperation>> getCooperationsByStrategy(@PathVariable Integer sid) {
        return ResponseResult.success(cooperationService.getCooperationsByStrategy(sid));
    }

    @GetMapping("/prize/{pid}")
    public ResponseResult<List<StrategyPriceCooperation>> getCooperationsByPrize(@PathVariable Integer pid) {
        return ResponseResult.success(cooperationService.getCooperationsByPrize(pid));
    }

    @PutMapping("/{cid}/weight/{weight}")
    public ResponseResult<Boolean> updatePrizeWeight(@PathVariable Integer cid, @PathVariable Double weight) {
        return ResponseResult.success(cooperationService.updatePrizeWeight(cid, weight));
    }

    @PutMapping("/{cid}/probability/{probability}")
    public ResponseResult<Boolean> updatePrizeProbability(@PathVariable Integer cid, @PathVariable Double probability) {
        return ResponseResult.success(cooperationService.updatePrizeProbability(cid, probability));
    }
} 