package com.dtdhehe.studentscore.service.impl;

import com.dtdhehe.studentscore.entity.Role;
import com.dtdhehe.studentscore.entity.Student;
import com.dtdhehe.studentscore.entity.User;
import com.dtdhehe.studentscore.entity.UserRole;
import com.dtdhehe.studentscore.mapper.RoleMapper;
import com.dtdhehe.studentscore.mapper.StudentMapper;
import com.dtdhehe.studentscore.mapper.UserMapper;
import com.dtdhehe.studentscore.service.StudentService;
import com.dtdhehe.studentscore.service.UserService;
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
 * @author 陈珊珊
 * @version 1.0
 * @date 2019/11/18 22:32
 * @description
 **/
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Student findById(String id) {
        return studentMapper.findById(id);
    }

    @Override
    public Student findBySno(String sno) {
        return studentMapper.findBySno(sno);
    }

    @Override
    public Integer saveOrUpdate(Student student) {
        if (StringUtils.isEmpty(student.getId())){
            student.setId(ConstantUtils.getUniqueKey());
            BeansUtil.addSaveCommonValue(student);
            //新增时同时保存到user表
            User user = new User();
            user.setId(ConstantUtils.getUniqueKey());
            BeansUtil.addSaveCommonValue(user);
            user.setUserName(student.getSno());
            //默认用户名和密码都是学号
            user.setPassword(PasswordUtils.getPWD(student.getSno(),user.getUserName()));
            //学生身份
            user.setStatus(ConstantUtils.STUDENT);
            Integer integer = userMapper.save(user);
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
                //保存用户id到学生表
                student.setUserId(user.getId());
                return studentMapper.save(student);
            }else {
                return 0;
            }
        }else {
            Student oldStudent = studentMapper.findById(student.getId());
            //将不为空的属性拷贝到旧对象中
            BeansUtil.copyPropertiesIgnoreNull(student,oldStudent);
            oldStudent.setUpdateTime(DateUtils.formatDateTime2());
            return studentMapper.update(oldStudent);
        }
    }

    @Override
    public Map<String, Object> queryStudent(Map<String, Object> queryMap) {
        Map<String,Object> resultMap = new HashMap<>(8);
        PageHelper.startPage((Integer) queryMap.get("pageNum"),(Integer)queryMap.get("pageSize"));
        List<Map<String,Object>> gradeList = studentMapper.queryStudent(queryMap);
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
        Student student = studentMapper.findById(id);
        //同时删除user表
        User user = userMapper.findById(student.getUserId());
        user.setValidFlag(ConstantUtils.NOTACTIVE);
        user.setUpdateTime(DateUtils.formatDateTime2());
        userMapper.update(user);
        return studentMapper.delete(id,DateUtils.formatDateTime2());
    }

    @Override
    public List<Student> findByGradeId(String id) {
        return studentMapper.findByGradeId(id);
    }
}
