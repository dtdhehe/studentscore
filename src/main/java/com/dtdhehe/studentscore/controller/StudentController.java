package com.dtdhehe.studentscore.controller;

import com.dtdhehe.studentscore.entity.Student;
import com.dtdhehe.studentscore.service.DepartmentService;
import com.dtdhehe.studentscore.service.GradeService;
import com.dtdhehe.studentscore.service.StudentService;
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

    @Autowired
    private StudentService studentService;

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
    public String getStudentInfo(@RequestParam(value = "id",required = false)String id, Model model){
        Student student = new Student();
        if (!StringUtils.isEmpty(id)){
            //id不为空，则为编辑。查询该对象
            student = studentService.findById(id);
        }
        model.addAttribute("student",student);
        //查询全部学院
        model.addAttribute("departmentList",departmentService.findAll());
        return "staff-manage/student/dialog";
    }

}
