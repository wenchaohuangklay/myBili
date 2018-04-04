package com.klay.service;

import com.klay.dao.UserMapper;
import com.klay.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("loginService")
public class loginServiceImp implements loginService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findUserByPrimaryKey(String id) {
        return userMapper.selectByPrimaryKey(id);
    }
}
