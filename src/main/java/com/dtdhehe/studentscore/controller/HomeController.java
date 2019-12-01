package com.dtdhehe.studentscore.controller;

import com.dtdhehe.studentscore.entity.Student;
import com.dtdhehe.studentscore.entity.Teacher;
import com.dtdhehe.studentscore.entity.User;
import com.dtdhehe.studentscore.entity.Wrap;
import com.dtdhehe.studentscore.service.StudentService;
import com.dtdhehe.studentscore.service.TeacherService;
import com.dtdhehe.studentscore.service.WrapService;
import com.dtdhehe.studentscore.util.ConstantUtils;
import com.dtdhehe.studentscore.util.DateUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author 陈姗姗
 * @version 1.0.0
 * @date 2019/11/8 23:16
 * @description
 **/
@Controller
public class HomeController {

    @Autowired
    private WrapService wrapService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentService studentService;

    @RequestMapping("/index")
    public String index(Model model){
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
        model.addAttribute("user",user);
        return "main";
    }

    @RequestMapping("/home")
    public String home(Model model){
        String now = DateUtils.formatDate(new Date(),"yyyy年MM月dd日");
        model.addAttribute("year",now.substring(0,4));
        model.addAttribute("date",now.substring(4,now.length()));
        SimpleDateFormat format = new SimpleDateFormat("EEEE");
        String currSun = format.format(new Date());
        model.addAttribute("currSun",currSun);
        //获得首页轮播图
        List<Wrap> wrapList = wrapService.queryAll();
        model.addAttribute("wrapList",wrapList);
        return "main-content";
    }

}
