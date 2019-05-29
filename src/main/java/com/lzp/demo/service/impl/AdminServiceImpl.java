package com.lzp.demo.service.impl;

import com.alibaba.fastjson.JSON;
import com.lzp.demo.dao.AdminMapper;
import com.lzp.demo.model.Admin;
import com.lzp.demo.service.AdminService;
import com.lzp.demo.service.CommonService;
import com.lzp.demo.service.serviceModel.AdminModel;
import com.lzp.demo.utils.ErrorCode;
import com.lzp.demo.utils.MD5Utils;
import com.lzp.demo.utils.RedisUtil;
import com.lzp.demo.utils.ResultMap;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @authorHmLzp
 * @create 2019 - 05 - 27 22:39
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Resource
    private AdminMapper adminMapper;

    @Resource
    private CommonService commonService;

    @Override
    public ResultMap queryAdminInfo(String telephone, String passWord) {


        Admin admin;
        passWord = MD5Utils.string2MD5(passWord);
        String tokenKey = (String) RedisUtil.get("admin_" + telephone);
        if (tokenKey != null){
            admin = commonService.getAdmin(tokenKey);
            RedisUtil.remove("admin_"+tokenKey);
            if(admin != null && admin.getPassWord().equals(passWord)){
                 RedisUtil.set("admin_"+admin.getTelephone(),admin.getToken(),RedisUtil.A_WEEK);
                 RedisUtil.set("admin_"+admin.getToken(), JSON.toJSONString(admin),RedisUtil.TWO_DAYS);
                 return ResultMap.ok().put("result",admin);
            }
        }
        admin = adminMapper.queryAdminInfo(telephone,passWord);
        if(admin != null){
            admin.setToken(UUID.randomUUID().toString());
            RedisUtil.set("admin_"+admin.getTelephone(),admin.getToken(),RedisUtil.A_WEEK);
            RedisUtil.set("admin_"+admin.getToken(), JSON.toJSONString(admin),RedisUtil.TWO_DAYS);
            return ResultMap.ok().put("result",admin);
        }
        return ResultMap.error(ErrorCode.LOGIN_ERROR,"账号或密码错误");
    }
}
