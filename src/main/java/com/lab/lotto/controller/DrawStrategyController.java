package com.lab.lotto.controller;

import com.lab.lotto.dto.response.ResponseResult;
import com.lab.lotto.entity.DrawStrategy;
import com.lab.lotto.service.DrawStrategyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/strategies")
public class DrawStrategyController {
    @Autowired
    private DrawStrategyService drawStrategyService;

    @PostMapping
    public ResponseResult<DrawStrategy> createStrategy(@RequestBody DrawStrategy strategy) {
        return ResponseResult.success(drawStrategyService.createStrategy(strategy));
    }

    @PutMapping("/{sid}")
    public ResponseResult<DrawStrategy> updateStrategy(@PathVariable Integer sid, @RequestBody DrawStrategy strategy) {
        strategy.setSid(sid);
        return ResponseResult.success(drawStrategyService.updateStrategy(strategy));
    }

    @DeleteMapping("/{sid}")
    public ResponseResult<Boolean> deleteStrategy(@PathVariable Integer sid) {
        return ResponseResult.success(drawStrategyService.deleteStrategy(sid));
    }

    @GetMapping("/{sid}")
    public ResponseResult<DrawStrategy> getStrategy(@PathVariable Integer sid) {
        return ResponseResult.success(drawStrategyService.getStrategy(sid));
    }

    @GetMapping
    public ResponseResult<List<DrawStrategy>> getAllStrategies() {
        return ResponseResult.success(drawStrategyService.getAllStrategies());
    }

    @GetMapping("/name/{sname}")
    public ResponseResult<DrawStrategy> getStrategyByName(@PathVariable String sname) {
        return ResponseResult.success(drawStrategyService.getStrategyByName(sname));
    }

    @GetMapping("/type/{stype}")
    public ResponseResult<List<DrawStrategy>> getStrategiesByType(@PathVariable Integer stype) {
        return ResponseResult.success(drawStrategyService.getStrategiesByType(stype));
    }
} 