package com.dtdhehe.studentscore.controller;

import com.dtdhehe.studentscore.entity.Subject;
import com.dtdhehe.studentscore.service.SubjectService;
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

    /**
     * 新增学科
     * @param subject
     * @return
     */
    @PostMapping("/subject")
    @ResponseBody
    public ResultVO saveSubject(Subject subject){
        Subject dbSubject = subjectService.findByNo(subject.getSubjectNo());
        if (dbSubject != null){
            return ResultUtils.failed("该学科编号已存在");
        }
        Integer result = subjectService.saveOrUpdate(subject);
        return result.equals(ConstantUtils.SUCCESS)?ResultUtils.success("保存成功"):ResultUtils.failed("保存失败,请重新保存");
    }

    /**
     * table查询学科列表
     * @param rows
     * @param page
     * @param subjectNo
     * @param subjectName
     * @return
     */
    @GetMapping("/querySubject")
    @ResponseBody
    public TableModel querySubject(@RequestParam("rows") Integer rows, @RequestParam("page") Integer page,
                                 @RequestParam(value = "subjectNo",required = false)String subjectNo,
                                 @RequestParam(value = "subjectName",required = false)String subjectName){
        TableModel tableModel = new TableModel();
        Map<String,Object> queryMap = new HashMap<>(8);
        queryMap.put("pageSize",rows);
        queryMap.put("pageNum",page + 1);
        queryMap.put("subjectNo",subjectNo);
        queryMap.put("subjectName",subjectName);
        Map<String,Object> resultMap = subjectService.querySubject(queryMap);
        tableModel.setRows((List) resultMap.get("list"));
        tableModel.setTotal((Integer) resultMap.get("count"));
        return tableModel;
    }

    /**
     * 更新学科
     * @param subject
     * @param id
     * @return
     */
    @PutMapping("/subject/{id}")
    @ResponseBody
    public ResultVO updateSubject(Subject subject,@PathVariable("id") String id){
        Subject dbSubject = subjectService.findByNo(subject.getSubjectNo());
        if (dbSubject != null && !dbSubject.getId().equals(id)){
            return ResultUtils.failed("该学科编号已存在");
        }
        Integer result = subjectService.saveOrUpdate(subject);
        return result.equals(ConstantUtils.SUCCESS)?ResultUtils.success("修改成功"):ResultUtils.failed("修改失败,请重新修改");
    }

    /**
     * 删除学科
     * @param id
     * @return
     */
    @DeleteMapping("/subject/{id}")
    @ResponseBody
    public ResultVO deleteMajor(@PathVariable("id") String id){
        Integer result = subjectService.delete(id);
        return result.equals(ConstantUtils.SUCCESS)?ResultUtils.success("删除成功"):ResultUtils.failed("删除失败,请重新删除");
    }

}
