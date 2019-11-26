package com.dtdhehe.studentscore.service.impl;

import com.dtdhehe.studentscore.entity.Role;
import com.dtdhehe.studentscore.entity.User;
import com.dtdhehe.studentscore.entity.UserRole;
import com.dtdhehe.studentscore.mapper.RoleMapper;
import com.dtdhehe.studentscore.mapper.UserMapper;
import com.dtdhehe.studentscore.service.UserService;
import com.dtdhehe.studentscore.util.BeansUtil;
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
    @Autowired
    private RoleMapper roleMapper;

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
            if (StringUtils.isEmpty(user.getValidFlag())){
                user.setValidFlag(ConstantUtils.NOTACTIVE);
            }
            user.setCreateTime(DateUtils.formatDateTime2());
            user.setUpdateTime(DateUtils.formatDateTime2());
            //新增用户时，同时保存用户-权限表
            UserRole userRole = new UserRole();
            userRole.setId(ConstantUtils.getUniqueKey());
            BeansUtil.addSaveCommonValue(userRole);
            userRole.setUserId(user.getId());
            //根据用户标识查询权限id
            Role role = roleMapper.findByRoleName(user.getStatus());
            userRole.setRoleId(role.getId());
            Integer result = roleMapper.saveUserRole(userRole);
            if (result.equals(ConstantUtils.SUCCESS)){
                return userMapper.save(user);
            }else {
                return 0;
            }
        }else {
            //TODO 修改用户
        }
        return null;
    }

    @Override
    public Integer updatePassword(String userId, String password) {
        User user = userMapper.findById(userId);
        //新密码
        String newPassword = PasswordUtils.getPWD(password, user.getUserName());
        user.setPassword(newPassword);
        user.setUpdateTime(DateUtils.formatDateTime2());
        return userMapper.update(user);
    }
}
