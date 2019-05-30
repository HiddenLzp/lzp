package com.lzp.demo.service.impl;

import com.alibaba.fastjson.JSON;
import com.lzp.demo.dao.AdminMapper;
import com.lzp.demo.model.Admin;
import com.lzp.demo.service.AdminService;
import com.lzp.demo.service.CommonService;
import com.lzp.demo.service.MailService;
import com.lzp.demo.service.serviceModel.AdminModel;
import com.lzp.demo.utils.ErrorCode;
import com.lzp.demo.utils.MD5Utils;
import com.lzp.demo.utils.RedisUtil;
import com.lzp.demo.utils.ResultMap;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Random;
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

    @Resource
    private MailService mailServicel;

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
            AdminModel adminModel = new AdminModel();
            BeanUtils.copyProperties(admin,adminModel);
            return ResultMap.ok().put("result",adminModel);
        }
        return ResultMap.error(ErrorCode.LOGIN_ERROR,"账号或密码错误");
    }

    @Override
    public ResultMap registerAdmin(String telephone, String email, String password) {
        /**
         * 查看手机号是否已经被注册
         */
        Admin admin =adminMapper.queryAdminByTelephone(telephone);
        if(admin != null){
            return ResultMap.error(100003,"该手机已被注册!");
        }
        //加密密码
        password = MD5Utils.string2MD5(password);

        //六位随机数
        Random random = new Random();
        String result="";
        for (int i=0;i<6;i++) {
            result += random.nextInt(10);
        }
        mailServicel.sendSimpleTextMailActual("注册验证码",result,new String[]{email},null,null,null);
        Integer flag = adminMapper.insertAdmin(telephone,email,password,result);
        if(flag == 1){
            return ResultMap.ok().put("result","邮件已发送!");
        }else {
            return ResultMap.error(100002,"注册失败，请稍后重试!");
        }
    }

    @Override
    public ResultMap checkmsgCode(String telephone, String msgCode) {
        Admin admin = adminMapper.queryAdminByTelephone(telephone);
        if(msgCode.equals(admin.getRandome())){
            Integer result = adminMapper.modifyStatus(telephone);
            if(result > 1){
                return ResultMap.ok();
            }else {
                return ResultMap.error(100010,"系统未知错误!");
            }

        }else {
            return ResultMap.error(100010,"验证码错误");
        }
    }
}
