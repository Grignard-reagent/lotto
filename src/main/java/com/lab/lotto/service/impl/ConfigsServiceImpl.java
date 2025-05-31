package com.lab.lotto.service.impl;

import com.lab.lotto.entity.Configs;
import com.lab.lotto.mapper.ConfigsMapper;
import com.lab.lotto.service.ConfigsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ConfigsServiceImpl implements ConfigsService {

    @Autowired
    private ConfigsMapper configsMapper;

    @Override
    @Transactional
    public Configs createConfigs(Configs configs) {
        configs.setCcreateTime(LocalDateTime.now());
        configs.setCupdateTime(LocalDateTime.now());
        configsMapper.insert(configs);
        return configs;
    }

    @Override
    @Transactional
    public Configs updateConfigs(Configs configs) {
        configs.setCupdateTime(LocalDateTime.now());
        configsMapper.update(configs);
        return configs;
    }

    @Override
    @Transactional
    public boolean deleteConfigs(Integer cid) {
        return configsMapper.delete(cid) > 0;
    }

    @Override
    public Configs getConfigs(Integer cid) {
        return configsMapper.selectById(cid);
    }

    @Override
    public List<Configs> getAllConfigs() {
        return configsMapper.selectAll();
    }

    @Override
    public Configs getConfigsByName(String cname) {
        return configsMapper.selectByName(cname);
    }

    @Override
    public List<Configs> getConfigsByStatus(Integer status) {
        return configsMapper.selectByStatus(status);
    }

    @Override
    @Transactional
    public boolean updateConfigsStatus(Integer cid, Integer status) {
        return configsMapper.updateStatus(cid, status) > 0;
    }
}