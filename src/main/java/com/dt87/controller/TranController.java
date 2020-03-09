package com.dt87.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/transform")
@Controller
public class TranController {
    @RequestMapping("/index")
    public String index(){
        return "system/index";
    }

    @RequestMapping("/need_login")
    public String tologin(){
        return "system/login";
    }
    @RequestMapping("/toDeskManager")
    public String toDeskManager(){
        return "system/deskManager";
    }


}
