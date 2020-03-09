package com.dt87.config;

import com.dt87.entity.Permission;
import com.dt87.entity.Role;
import com.dt87.entity.User;
import com.dt87.service.impl.UserServiceImpl;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CustomerFealm extends AuthorizingRealm {
    @Autowired
    private UserServiceImpl userService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("授权操作");
        //根据用户信息查询角色和角色对应的权限
        Set<String> roles = new HashSet<>();
        Set<String> permissions = new HashSet<>();

        String username = (String) principalCollection.getPrimaryPrincipal();
        System.out.println("************************"+username);
        List<Role> ro = userService.findByName(username).getRoleList();
        for(Role r:ro){
            roles.add(r.getName());
            for(Permission p:r.getPermissionList()){
                permissions.add(p.getPercode());
            }
        }
        //这表里面system 超级管理员确实没有权限
        System.out.println("aaaaaaaaaaaaaaaaaaaa"+ro.toString());
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setStringPermissions(permissions);
        simpleAuthorizationInfo.setRoles(roles);
        System.out.println("授权结束");
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("认证操作");
        //根据用户名获取用户的信息
        String username = (String)authenticationToken.getPrincipal();
        User user = userService.findByName(username);
        if(user == null){
            return null;
        }
        System.out.println("认证结束");
        return new SimpleAuthenticationInfo(username,user.getPwd(),this.getClass().getName());
    }
}
