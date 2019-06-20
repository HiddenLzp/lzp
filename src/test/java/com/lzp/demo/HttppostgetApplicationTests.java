package com.lzp.demo;

import com.lzp.demo.service.MailService;
import com.lzp.demo.utils.HttpRequestUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HttppostgetApplicationTests {


    @Resource
    private MailService mailService;

    @Resource
    private RestTemplate restTemplate;
    @Test
    public void contextLoads() {

       // mailService.sendSimpleTextMailActual("测试发发送","1111",new String[]{"1223279108@qq.com"},null,null,null);
    }

    @Test
    public void test(){
        ResponseEntity<String> forEntity = restTemplate.getForEntity("https://api.apiopen.top/getJoke", String.class);
        System.out.println(forEntity.getBody());
    }

    public static void main(String[] args) {

       /* Map map = new HashMap();
        map.put("page",1);
        map.put("count",2);
        map.put("type","image");
        String s = HttpRequestUtil.httpRequest("https://api.apiopen.top/getJoke", HttpRequestUtil.GET, map);
        System.out.println(s);*/


       float a = 1.0f - 0.9f;
        System.out.println("1.0f - 0.9f = " + a);//0.100000024

        float b = 09f - 0.8f;
        System.out.println(" 09f - 0.8f = " + b);//8.2

        Float a1 = Float.valueOf(1.0f - 0.9f);
        System.out.println("Float.valueOf(1.0f - 0.9f) = " + a1);//0.100000024
        Float b1 = Float.valueOf(0.9f - 0.8f);
        System.out.println("Float.valueOf(0.9f - 0.8f) = " + b1);//0.099999964

        /**
         * 有空指针异常
         */
        String param = null;
        switch (param) {
            case "null":
                System.out.println("null");
                break;
            default:
                System.out.println("default");
        }




    }

}
