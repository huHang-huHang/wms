package com.dt87.service.impl;

import com.dt87.entity.Notice;
import com.dt87.mapper.NoticeMapper;
import com.dt87.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
@Service
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    private NoticeMapper noticeMapper;
    @Override
    public List<Notice> findNotice(Notice notice) {
        return noticeMapper.findNotice(notice);
    }

    @Override
    public int addNotice(Notice notice) {
        return noticeMapper.addNotice(notice);
    }

    @Override
    public int updateNotice(Notice notice) {
        return noticeMapper.updateNotice(notice);
    }

    @Override
    public int deleteNotice(int id) {
        return noticeMapper.deleteNotice(id);
    }

    @Override
    @Transactional
    public int deleteNotices(List<Serializable> list) {
        return noticeMapper.deleteNotices(list);
    }
}
