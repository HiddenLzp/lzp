package com.lzp.demo.controller;

import com.lzp.demo.dao.jpa.AdminDao;
import com.lzp.demo.model.Admin;
import com.lzp.demo.service.AdminService;
import com.lzp.demo.utils.ErrorCode;
import com.lzp.demo.utils.ErrorMessage;
import com.lzp.demo.utils.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
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

    @Autowired
    private AdminDao adminDao;


    /**
     * 登录
     * @param telephone 手机号
     * @param passWord 密码
     * @return 登录者信息
     */
    @RequestMapping("/getAdminInfo")
    public ResultMap queryAdminInfo(String telephone,String passWord){
        return  adminServiceImpl.queryAdminInfo(telephone,passWord);
    }

    @PostMapping("/registerAdmin")
    public ResultMap registerAdmin(String telephone, String email){
        if(StringUtils.isEmpty(telephone) || StringUtils.isEmpty(email)){
            return ResultMap.error(ErrorCode.INFO_iS_FAIL,ErrorMessage.INFO_iS_FAIL);
        }
        return adminServiceImpl.registerAdmin(telephone,email);
    }

    @PostMapping("/checkmsgCode")
    public ResultMap checkmsgCode(String telephone, String msgCode, String email,String password){
        if(StringUtils.isEmpty(telephone) || StringUtils.isEmpty(msgCode)){
            return ResultMap.error(ErrorCode.INFO_iS_FAIL,ErrorMessage.INFO_iS_FAIL);
        }
        return adminServiceImpl.checkmsgCode(telephone,msgCode,email,password);
    }


    @PostMapping("/forgetPwd")
    public ResultMap forget(String email){
        if(StringUtils.isEmpty(email)){
             return ResultMap.error(ErrorCode.INFO_iS_FAIL,ErrorMessage.INFO_iS_FAIL);
        }
        return adminServiceImpl.forgetPwd(email);
    }

    @PostMapping("/updatePwd")
    public ResultMap updatePwd(String msgCode,String email,String password){
        if(StringUtils.isEmpty(email)||StringUtils.isEmpty(password)||StringUtils.isEmpty(msgCode)){
            return ResultMap.error(ErrorCode.INFO_iS_FAIL, ErrorMessage.INFO_iS_FAIL);
        }
        return adminServiceImpl.updatePwd(msgCode,email,password);
    }

    @RequestMapping("/test")
    public Admin test(){

        return adminDao.findById(1).get();
    }


}
