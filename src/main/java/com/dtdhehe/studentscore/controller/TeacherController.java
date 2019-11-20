package com.dtdhehe.studentscore.controller;

import com.dtdhehe.studentscore.entity.Teacher;
import com.dtdhehe.studentscore.service.DepartmentService;
import com.dtdhehe.studentscore.service.TeacherService;
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
 * @date 2019/11/20 16:17
 * @description
 **/
@Controller
@RequestMapping("/staff")
public class TeacherController {

    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private TeacherService teacherService;

    /**
     * 教师管理
     * @return
     */
    @GetMapping("/getTeacherManage")
    public String getTeacherManage(Model model){
        //查询全部学院
        model.addAttribute("departmentList",departmentService.findAll());
        return "staff-manage/teacher/teacher";
    }

    /**
     * 教师新增编辑页面
     * @return
     */
    @GetMapping("/getTeacherInfo")
    public String getTeacherInfo(@RequestParam(value = "id",required = false)String id, Model model){
        Teacher teacher = new Teacher();
        if (!StringUtils.isEmpty(id)){
            //id不为空，则为编辑。查询该对象
            teacher = teacherService.findById(id);
        }
        model.addAttribute("teacher",teacher);
        //查询全部学院
        model.addAttribute("departmentList",departmentService.findAll());
        return "staff-manage/teacher/dialog";
    }

    /**
     * 新增教师
     * @param teacher
     * @return
     */
    @PostMapping("/teacher")
    @ResponseBody
    public ResultVO saveTeacher(Teacher teacher){
        Teacher dbTeacher = teacherService.findByTno(teacher.getTno());
        if (dbTeacher != null){
            return ResultUtils.failed("该编号已存在");
        }
        Integer result = teacherService.saveOrUpdate(teacher);
        return result.equals(ConstantUtils.SUCCESS)?ResultUtils.success("保存成功"):ResultUtils.failed("保存失败,请重新保存");
    }

    /**
     * 更新教师
     * @param teacher
     * @param id
     * @return
     */
    @PutMapping("/teacher/{id}")
    @ResponseBody
    public ResultVO updateTeacher(Teacher teacher,@PathVariable("id") String id){
        Teacher dbTeacher = teacherService.findByTno(teacher.getTno());
        if (dbTeacher != null && !dbTeacher.getId().equals(id)){
            return ResultUtils.failed("该编号已存在");
        }
        Integer result = teacherService.saveOrUpdate(teacher);
        return result.equals(ConstantUtils.SUCCESS)?ResultUtils.success("修改成功"):ResultUtils.failed("修改失败,请重新修改");
    }

    /**
     * table查询教师列表
     * @param rows
     * @param page
     * @param departmentId
     * @return
     */
    @GetMapping("/queryTeacher")
    @ResponseBody
    public TableModel queryTeacher(@RequestParam("rows") Integer rows, @RequestParam("page") Integer page,
                                   @RequestParam(value = "departmentId",required = false)String departmentId,
                                   @RequestParam(value = "tno",required = false)String tno,
                                   @RequestParam(value = "name",required = false)String name){
        TableModel tableModel = new TableModel();
        Map<String,Object> queryMap = new HashMap<>(8);
        queryMap.put("pageSize",rows);
        queryMap.put("pageNum",page + 1);
        queryMap.put("departmentId",departmentId);
        queryMap.put("tno",tno);
        queryMap.put("name",name);
        Map<String,Object> resultMap = teacherService.queryTeacher(queryMap);
        tableModel.setRows((List) resultMap.get("list"));
        tableModel.setTotal((Integer) resultMap.get("count"));
        return tableModel;
    }

    /**
     * 删除教师
     * @param id
     * @return
     */
    @DeleteMapping("/teacher/{id}")
    @ResponseBody
    public ResultVO deleteTeacher(@PathVariable("id") String id){
        Integer result = teacherService.delete(id);
        return result.equals(ConstantUtils.SUCCESS)?ResultUtils.success("删除成功"):ResultUtils.failed("删除失败,请重新删除");
    }

}
