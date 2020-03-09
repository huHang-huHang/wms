package com.dt87.controller;

import com.dt87.entity.JsonData;
import com.dt87.entity.Notice;
import com.dt87.entity.User;
import com.dt87.service.impl.NoticeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/notice")
public class NoticeController {
    @Autowired
    private NoticeServiceImpl noticeService;

    @RequestMapping("/loadAllNotice")
    @ResponseBody
    public JsonData loadAllNotice(Notice notice){
        return JsonData.buildSuccess(noticeService.findNotice(notice),0);
    }

    /**
     * @author xyh
     * @param notice
     * @param session
     * @return
     */
    @RequestMapping("/addNotice")
    @ResponseBody
    public JsonData addNotice(Notice notice, HttpSession session){
        notice.setCreatetime(new Date());
        User user = (User)session.getAttribute("user");
        notice.setOpername(user.getName());
        try {
            int count = noticeService.addNotice(notice);
            if(count > 0){
                return JsonData.buildSuccess(null,"添加成功");
            }else{
                return JsonData.buildError("添加失败",-1);
            }
        }catch (Exception e){
            return JsonData.buildError("添加失败",-1);
        }

    }

    /**
     * 修改
     */
    @RequestMapping("/updateNotice")
    @ResponseBody
    public JsonData updateNotice(Notice notice){
        if(notice.getTitle() != null && notice.getContent() != null && !notice.getTitle().equals("")&&!notice.getContent().equals("")){
            try {
                noticeService.updateNotice(notice);
                return JsonData.buildSuccess(null,"修改成功");
            }catch (Exception e){
                return JsonData.buildError("修改失败",-1);
            }

        }else{
            return JsonData.buildError("标题或内容不能为空",-1);
        }

    }

    /**
     * 删除
     */
    @ResponseBody
    @RequestMapping("/deleteNotice")
    public JsonData deleteNotice(int id){

        try {
            noticeService.deleteNotice(id);
            return JsonData.buildSuccess(null,0);
        }catch (Exception e){
            return JsonData.buildError("删除失败",-1);
        }

    }

    /**
     * 批量删除
     */
    @ResponseBody
    @RequestMapping("/batchDeleteNotice")
    public JsonData batchDeleteNotice(int[] ids){
        List<Serializable> list = new ArrayList<Serializable>();
        for (int i = 0; i<ids.length; i++){
            list.add(ids[i]);
        }
        try{
            noticeService.deleteNotices(list);
            return JsonData.buildSuccess(null,"批量删除成功");
        }catch (Exception e){
            return JsonData.buildError("批量删除",-1);
        }
    }
}
