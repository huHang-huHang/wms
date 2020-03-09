package com.dt87.service;

import com.dt87.entity.User;

public interface UserService {

    //根据用户名查询角色和权限
    public User findByName(String username);

    public User login(String name, String pwd);
}
