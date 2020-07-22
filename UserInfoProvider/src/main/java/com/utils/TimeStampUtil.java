package com.utils;

import java.sql.Timestamp;

/**
 * 获取用户注册时间的工具类
 */
public class TimeStampUtil {

    /**
     * 获取当前时间戳
     * @return 返回当前时间戳
     */
    public static Timestamp getTimeStamp(){
        return new Timestamp(System.currentTimeMillis());
    }
}
