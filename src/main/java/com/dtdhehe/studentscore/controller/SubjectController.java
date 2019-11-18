package com.dtdhehe.studentscore.controller;

import com.dtdhehe.studentscore.entity.Subject;
import com.dtdhehe.studentscore.service.SubjectService;
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
 * @date 2019/11/18 10:34
 * @description
 **/
@Controller
@RequestMapping("/teach")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    /**
     * 学科管理
     * @return
     */
    @GetMapping("/getSubjectManage")
    public String getSubjectManage(Model model){
        return "teach-manage/subject/subject";
    }

    /**
     * 学科新增编辑页面
     * @return
     */
    @GetMapping("/getSubjectInfo")
    public String getSubjectInfo(@RequestParam(value = "id",required = false)String id, Model model){
        Subject subject = new Subject();
        if (!StringUtils.isEmpty(id)){
            //id不为空，则为编辑。查询该对象
            subject = subjectService.findById(id);
        }
        model.addAttribute("subject",subject);
        return "teach-manage/subject/dialog";
    }

}
