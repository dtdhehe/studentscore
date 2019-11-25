package com.dtdhehe.studentscore.controller;

import com.dtdhehe.studentscore.entity.Score;
import com.dtdhehe.studentscore.service.DepartmentService;
import com.dtdhehe.studentscore.service.ScoreService;
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
 * @date 2019/11/25 10:19
 * @description
 **/
@Controller
@RequestMapping("/score")
public class ScoreController {

    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private ScoreService scoreService;

    /**
     * 成绩管理
     * @return
     */
    @GetMapping("/getScoreManage")
    public String getScoreManage(Model model){
        //查询全部学院
        model.addAttribute("departmentList",departmentService.findAll());
        return "score-manage/score/score";
    }

    /**
     * 成绩录入页面
     * @return
     */
    @GetMapping("/getScoreInfo")
    public String getScoreInfo(@RequestParam("tid")String tid,@RequestParam("tsid")String tsid,Model model){
        Map<String, Object> scoreMap = scoreService.queryScoreForEdit(tid, tsid);
        model.addAttribute("scoreMap",scoreMap);
        return "score-manage/score/dialog";
    }

    /**
     * table查询成绩列表
     * @param rows
     * @param page
     * @param subjectName
     * @return
     */
    @GetMapping("/queryScore")
    @ResponseBody
    public TableModel queryScore(@RequestParam("rows") Integer rows, @RequestParam("page") Integer page,
                                  @RequestParam(value = "subjectName",required = false)String subjectName,
                                  @RequestParam(value = "departmentId",required = false)String departmentId,
                                  @RequestParam(value = "majorId",required = false)String majorId,
                                  @RequestParam(value = "gradeId",required = false)String gradeId,
                                  @RequestParam(value = "teacherName",required = false)String teacherName,
                                  @RequestParam(value = "sno",required = false)String sno){
        TableModel tableModel = new TableModel();
        Map<String,Object> queryMap = new HashMap<>(16);
        queryMap.put("pageSize",rows);
        queryMap.put("pageNum",page + 1);
        queryMap.put("subjectName",subjectName);
        queryMap.put("departmentId",departmentId);
        queryMap.put("majorId",majorId);
        queryMap.put("gradeId",gradeId);
        queryMap.put("teacherName",teacherName);
        queryMap.put("sno",sno);
        Map<String,Object> resultMap = scoreService.queryScore(queryMap);
        tableModel.setRows((List) resultMap.get("list"));
        tableModel.setTotal((Integer) resultMap.get("count"));
        return tableModel;
    }

    /**
     * 录入成绩
     * @param score
     * @return
     */
    @PostMapping("/score")
    @ResponseBody
    public ResultVO saveScore(Score score){
        Integer result = scoreService.saveOrUpdate(score);
        return result.equals(ConstantUtils.SUCCESS)?ResultUtils.success("保存成功"):ResultUtils.failed("保存失败,请重新保存");
    }

    /**
     * 更新成绩
     * @param score
     * @param id
     * @return
     */
    @PutMapping("/score/{id}")
    @ResponseBody
    public ResultVO updateScore(Score score,@PathVariable("id") String id){
        Integer result = scoreService.saveOrUpdate(score);
        return result.equals(ConstantUtils.SUCCESS)?ResultUtils.success("修改成功"):ResultUtils.failed("修改失败,请重新修改");
    }

    /**
     * 删除成绩
     * @param id
     * @return
     */
    @DeleteMapping("/score/{id}")
    @ResponseBody
    public ResultVO deleteScore(@PathVariable("id") String id){
        Integer result = scoreService.delete(id);
        return result.equals(ConstantUtils.SUCCESS)?ResultUtils.success("删除成功"):ResultUtils.failed("删除失败,请重新删除");
    }

}
