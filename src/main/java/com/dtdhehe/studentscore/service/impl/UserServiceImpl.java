package com.dtdhehe.studentscore.service.impl;

import com.dtdhehe.studentscore.entity.User;
import com.dtdhehe.studentscore.mapper.UserMapper;
import com.dtdhehe.studentscore.service.UserService;
import com.dtdhehe.studentscore.util.ConstantUtils;
import com.dtdhehe.studentscore.util.DateUtils;
import com.dtdhehe.studentscore.util.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author 陈珊珊
 * @version 1.0
 * @date 2019/11/5 23:39
 * @description
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByUserName(String userName) {
        return userMapper.findByUserName(userName);
    }

    @Override
    public Integer saveOrUpdateUser(User user) {
        if (StringUtils.isEmpty(user.getId())){
            //id为空，新增用户
            user.setId(ConstantUtils.getUniqueKey());
            //取用户名作为加密盐
            user.setPassword(PasswordUtils.getPWD(user.getPassword(),user.getUserName()));
            user.setValidFlag(ConstantUtils.NOTACTIVE);
            user.setCreateTime(DateUtils.formatDateTime2());
            user.setUpdateTime(DateUtils.formatDateTime2());
            return userMapper.save(user);
        }else {
            //TODO 修改用户
        }
        return null;
    }
}
