package com.lzp.demo.utils;

import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * @Auther:lk
 * @Date:2019/6/213 :51
 * @Description:
 */
public class RandomNum {

    public static String getRandomNum(){
        //六位随机数
        Random random = new Random();
        String result="";
        for (int i=0;i<6;i++) {
            result += random.nextInt(10);

        }
        return result;
    }

}
