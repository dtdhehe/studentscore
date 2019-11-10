package com.dtdhehe.studentscore.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 陈姗姗
 * @version 1.0.0
 * @date 2019/11/6 18:06
 * @description
 **/
public class DateUtils {

    public static Date now() {
        return new Date();
    }

    public static String formatDate(Date date, String pattern) {
        if (date == null) {
            return "";
        } else {
            if (pattern == null) {
                pattern = "yyyy-MM-dd";
            }

            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            return sdf.format(date);
        }
    }

    public static String formatDateTime() {
        return formatDate(now(), "yyyy-MM-dd HH:mm:ss");
    }

    public static String formatDateTime2() {
        return formatDate(now(), "yyyyMMddHHmmssSSS");
    }

    public static String formatDateTime3() {
        return formatDate(now(), "yyyyMMddHHmmss");
    }

}
