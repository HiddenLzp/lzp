package com.lzp.demo.controller;

import com.lzp.demo.service.AdminService;
import com.lzp.demo.utils.ResultMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.xml.transform.Result;

/**
 * @authorHmLzp
 * @create 2019 - 05 - 27 22:40
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private AdminService adminServiceImpl;


    @PostMapping("/getAdminInfo")
    public ResultMap queryAdminInfo(String telephone,String passWord){
        return  adminServiceImpl.queryAdminInfo(telephone,passWord);
    }

    @PostMapping("/registerAdmin")
    public ResultMap registerAdmin(String telephone, String email){
        if(StringUtils.isEmpty(telephone) || StringUtils.isEmpty(email)){
            return ResultMap.error(100001,"请填写完整的信息");
        }
        return adminServiceImpl.registerAdmin(telephone,email);
    }

    @PostMapping("/checkmsgCode")
    public ResultMap checkmsgCode(String telephone, String msgCode, String email,String password){
        if(StringUtils.isEmpty(telephone) || StringUtils.isEmpty(msgCode)){
            return ResultMap.error(100007,"请输入验证码！");
        }
        return adminServiceImpl.checkmsgCode(telephone,msgCode,email,password);
    }


    @PostMapping("/forgetPwd")
    public ResultMap forget(String email){
        if(StringUtils.isEmpty(email)){
             return ResultMap.error(10001,"请输入完整的信息");
        }
        return adminServiceImpl.forgetPwd(email);
    }

    @PostMapping("/updatePwd")
    public ResultMap updatePwd(String msgCode,String email,String password){
        if(StringUtils.isEmpty(email)||StringUtils.isEmpty(password)||StringUtils.isEmpty(msgCode)){
            return ResultMap.error(10001,"请输入完整的信息");
        }
        return adminServiceImpl.updatePwd(msgCode,email,password);
    }


}
