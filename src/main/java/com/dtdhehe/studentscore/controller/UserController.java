package com.dtdhehe.studentscore.controller;

import com.dtdhehe.studentscore.entity.*;
import com.dtdhehe.studentscore.service.*;
import com.dtdhehe.studentscore.util.ConstantUtils;
import com.dtdhehe.studentscore.util.DateUtils;
import com.dtdhehe.studentscore.util.PasswordUtils;
import com.dtdhehe.studentscore.util.ResultUtils;
import com.dtdhehe.studentscore.vo.ResultVO;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

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
    @Autowired
    private UserService userService;
    @Autowired
    private GradeService gradeService;
    @Autowired
    private MajorService majorService;

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
            Department department = departmentService.findById(teacher.getDepartmentId());
            model.addAttribute("department",department);
        }else if (ConstantUtils.STUDENT.equals(user.getStatus())){
            //学生身份
            Student student = studentService.findByUserId(user.getId());
            model.addAttribute("student",student);
            Department department = departmentService.findById(student.getDepartmentId());
            model.addAttribute("department",department);
            Major major = majorService.findById(student.getMajorId());
            model.addAttribute("major",major);
            Grade grade = gradeService.findById(student.getGradeId());
            model.addAttribute("grade",grade);
        }
        model.addAttribute("user",user);
        return "user-manage/userinfo/userinfo";
    }

    /**
     * 个人信息新增编辑页面
     * @return
     */
    @GetMapping("/getUserInfo")
    public String getUserInfo(Model model){
        return "user-manage/userinfo/dialog";
    }

    /**
     * 修改密码
     * @param oldPassword
     * @param password
     * @return
     */
    @PutMapping("/changePassword")
    @ResponseBody
    public ResultVO updateTeachSubject(@RequestParam("oldPassword")String oldPassword,@RequestParam("password")String password){
        //获得当前登录用户
        User user = (User)SecurityUtils.getSubject().getPrincipal();
        //判断旧密码
        String encryOldPassword = PasswordUtils.getPWD(oldPassword, user.getUserName());
        if (!encryOldPassword.equals(user.getPassword())){
            //原密码错误
            return ResultUtils.failed("原密码错误");
        }
        //修改密码
        Integer result = userService.updatePassword(user.getId(), password);
        return result.equals(ConstantUtils.SUCCESS)?ResultUtils.success("修改成功"):ResultUtils.failed("修改失败,请重新修改");
    }

}
