package com.lzp.demo.service.impl;

import com.alibaba.fastjson.JSON;
import com.lzp.demo.model.Admin;
import com.lzp.demo.service.CommonService;
import com.lzp.demo.utils.RedisUtil;
import org.springframework.stereotype.Service;

/**
 * @authorHmLzp
 * @create 2019 - 05 - 27 22:55
 */
@Service
public class CommonServiceImpl implements CommonService {
    @Override
    public Admin getAdmin(String token) {
        String redisKey = "admin_"+token;
        Admin admin = null;
        if(RedisUtil.exists(redisKey)){
            admin = JSON.parseObject((String) RedisUtil.get(redisKey),Admin.class);
        }
        return admin;
    }
}
