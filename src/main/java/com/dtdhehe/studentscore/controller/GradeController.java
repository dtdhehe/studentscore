package com.dtdhehe.studentscore.controller;

import com.dtdhehe.studentscore.entity.Grade;
import com.dtdhehe.studentscore.service.DepartmentService;
import com.dtdhehe.studentscore.service.GradeService;
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
 * @date 2019/11/15 17:49
 * @description
 **/
@Controller
@RequestMapping("/teach")
public class GradeController {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private GradeService gradeService;

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
            grade = gradeService.findById(id);
        }
        model.addAttribute("grade",grade);
        //查询全部学院
        model.addAttribute("departmentList",departmentService.findAll());
        return "teach-manage/grade/dialog";
    }

    /**
     * 新增班级
     * @param grade
     * @return
     */
    @PostMapping("/grade")
    @ResponseBody
    public ResultVO saveGrade(Grade grade){
        Grade dbMajor = gradeService.findByNo(grade.getMajorId(),grade.getGradeNum());
        if (dbMajor != null){
            return ResultUtils.failed("该专业下的班级序号已存在");
        }
        Integer result = gradeService.saveOrUpdate(grade);
        return result.equals(ConstantUtils.SUCCESS)?ResultUtils.success("保存成功"):ResultUtils.failed("保存失败,请重新保存");
    }

    /**
     * 更新班级
     * @param grade
     * @param id
     * @return
     */
    @PutMapping("/grade/{id}")
    @ResponseBody
    public ResultVO updateMajor(Grade grade,@PathVariable("id") String id){
        Grade dbGrade = gradeService.findByNo(grade.getMajorId(),grade.getGradeNum());
        if (dbGrade != null && !dbGrade.getId().equals(id)){
            return ResultUtils.failed("该专业下的班级序号已存在");
        }
        Integer result = gradeService.saveOrUpdate(grade);
        return result.equals(ConstantUtils.SUCCESS)?ResultUtils.success("修改成功"):ResultUtils.failed("修改失败,请重新修改");
    }

    /**
     * table查询专业列表
     * @param rows
     * @param page
     * @param majorId
     * @param gradeName
     * @param departmentId
     * @return
     */
    @GetMapping("/queryGrade")
    @ResponseBody
    public TableModel queryGrade(@RequestParam("rows") Integer rows, @RequestParam("page") Integer page,
                                 @RequestParam(value = "majorId",required = false)String majorId,
                                 @RequestParam(value = "gradeName",required = false)String gradeName,
                                 @RequestParam(value = "departmentId",required = false)String departmentId){
        TableModel tableModel = new TableModel();
        Map<String,Object> queryMap = new HashMap<>(8);
        queryMap.put("pageSize",rows);
        queryMap.put("pageNum",page + 1);
        queryMap.put("majorId",majorId);
        queryMap.put("gradeName",gradeName);
        queryMap.put("departmentId",departmentId);
        Map<String,Object> resultMap = gradeService.queryGrade(queryMap);
        tableModel.setRows((List) resultMap.get("list"));
        tableModel.setTotal((Integer) resultMap.get("count"));
        return tableModel;
    }

    /**
     * 删除专业
     * @param id
     * @return
     */
    @DeleteMapping("/grade/{id}")
    @ResponseBody
    public ResultVO deleteGrade(@PathVariable("id") String id){
        Integer result = gradeService.delete(id);
        return result.equals(ConstantUtils.SUCCESS)?ResultUtils.success("删除成功"):ResultUtils.failed("删除失败,请重新删除");
    }

    /**
     * 根据专业id查询所有班级
     * @param id
     * @return
     */
    @GetMapping("/{id}/grade")
    @ResponseBody
    public ResultVO getGradeByMajorId(@PathVariable("id") String id){
        List<Grade> gradeList = gradeService.findByMajorId(id);
        return (gradeList != null)?ResultUtils.success("查询成功",gradeList):ResultUtils.failed("查询失败");
    }

}
