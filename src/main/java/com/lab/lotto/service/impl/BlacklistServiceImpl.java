package com.lab.lotto.service.impl;

import com.lab.lotto.entity.Blacklist;
import com.lab.lotto.mapper.BlacklistMapper;
import com.lab.lotto.service.BlacklistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class BlacklistServiceImpl implements BlacklistService {

    @Autowired
    private BlacklistMapper blacklistMapper;

    @Override
    @Transactional
    public Blacklist addBlacklist(Blacklist blacklist) {
        blacklistMapper.insert(blacklist);
        return blacklist;
    }

    @Override
    @Transactional
    public Blacklist updateBlacklist(Blacklist blacklist) {
        blacklistMapper.updateById(blacklist);
        return blacklist;
    }

    @Override
    @Transactional
    public boolean deleteBlacklist(Integer bid) {
        return blacklistMapper.deleteById(bid) > 0;
    }

    @Override
    public Blacklist getBlacklist(Integer bid) {
        return blacklistMapper.selectById(bid);
    }

    @Override
    public List<Blacklist> getAllBlacklists() {
        return blacklistMapper.selectList(null);
    }

    @Override
    public List<Blacklist> getBlacklistsByUser(Integer uid) {
        return blacklistMapper.selectByUser(uid);
    }

    @Override
    public List<Blacklist> getBlacklistsByType(String btype) {
        return blacklistMapper.selectByType(btype);
    }

    @Override
    public boolean isUserBlacklisted(Integer uid) {
        return blacklistMapper.selectByUser(uid).size() > 0;
    }

    @Override
    public List<Blacklist> getExpiredBlacklists(Date currentTime) {
        return blacklistMapper.selectExpired(currentTime);
    }

    @Override
    public Blacklist createBlacklist(Blacklist blacklist) {
        blacklistMapper.insert(blacklist);
        return blacklist;
    }
}