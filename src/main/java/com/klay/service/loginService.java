package com.klay.service;

import com.klay.model.User;

public interface loginService {
    public User findUserByPrimaryKey(String id);
}
