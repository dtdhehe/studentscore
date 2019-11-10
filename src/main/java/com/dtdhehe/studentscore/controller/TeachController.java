package com.dtdhehe.studentscore.controller;

import com.dtdhehe.studentscore.entity.Department;
import com.dtdhehe.studentscore.service.DepartmentService;
import com.dtdhehe.studentscore.util.ConstantUtils;
import com.dtdhehe.studentscore.util.ResultUtils;
import com.dtdhehe.studentscore.vo.ResultVO;
import com.dtdhehe.studentscore.vo.TableModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 陈姗姗
 * @version 1.0.0
 * @date 2019/11/9 22:54
 * @description
 **/
@Controller
@RequestMapping("/teach")
public class TeachController {

    @Autowired
    private DepartmentService departmentService;

    /**
     * 学院管理
     * @return
     */
    @GetMapping("/getCollegeManage")
    public String getCollegeManage(){
        return "teach-manage/college/college";
    }

    /**
     * 学院新增编辑页面
     * @return
     */
    @GetMapping("/getCollegeInfo")
    public String getCollegeInfo(){
        return "teach-manage/college/dialog";
    }

    /**
     * 新增学院
     * @param department
     * @return
     */
    @PostMapping("/college")
    @ResponseBody
    public ResultVO save(Department department){
        Department dbDepartment = departmentService.findByNo(department.getDepartmentNo());
        if (dbDepartment != null){
            return ResultUtils.failed("该学院编号已存在");
        }
        Integer result = departmentService.saveOrUpdate(department);
        return result.equals(ConstantUtils.SUCCESS)?ResultUtils.success("保存成功"):ResultUtils.failed("保存失败,请重新保存");
    }

    @GetMapping("/college")
    @ResponseBody
    public TableModel queryDepartment(@RequestParam("rows") Integer rows,@RequestParam("page") Integer page,
                                      @RequestParam(value = "departmentNo",required = false)String departmentNo,
                                      @RequestParam(value = "departmentName",required = false)String departmentName){
        TableModel tableModel = new TableModel();
        Map<String,Object> queryMap = new HashMap<>(8);
        queryMap.put("pageSize",rows);
        queryMap.put("pageNum",page + 1);
        queryMap.put("departmentNo",departmentNo);
        queryMap.put("departmentName",departmentName);
        Map<String,Object> resultMap = departmentService.queryDepartment(queryMap);
        tableModel.setRows((List) resultMap.get("list"));
        tableModel.setTotal((Integer) resultMap.get("count"));
        return tableModel;
    }

}
