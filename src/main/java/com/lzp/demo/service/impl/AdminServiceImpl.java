package com.lzp.demo.service.impl;

import com.alibaba.fastjson.JSON;
import com.lzp.demo.dao.AdminMapper;
import com.lzp.demo.model.Admin;
import com.lzp.demo.service.AdminService;
import com.lzp.demo.service.CommonService;
import com.lzp.demo.service.MailService;
import com.lzp.demo.service.serviceModel.AdminModel;
import com.lzp.demo.utils.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import javax.xml.transform.Result;
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

    @Resource
    private RandomNum randomNum;

    @Override
    public ResultMap queryAdminInfo(String telephone, String passWord) {

        Admin admin;
        passWord = MD5Utils.string2MD5(passWord);
        String tokenKey = (String) RedisUtil.get("admin_" + telephone);
        if (tokenKey != null){
            admin = commonService.getAdmin(tokenKey);
            RedisUtil.remove("admin_"+tokenKey);
            if(admin != null && admin.getPassword().equals(passWord)){
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
    public ResultMap registerAdmin(String telephone, String email) {
        boolean exists = RedisUtil.exists("register_" + telephone);
        if(exists){
            return ResultMap.ok().put("result","验证码已经发送，若未收到，请在120秒后重试!");
        }
        /**
         * 查看手机号是否已经被注册
                */
        Admin admin =adminMapper.queryAdminByTelephone(telephone,null);
        if(admin != null){
            if(admin.getStatus() == "2"){
                return ResultMap.ok().put("result","填写验证码!");
            }else{
                return ResultMap.error(100003,"该手机已被注册!");

            }
        }else{
            Admin admin2 =adminMapper.queryAdminByTelephone(null,email);
            if(admin2!=null){
                    return ResultMap.error(100003,"该邮箱已被注册");
            }
        }
        //六位随机数
        String result= randomNum.getRandomNum();
            try {
                mailServicel.sendSimpleTextMailActual("注册验证码",result,new String[]{email},null,null,null);
                //验证码存入redis
                RedisUtil.set("register_"+telephone,result, (long) (60 * 2));
                return ResultMap.ok().put("result","邮件已发送!");
            }catch (Exception e){

                return ResultMap.error(100002,"注册失败，请稍后重试!");
            }
    }

    @Override
    public ResultMap checkmsgCode(String telephone, String msgCode, String email, String password) {
        String result = (String) RedisUtil.get("register_" + telephone);
        if(msgCode.equals(result)){
            try {
                //加密密码
                password = MD5Utils.string2MD5(password);
                Integer flag = adminMapper.insertAdmin(telephone,email,password,result);
                if (flag == 1) {
                    RedisUtil.remove("register_"+telephone);
                    return ResultMap.ok().put("result","注册成功!请返回重新登录!");
                }else {
                    return ResultMap.error(100002,"注册失败，请稍后重试!");
                }
            }catch (Exception e){
                e.printStackTrace();
                return ResultMap.error(100010,"系统未知错误!");
            }

        }else {
            return ResultMap.error(100010,"验证码错误");
        }
    }

    @Override
    public ResultMap forgetPwd(String email) {
        boolean bool= RedisUtil.exists("forget_"+email);
        if(bool){
            return ResultMap.ok().put("result","验证码已经发送，若未收到，请在120秒后重试!");
        }
        //判断是否存在账号
        Admin admin = adminMapper.queryAdminByTelephone(null,email);
        if(admin == null){
            return ResultMap.error(100009,"不存该账号");
        }
        //生曾随机验证码
        String num= randomNum.getRandomNum();
        try{
            mailServicel.sendSimpleTextMailActual("修改密码",num,new String[]{email},null,null,null);
            RedisUtil.set("forget_"+email,num, (long)(60*3));
            return ResultMap.ok().put("result","邮件已发送");
        }catch(Exception e){
            return ResultMap.error(100002,e.getMessage());
        }
    }

    @Override
    public ResultMap updatePwd(String msgCode,String email, String passWord)  {
         String msgCode2= (String)RedisUtil.get("forget_"+email);
         if(!msgCode2.equals(msgCode)){
             return ResultMap.error(100005,"验证码不一致");
         }
         //将密码加密
        passWord=MD5Utils.string2MD5(passWord);
        Integer result=null;
        try {
            result = adminMapper.updateAdminPwd( passWord,email);
            return ResultMap.ok(200,"修改成功");
        }catch (Exception ex){
            return ResultMap.error("修改失败");
        }
    }
}
