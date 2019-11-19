package com.dtdhehe.studentscore.controller;

import com.dtdhehe.studentscore.entity.Student;
import com.dtdhehe.studentscore.service.DepartmentService;
import com.dtdhehe.studentscore.service.GradeService;
import com.dtdhehe.studentscore.service.StudentService;
import com.dtdhehe.studentscore.util.ConstantUtils;
import com.dtdhehe.studentscore.util.ResultUtils;
import com.dtdhehe.studentscore.vo.ResultVO;
import com.dtdhehe.studentscore.vo.TableModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /**
     * 新增学生
     * @param student
     * @return
     */
    @PostMapping("/student")
    @ResponseBody
    public ResultVO saveGrade(Student student){
        Student dbStudent = studentService.findBySno(student.getSno());
        if (dbStudent != null){
            return ResultUtils.failed("该学号已存在");
        }
        Integer result = studentService.saveOrUpdate(student);
        return result.equals(ConstantUtils.SUCCESS)?ResultUtils.success("保存成功"):ResultUtils.failed("保存失败,请重新保存");
    }

    /**
     * 更新学生
     * @param student
     * @param id
     * @return
     */
    @PutMapping("/student/{id}")
    @ResponseBody
    public ResultVO updateMajor(Student student,@PathVariable("id") String id){
        Student dbStudent = studentService.findBySno(student.getSno());
        if (dbStudent != null && !dbStudent.getId().equals(id)){
            return ResultUtils.failed("该学号号已存在");
        }
        Integer result = studentService.saveOrUpdate(student);
        return result.equals(ConstantUtils.SUCCESS)?ResultUtils.success("修改成功"):ResultUtils.failed("修改失败,请重新修改");
    }

    /**
     * table查询学生列表
     * @param rows
     * @param page
     * @param majorId
     * @param gradeName
     * @param departmentId
     * @return
     */
    @GetMapping("/queryStudent")
    @ResponseBody
    public TableModel queryStudent(@RequestParam("rows") Integer rows, @RequestParam("page") Integer page,
                                 @RequestParam(value = "majorId",required = false)String majorId,
                                 @RequestParam(value = "gradeName",required = false)String gradeName,
                                 @RequestParam(value = "departmentId",required = false)String departmentId,
                                 @RequestParam(value = "sno",required = false)String sno,
                                 @RequestParam(value = "name",required = false)String name){
        TableModel tableModel = new TableModel();
        Map<String,Object> queryMap = new HashMap<>(8);
        queryMap.put("pageSize",rows);
        queryMap.put("pageNum",page + 1);
        queryMap.put("majorId",majorId);
        queryMap.put("gradeName",gradeName);
        queryMap.put("departmentId",departmentId);
        queryMap.put("sno",sno);
        queryMap.put("name",name);
        Map<String,Object> resultMap = studentService.queryStudent(queryMap);
        tableModel.setRows((List) resultMap.get("list"));
        tableModel.setTotal((Integer) resultMap.get("count"));
        return tableModel;
    }

    /**
     * 删除学生
     * @param id
     * @return
     */
    @DeleteMapping("/student/{id}")
    @ResponseBody
    public ResultVO deleteStudent(@PathVariable("id") String id){
        Integer result = studentService.delete(id);
        return result.equals(ConstantUtils.SUCCESS)?ResultUtils.success("删除成功"):ResultUtils.failed("删除失败,请重新删除");
    }

}
