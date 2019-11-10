package com.dtdhehe.studentscore.controller;

import org.springframework.stereotype.Controller;
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
public class StaffController {

    /**
     * 学生管理
     * @return
     */
    @GetMapping("/student")
    public String getStudentManage(){
        return "staff-manage/student/student";
    }

    /**
     * 学生新增编辑页面
     * @return
     */
    @RequestMapping("/student/dialog")
    public String getStudentInfo(){
        return "staff-manage/student/dialog";
    }

}
