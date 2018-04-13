package com.klay.service;

import com.klay.dao.UserMapper;
import com.klay.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImp implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public Integer updateUser(User user) {
        return this.userMapper.updateByPrimaryKey(user);
    }
}
