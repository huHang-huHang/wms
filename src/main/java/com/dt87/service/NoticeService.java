package com.dt87.service;

import com.dt87.entity.Notice;

import java.io.Serializable;
import java.util.List;

public interface NoticeService {

    public List<Notice> findNotice(Notice notice);

    public int addNotice(Notice notice);

    public int updateNotice(Notice notice);

    public int deleteNotice(int id);

    public int deleteNotices(List<Serializable> list);
}
