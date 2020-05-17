package com.car.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zhengjie on 2020/5/8.
 */
public class String2Date {
    public static Date StringToTime(String date) throws ParseException {
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
        Date time=ft.parse(date);
        return time;
    }

    public static String DateToString(Date date){
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
        String time=ft.format(date);
        return time;
    }

}
