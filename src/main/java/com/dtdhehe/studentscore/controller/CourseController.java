package com.dtdhehe.studentscore.controller;

import com.dtdhehe.studentscore.entity.TeachSubject;
import com.dtdhehe.studentscore.service.DepartmentService;
import com.dtdhehe.studentscore.service.SubjectService;
import com.dtdhehe.studentscore.service.TeachSubjectService;
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
 * @date 2019/11/22 16:30
 * @description
 **/
@Controller
@RequestMapping("/score")
public class CourseController {

    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private TeachSubjectService teachSubjectService;

    /**
     * 课程管理
     * @return
     */
    @GetMapping("/getCourseManage")
    public String getCourseManage(Model model){
        //查询全部学院
        model.addAttribute("departmentList",departmentService.findAll());
        return "score-manage/course/course";
    }

    /**
     * 课程新增编辑页面
     * @return
     */
    @GetMapping("/getCourseInfo")
    public String getCourseInfo(@RequestParam(value = "id",required = false)String id, Model model){
        TeachSubject course = new TeachSubject();
        if (!StringUtils.isEmpty(id)){
            //id不为空，则为编辑。查询该对象
            course = teachSubjectService.findById(id);
        }
        model.addAttribute("course",course);
        //查询全部学院
        model.addAttribute("departmentList",departmentService.findAll());
        return "score-manage/course/dialog";
    }

    /**
     * 打开课程选择弹窗
     * @return
     */
    @GetMapping("/openSubjectSelect")
    public String openSubjectSelect(){
        return "score-manage/course/subject-select";
    }

    /**
     * 打开教师选择弹窗
     * @return
     */
    @GetMapping("/openTeacherSelect")
    public String openTeacherSelect(Model model){
        //查询全部学院
        model.addAttribute("departmentList",departmentService.findAll());
        return "score-manage/course/teacher-select";
    }

    /**
     * 新增学科
     * @param teachSubject
     * @return
     */
    @PostMapping("/course")
    @ResponseBody
    public ResultVO saveTeachSubject(TeachSubject teachSubject){
        TeachSubject dbTeachSubject = teachSubjectService.findBySubjectIdAndGradeId(teachSubject.getSubjectId(),teachSubject.getGradeId());
        if (dbTeachSubject != null){
            return ResultUtils.failed("该课程已存在");
        }
        Integer result = teachSubjectService.saveOrUpdate(teachSubject);
        return result.equals(ConstantUtils.SUCCESS)?ResultUtils.success("保存成功"):ResultUtils.failed("保存失败,请重新保存");
    }

    /**
     * table查询学科列表
     * @param rows
     * @param page
     * @param subjectName
     * @return
     */
    @GetMapping("/queryCourse")
    @ResponseBody
    public TableModel queryCourse(@RequestParam("rows") Integer rows, @RequestParam("page") Integer page,
                                   @RequestParam(value = "subjectName",required = false)String subjectName,
                                   @RequestParam(value = "departmentId",required = false)String departmentId,
                                   @RequestParam(value = "majorId",required = false)String majorId,
                                   @RequestParam(value = "gradeId",required = false)String gradeId,
                                   @RequestParam(value = "teacherName",required = false)String teacherName,
                                   @RequestParam(value = "schoolYear",required = false)String schoolYear,
                                   @RequestParam(value = "schoolTerm",required = false)String schoolTerm){
        TableModel tableModel = new TableModel();
        Map<String,Object> queryMap = new HashMap<>(16);
        queryMap.put("pageSize",rows);
        queryMap.put("pageNum",page + 1);
        queryMap.put("subjectName",subjectName);
        queryMap.put("departmentId",departmentId);
        queryMap.put("majorId",majorId);
        queryMap.put("gradeId",gradeId);
        queryMap.put("teacherName",teacherName);
        queryMap.put("schoolYear",schoolYear);
        queryMap.put("schoolTerm",schoolTerm);
        Map<String,Object> resultMap = teachSubjectService.queryTeachSubject(queryMap);
        tableModel.setRows((List) resultMap.get("list"));
        tableModel.setTotal((Integer) resultMap.get("count"));
        return tableModel;
    }

    /**
     * 更新课程
     * @param teachSubject
     * @param id
     * @return
     */
    @PutMapping("/course/{id}")
    @ResponseBody
    public ResultVO updateTeachSubject(TeachSubject teachSubject,@PathVariable("id") String id){
        TeachSubject dbTeachSubject = teachSubjectService.findBySubjectIdAndGradeId(teachSubject.getSubjectId(),teachSubject.getGradeId());
        if (dbTeachSubject != null && !dbTeachSubject.getId().equals(id)){
            return ResultUtils.failed("该课程已存在");
        }
        Integer result = teachSubjectService.saveOrUpdate(teachSubject);
        return result.equals(ConstantUtils.SUCCESS)?ResultUtils.success("修改成功"):ResultUtils.failed("修改失败,请重新修改");
    }

    /**
     * 删除课程
     * @param id
     * @return
     */
    @DeleteMapping("/course/{id}")
    @ResponseBody
    public ResultVO deleteTeachSubject(@PathVariable("id") String id){
        Integer result = teachSubjectService.delete(id);
        return result.equals(ConstantUtils.SUCCESS)?ResultUtils.success("删除成功"):ResultUtils.failed("删除失败,请重新删除");
    }

}
