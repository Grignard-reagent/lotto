package com.lab.lotto.controller;

import com.lab.lotto.dto.response.ResponseResult;
import com.lab.lotto.entity.Configs;
import com.lab.lotto.service.ConfigsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/configs")
public class ConfigsController {
    @Autowired
    private ConfigsService configsService;

    @PostMapping
    public ResponseResult<Configs> createConfigs(@RequestBody Configs configs) {
        return ResponseResult.success(configsService.createConfigs(configs));
    }

    @PutMapping("/{cid}")
    public ResponseResult<Configs> updateConfigs(@PathVariable Integer cid, @RequestBody Configs configs) {
        configs.setCid(cid);
        return ResponseResult.success(configsService.updateConfigs(configs));
    }

    @DeleteMapping("/{cid}")
    public ResponseResult<Boolean> deleteConfigs(@PathVariable Integer cid) {
        return ResponseResult.success(configsService.deleteConfigs(cid));
    }

    @GetMapping("/{cid}")
    public ResponseResult<Configs> getConfigs(@PathVariable Integer cid) {
        return ResponseResult.success(configsService.getConfigs(cid));
    }

    @GetMapping
    public ResponseResult<List<Configs>> getAllConfigs() {
        return ResponseResult.success(configsService.getAllConfigs());
    }

    @GetMapping("/name/{cname}")
    public ResponseResult<Configs> getConfigsByName(@PathVariable String cname) {
        return ResponseResult.success(configsService.getConfigsByName(cname));
    }

    @GetMapping("/status/{cstatus}")
    public ResponseResult<List<Configs>> getConfigsByStatus(@PathVariable Integer cstatus) {
        return ResponseResult.success(configsService.getConfigsByStatus(cstatus));
    }

    @PutMapping("/{cid}/status/{cstatus}")
    public ResponseResult<Boolean> updateConfigsStatus(@PathVariable Integer cid, @PathVariable Integer cstatus) {
        return ResponseResult.success(configsService.updateConfigsStatus(cid, cstatus));
    }
}