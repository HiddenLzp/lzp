package com.lzp.demo.controller;

import com.lzp.demo.service.AdminService;
import com.lzp.demo.utils.ResultMap;
import org.springframework.util.StringUtils;
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

    @PostMapping("/registerAdmin")
    public ResultMap registerAdmin(String telephone, String email,String password){
        if(StringUtils.isEmpty(telephone) || StringUtils.isEmpty(email) || StringUtils.isEmpty(password)){
            return ResultMap.error(100001,"请填写完整的信息");
        }
        return adminService.registerAdmin(telephone,email,password);
    }

    @PostMapping("/checkmsgCode")
    public ResultMap checkmsgCode(String telephone, String msgCode){
        if(StringUtils.isEmpty(telephone) || StringUtils.isEmpty(msgCode)){
            return ResultMap.error(100007,"请输入验证码！");
        }
        return adminService.checkmsgCode(telephone,msgCode);
    }



}
