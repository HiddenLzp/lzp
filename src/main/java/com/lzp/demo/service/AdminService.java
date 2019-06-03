package com.lzp.demo.service;

import com.lzp.demo.service.serviceModel.AdminModel;
import com.lzp.demo.utils.ResultMap;

public interface AdminService {
    /**
     *  查询用户信息，条件: 手机号，密码
     */
    ResultMap queryAdminInfo(String telephone, String passWord);

    /**
     * 用户注册
     */
    ResultMap registerAdmin(String telephone, String email);

    /**
     * 验证验证码，修改用户的状态
     */
    ResultMap checkmsgCode(String telephone, String msgCode, String email, String password);


    ResultMap forgetPwd(String email);

    ResultMap updatePwd(String msgCode,String email,String password);
}
