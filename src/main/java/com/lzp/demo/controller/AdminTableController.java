package com.lzp.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @authorHmLzp
 * @create 2019 - 05 - 27 23:22
 */
@Controller
@RequestMapping("/admin")
public class AdminTableController {

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/")
    public String login(){
        return "login";
    }

    @RequestMapping("/forget")
    public String forget(){ return "forget"; }

    @RequestMapping("/blank")
    public String blank(){ return "index/blank"; }

    @RequestMapping("/calender")
    public String calender(){ return "index/calender"; }

}
