package com.lzp.demo.service;

import com.lzp.demo.service.serviceModel.AdminModel;
import com.lzp.demo.utils.ResultMap;

public interface AdminService {
    /**
     *  查询用户信息，条件: 手机号，密码
     */
    ResultMap queryAdminInfo(String telephone, String passWord);
}
