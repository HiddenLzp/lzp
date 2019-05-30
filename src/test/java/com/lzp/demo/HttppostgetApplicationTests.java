package com.lzp.demo;

import com.lzp.demo.service.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HttppostgetApplicationTests {


    @Resource
    private MailService mailService;

    @Test
    public void contextLoads() {

        mailService.sendSimpleTextMailActual("测试发发送","1111",new String[]{"1223279108@qq.com"},null,null,null);
    }

}
