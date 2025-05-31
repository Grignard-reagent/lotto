package com.lab.lotto.service.impl;

import com.lab.lotto.entity.User;
import com.lab.lotto.mapper.UserMapper;
import com.lab.lotto.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional
    public User createUser(User user) {
        user.setUcreateTime(LocalDateTime.now());
        user.setUupdateTime(LocalDateTime.now());
        userMapper.insert(user);
        return user;
    }

    @Override
    @Transactional
    public User updateUser(User user) {
        user.setUupdateTime(LocalDateTime.now());
        userMapper.update(user);
        return user;
    }

    @Override
    @Transactional
    public boolean deleteUser(Integer uid) {
        return userMapper.deleteById(uid) > 0;
    }

    @Override
    public User getUser(Integer uid) {
        return userMapper.findById(uid);
    }

    @Override
    public List<User> getAllUsers() {
        return userMapper.findAll();
    }

    @Override
    public List<User> getUsersByStatus(Integer status) {
        return userMapper.findByStatus(status);
    }

    @Override
    public User getUserByStuid(Integer stuid) {
        return userMapper.findByStuId(stuid);
    }

    @Override
    public User getUserByUname(String uname) {
        return userMapper.findByUname(uname);
    }

    @Override
    public boolean isUserBlacklisted(Integer uid) {
        User user = getUser(uid);
        return user != null && "blacklist".equals(user.getStatus());
    }

    @Override
    public void updateUserStatus(Integer uid, String status) {
        User user = getUser(uid);
        if (user != null) {
            user.setStatus(status);
            updateUser(user);
        }
    }

    @Override
    public boolean hasReachedDrawLimit(Integer uid) {
        User user = getUser(uid);
        return user != null && user.getDrawCount() >= user.getDrawLimit();
    }
}