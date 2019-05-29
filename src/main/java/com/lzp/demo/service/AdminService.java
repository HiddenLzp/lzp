package com.lzp.demo.service;

import com.lzp.demo.service.serviceModel.AdminModel;
import com.lzp.demo.utils.ResultMap;

public interface AdminService {
    ResultMap queryAdminInfo(String telephone, String passWord);
}
