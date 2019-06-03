package com.lzp.demo.utils;

import java.util.HashSet;
import java.util.List;

/**
 * @Author LiZePing
 * @date2019/6/3 16:45
 * 工具类
 */
public class CommonUtils {

    /**
     * y
     */
    public static void removeDuplicate(List list) {

        HashSet h = new HashSet(list);

        list.clear();

        list.addAll(h);

        System.out.println(list);

    }
}
