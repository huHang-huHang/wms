package com.dt87.mapper;

import com.dt87.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
public interface RoleMapper {

    public List<Role> findRoleByName(@Param("id") int id);


}
