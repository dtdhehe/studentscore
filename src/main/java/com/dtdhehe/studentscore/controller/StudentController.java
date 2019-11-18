package com.dtdhehe.studentscore.controller;

import com.dtdhehe.studentscore.service.DepartmentService;
import com.dtdhehe.studentscore.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 陈姗姗
 * @version 1.0.0
 * @date 2019/11/9 20:09
 * @description
 **/
@Controller
@RequestMapping("/staff")
public class StudentController {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private GradeService gradeService;

    /**
     * 学生管理
     * @return
     */
    @GetMapping("/getStudentManage")
    public String getStudentManage(Model model){
        //查询全部学院
        model.addAttribute("departmentList",departmentService.findAll());
        return "staff-manage/student/student";
    }

    /**
     * 学生新增编辑页面
     * @return
     */
    @GetMapping("/getStudentInfo")
    public String getStudentInfo(Model model){
        return "staff-manage/student/dialog";
    }

}
