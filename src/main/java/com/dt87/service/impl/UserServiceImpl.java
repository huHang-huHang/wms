package com.dt87.service.impl;

import com.dt87.entity.Role;
import com.dt87.entity.User;
import com.dt87.mapper.PermissionMapper;
import com.dt87.mapper.RoleMapper;
import com.dt87.mapper.UserMapper;
import com.dt87.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private PermissionMapper permissionMapper;
    @Override
    public User findByName(String username) {

        User user = userMapper.findByName(username);
        List<Role> roles = roleMapper.findRoleByName(user.getId());
        for (Role r:roles) {
            r.setPermissionList(permissionMapper.findPermissionById(r.getId()));
        }
        user.setRoleList(roles);
        return user;
    }

    @Override
    public User login(String name, String pwd) {
       return userMapper.login(name, pwd);
    }
}
