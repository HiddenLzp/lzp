package com.lzp.demo.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Author：罗皖西
 * @Description：时间工具类
 * @Date：2019/2/12 0012
 */
public class DateUtil {

    //获取当前时间到第二天凌晨的秒数
    public static Long getSecondsNextEarlyMorning() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return (calendar.getTimeInMillis() - System.currentTimeMillis()) / 1000;
    }

    /**
     * 开始时间转换为 yy-MM-dd 00:00:00
     * @param date
     * @return
     */
    public static Date conversionStartDate(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR,00);
        calendar.add(Calendar.MINUTE,00);
        calendar.add(Calendar.SECOND,00);
        date =  calendar.getTime();
        return date;
    }

    /**
     * 结束时间转换为 yy-MM-dd 23:59:59
     * @param date
     * @return
     */
    public static Date conversionEndDate(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR,23);
        calendar.add(Calendar.MINUTE,59);
        calendar.add(Calendar.SECOND,59);
        date =  calendar.getTime();
        return date;
    }

    /**
     *  将时间转换为2019年05月21日 的格式
     * @param date
     * @return
     */
    public static String conversionDate(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1 ;
        int day = calendar.get(Calendar.DATE);
        return year+"年"+month+"月"+day+"日";
    }

    /**
     * 转化时间格式为 2019-06-04 12:02:00格式
     */
    public static String conversionDateToString(Date date){
        if(date == null){
            date = new Date();
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String formatDate = simpleDateFormat.format(date);

        return formatDate;
    }

}
