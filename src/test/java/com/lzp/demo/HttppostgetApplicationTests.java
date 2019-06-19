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

        Map map = new HashMap();
        map.put("page",1);
        map.put("count",2);
        map.put("type","image");
        String s = HttpRequestUtil.httpRequest("https://api.apiopen.top/getJoke", HttpRequestUtil.GET, map);
        System.out.println(s);

    }

}
