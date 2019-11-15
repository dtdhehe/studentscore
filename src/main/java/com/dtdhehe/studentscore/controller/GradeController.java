package com.dtdhehe.studentscore.controller;

import com.dtdhehe.studentscore.entity.Grade;
import com.dtdhehe.studentscore.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 陈姗姗
 * @version 1.0.0
 * @date 2019/11/15 17:49
 * @description
 **/
@Controller
@RequestMapping("/teach")
public class GradeController {

    @Autowired
    private DepartmentService departmentService;

    /**
     * 班级管理
     * @return
     */
    @GetMapping("/getGradeManage")
    public String getMajorManage(Model model){
        //查询全部学院
        model.addAttribute("departmentList",departmentService.findAll());
        return "teach-manage/grade/grade";
    }

    /**
     * 班级新增编辑页面
     * @return
     */
    @GetMapping("/getGradeInfo")
    public String getMajorInfo(@RequestParam(value = "id",required = false)String id, Model model){
        Grade grade = new Grade();
        if (!StringUtils.isEmpty(id)){
            //id不为空，则为编辑。查询该对象
//            grade = majorService.findById(id);
        }
        model.addAttribute("grade",grade);
        //查询全部学院
        model.addAttribute("departmentList",departmentService.findAll());
        return "teach-manage/major/dialog";
    }

}
