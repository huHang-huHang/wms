package com.dt87.mapper;

import com.dt87.entity.Notice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
@Mapper
public interface NoticeMapper {

    //查询所有
    public List<Notice> findNotice(@Param("notice") Notice notice);

    //添加
    public int addNotice(@Param("notice") Notice notice);

    //修改
    public int updateNotice(@Param("notice") Notice notice);

    //删除
    public int deleteNotice(@Param("id") int id);

    //批量删除
    public int deleteNotices(@Param("list") List<Serializable> list);
}
