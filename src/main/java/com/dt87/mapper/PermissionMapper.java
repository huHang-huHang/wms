package com.dt87.mapper;

import com.dt87.entity.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface PermissionMapper {

    public List<Permission> findPermissionById(@Param("id") int id);

    //加载目录
    public List<Permission> loadMunu();
}
