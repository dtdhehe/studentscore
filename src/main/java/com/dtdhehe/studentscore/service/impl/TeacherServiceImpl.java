package com.dtdhehe.studentscore.service.impl;

import com.dtdhehe.studentscore.entity.Role;
import com.dtdhehe.studentscore.entity.Teacher;
import com.dtdhehe.studentscore.entity.User;
import com.dtdhehe.studentscore.entity.UserRole;
import com.dtdhehe.studentscore.mapper.RoleMapper;
import com.dtdhehe.studentscore.mapper.TeacherMapper;
import com.dtdhehe.studentscore.mapper.UserMapper;
import com.dtdhehe.studentscore.service.TeacherService;
import com.dtdhehe.studentscore.util.BeansUtil;
import com.dtdhehe.studentscore.util.ConstantUtils;
import com.dtdhehe.studentscore.util.DateUtils;
import com.dtdhehe.studentscore.util.PasswordUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 陈姗姗
 * @version 1.0.0
 * @date 2019/11/20 16:29
 * @description
 **/
@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Teacher findById(String id) {
        return teacherMapper.findById(id);
    }

    @Override
    public Teacher findByTno(String tno) {
        return teacherMapper.findByTno(tno);
    }

    @Override
    public Integer saveOrUpdate(Teacher teacher) {
        if (StringUtils.isEmpty(teacher.getId())){
            teacher.setId(ConstantUtils.getUniqueKey());
            BeansUtil.addSaveCommonValue(teacher);
            //新增时同时保存到user表
            User user = new User();
            user.setId(ConstantUtils.getUniqueKey());
            BeansUtil.addSaveCommonValue(user);
            user.setUserName(teacher.getTno());
            //默认用户名和密码都是编号
            user.setPassword(PasswordUtils.getPWD(teacher.getTno(),user.getUserName()));
            //教师身份
            user.setStatus(ConstantUtils.TEACHER);
            Integer integer = userMapper.save(user);
            //保存用户id到教师表
            teacher.setUserId(user.getId());
            if (integer.equals(ConstantUtils.SUCCESS)){
                //新增用户时，同时保存用户-权限表
                UserRole userRole = new UserRole();
                userRole.setId(ConstantUtils.getUniqueKey());
                BeansUtil.addSaveCommonValue(userRole);
                userRole.setUserId(user.getId());
                //根据用户标识查询权限id
                Role role = roleMapper.findByRoleName(ConstantUtils.ROLE_MAP.get(user.getStatus()));
                userRole.setRoleId(role.getId());
                roleMapper.saveUserRole(userRole);
                return teacherMapper.save(teacher);
            }else {
                return 0;
            }
        }else {
            Teacher oldTeacher = teacherMapper.findById(teacher.getId());
            //将不为空的属性拷贝到旧对象中
            BeansUtil.copyPropertiesIgnoreNull(teacher,oldTeacher);
            oldTeacher.setUpdateTime(DateUtils.formatDateTime2());
            return teacherMapper.update(oldTeacher);
        }
    }

    @Override
    public Map<String, Object> queryTeacher(Map<String, Object> queryMap) {
        Map<String,Object> resultMap = new HashMap<>(8);
        PageHelper.startPage((Integer) queryMap.get("pageNum"),(Integer)queryMap.get("pageSize"));
        List<Map<String,Object>> gradeList = teacherMapper.queryTeacher(queryMap);
        //修改日期格式
        for (Map<String,Object> item : gradeList){
            //转换日期格式
            item.put("update_time",DateUtils.convertDateToViewType((String) item.get("update_time"),"datetime"));
        }
        //总条数
        PageInfo<Map<String,Object>> pageInfo = new PageInfo<>(gradeList);
        int count = (int) pageInfo.getTotal();
        //将结果返回
        resultMap.put("count",count);
        resultMap.put("list",gradeList);
        return resultMap;
    }

    @Override
    public Integer delete(String id) {
        Teacher teacher = teacherMapper.findById(id);
        //同时删除user表
        User user = userMapper.findById(teacher.getUserId());
        user.setValidFlag(ConstantUtils.NOTACTIVE);
        user.setUpdateTime(DateUtils.formatDateTime2());
        userMapper.update(user);
        //删除教师
        teacher.setValidFlag(ConstantUtils.NOTACTIVE);
        teacher.setUpdateTime(DateUtils.formatDateTime2());
        return teacherMapper.update(teacher);
    }
}
