package com.dtdhehe.studentscore.service;

import com.dtdhehe.studentscore.entity.User;

/**
 * @author 陈珊珊
 * @version 1.0
 * @date 2019/11/5 23:09
 * @description
 **/
public interface UserService {

    /**
     * 根据用户名查找用户
     * @param userName
     */
    User findByUserName(String userName);

    /**
     * 保存用户(注册)
     * @param user
     * @return
     */
    Integer saveOrUpdateUser(User user);

    /**
     * 根据id更新用户的password
     * @param userId
     * @param password
     * @return
     */
    Integer updatePassword(String userId,String password);

}
