package com.lzp.demo.controller;

import com.lzp.demo.service.AdminService;
import com.lzp.demo.utils.ResultMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @authorHmLzp
 * @create 2019 - 05 - 27 22:40
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private AdminService adminService;


    @PostMapping("/getAdminInfo")
    public ResultMap queryAdminInfo(String telephone,String passWord){
        return  adminService.queryAdminInfo(telephone,passWord);
    }

}
