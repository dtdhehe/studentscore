package com.dtdhehe.studentscore.util;

import java.util.UUID;

/**
 * @author 陈珊珊
 * @version 1.0
 * @date 2019/11/5 23:27
 * @description
 **/
public class ConstantUtils {
    public static final String UNKNOWN = "-1";
    public static final String NOTACTIVE = "0";
    public static final String ACTIVE = "1";
    public static final String LOCKED = "2";
    public static final Integer ERROR = 0;
    public static final Integer SUCCESS = 1;
    public static final Integer FAILED = 2;

    /**
     * 随机获得主键
     * @return
     */
    public static synchronized String getUniqueKey() {
        //取前8位为用户主键
        return UUID.randomUUID().toString().substring(0,8);
    }
}
