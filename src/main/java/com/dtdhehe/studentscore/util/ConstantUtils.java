package com.dtdhehe.studentscore.util;

import java.util.HashMap;
import java.util.Map;
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
    public static final String ADMIN = "0";
    public static final String TEACHER = "1";
    public static final String STUDENT = "2";
    public static final Map<String,String> ROLE_MAP = new HashMap<>();
    static {
        ROLE_MAP.put(ConstantUtils.ADMIN,"admin");
        ROLE_MAP.put(ConstantUtils.TEACHER,"teacher");
        ROLE_MAP.put(ConstantUtils.STUDENT,"student");
    }

    /**
     * 随机获得主键
     * @return
     */
    public static synchronized String getUniqueKey() {
        //取前8位为用户主键
        return UUID.randomUUID().toString().substring(0,8);
    }
}
