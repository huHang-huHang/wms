package com.dt87.mapper;

import com.dt87.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
public interface UserMapper {

    //根据用户名查寻角色
    public User findByName(@Param("name") String name);

    //登录
    public User login(@Param("username") String username, @Param("password") String password);

}
