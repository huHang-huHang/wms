package com.dt87.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/system")
@Controller
public class SystemController {

    //系统公告的跳转
    @RequestMapping("/toNoticeManager")
    public String noticeMenu(){

        return "system/notice/noticeManager";
    }
    //系统公告跳部门管理
    @RequestMapping("/toDeptManager")
    public String  departmentMenu(){
        return  "system/department/departmentManager";
    }
}
