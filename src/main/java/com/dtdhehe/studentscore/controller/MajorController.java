package com.dtdhehe.studentscore.controller;

import com.dtdhehe.studentscore.entity.Major;
import com.dtdhehe.studentscore.service.DepartmentService;
import com.dtdhehe.studentscore.service.MajorService;
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
 * @date 2019/11/14 17:05
 * @description
 **/
@Controller
@RequestMapping("/teach")
public class MajorController {

    @Autowired
    private MajorService majorService;
    @Autowired
    private DepartmentService departmentService;

    /**
     * 专业管理
     * @return
     */
    @GetMapping("/getMajorManage")
    public String getMajorManage(){
        return "teach-manage/major/major";
    }

    /**
     * 专业新增编辑页面
     * @return
     */
    @GetMapping("/getMajorInfo")
    public String getMajorInfo(@RequestParam(value = "id",required = false)String id, Model model){
        Major major = new Major();
        if (!StringUtils.isEmpty(id)){
            //id不为空，则为编辑。查询该对象
            major = majorService.findById(id);
        }
        model.addAttribute("major",major);
        //查询全部学院
        model.addAttribute("departmentList",departmentService.findAll());
        return "teach-manage/major/dialog";
    }

}
