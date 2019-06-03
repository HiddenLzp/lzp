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
     * 将list集合中重复的元素去除，返回
     */
    public static void removeDuplicate(List list) {

        HashSet h = new HashSet(list);

        list.clear();

        list.addAll(h);

    }
}
