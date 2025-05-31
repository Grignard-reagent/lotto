package com.lab.lotto.controller;

import com.lab.lotto.dto.response.ResponseResult;
import com.lab.lotto.entity.DrawFrequency;
import com.lab.lotto.service.DrawFrequencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/frequencies")
public class DrawFrequencyController {
    @Autowired
    private DrawFrequencyService frequencyService;

    @PostMapping
    public ResponseResult<DrawFrequency> createFrequency(@RequestBody DrawFrequency frequency) {
        return ResponseResult.success(frequencyService.createFrequency(frequency));
    }

    @PutMapping("/{fid}")
    public ResponseResult<DrawFrequency> updateFrequency(@PathVariable Integer fid, @RequestBody DrawFrequency frequency) {
        frequency.setFid(fid);
        return ResponseResult.success(frequencyService.updateFrequency(frequency));
    }

    @DeleteMapping("/{fid}")
    public ResponseResult<Boolean> deleteFrequency(@PathVariable Integer fid) {
        return ResponseResult.success(frequencyService.deleteFrequency(fid));
    }

    @GetMapping("/{fid}")
    public ResponseResult<DrawFrequency> getFrequency(@PathVariable Integer fid) {
        return ResponseResult.success(frequencyService.getFrequency(fid));
    }

    @GetMapping
    public ResponseResult<List<DrawFrequency>> getAllFrequencies() {
        return ResponseResult.success(frequencyService.getAllFrequencies());
    }

    @GetMapping("/user/{uid}")
    public ResponseResult<List<DrawFrequency>> getFrequenciesByUser(@PathVariable Integer uid) {
        return ResponseResult.success(frequencyService.getFrequenciesByUser(uid));
    }

    @GetMapping("/time-window/{timeWindow}")
    public ResponseResult<List<DrawFrequency>> getFrequenciesByTimeWindow(@PathVariable String timeWindow) {
        return ResponseResult.success(frequencyService.getFrequenciesByTimeWindow(timeWindow));
    }

    @GetMapping("/time-range")
    public ResponseResult<List<DrawFrequency>> getFrequenciesByTimeRange(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startTime,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endTime) {
        return ResponseResult.success(frequencyService.getFrequenciesByTimeRange(startTime, endTime));
    }

    @GetMapping("/check-limit")
    public ResponseResult<Boolean> checkUserLimit(
            @RequestParam Integer uid,
            @RequestParam String timeWindow,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime currentTime) {
        return ResponseResult.success(frequencyService.checkUserLimit(uid, timeWindow, currentTime));
    }
} 