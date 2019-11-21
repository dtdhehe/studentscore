package com.dtdhehe.studentscore.controller;

import com.dtdhehe.studentscore.entity.Student;
import com.dtdhehe.studentscore.entity.Teacher;
import com.dtdhehe.studentscore.entity.User;
import com.dtdhehe.studentscore.service.DepartmentService;
import com.dtdhehe.studentscore.service.StudentService;
import com.dtdhehe.studentscore.service.TeacherService;
import com.dtdhehe.studentscore.util.ConstantUtils;
import com.dtdhehe.studentscore.util.DateUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 陈珊珊
 * @version 1.0
 * @date 2019/11/5 23:36
 * @description
 **/
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private DepartmentService departmentService;

    /**
     * 个人信息管理
     * @return
     */
    @GetMapping("/getUserManage")
    public String getUserManage(Model model){
        //获得当前登录用户
        User user = (User)SecurityUtils.getSubject().getPrincipal();
        //根据用户身份标识判断查询学生还是教师
        if (ConstantUtils.TEACHER.equals(user.getStatus())){
            //教师身份
            Teacher teacher = teacherService.findByUserId(user.getId());
            model.addAttribute("teacher",teacher);
        }else if (ConstantUtils.STUDENT.equals(user.getStatus())){
            //学生身份
            Student student = studentService.findByUserId(user.getId());
            model.addAttribute("student",student);
        }
        //转换创建时间
        user.setCreateTime(DateUtils.convertDateToViewType(user.getCreateTime(),"datetime"));
        model.addAttribute("user",user);
        model.addAttribute("department",departmentService.findAll());
        return "user-manage/userinfo/userinfo";
    }

    /**
     * 个人信息新增编辑页面
     * @return
     */
    @GetMapping("/getUserInfo")
    public String getUserInfo(@RequestParam(value = "id",required = false)String id, Model model){
        return "user-manage/userinfo/dialog";
    }

}
