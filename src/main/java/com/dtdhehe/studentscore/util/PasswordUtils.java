package com.dtdhehe.studentscore.util;

import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * @author 陈珊珊
 * @version 1.0
 * @date 2019/11/5 23:28
 * @description
 **/
public class PasswordUtils {
    /**
     * 密码加密
     * @param userPwd
     * @return
     */
    public static String getPWD(String userPwd,String salt){
        return new SimpleHash("MD5",userPwd,salt,1024).toHex();
    }
}
