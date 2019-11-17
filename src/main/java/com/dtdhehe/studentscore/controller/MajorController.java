package com.dtdhehe.studentscore.controller;

import com.dtdhehe.studentscore.entity.Major;
import com.dtdhehe.studentscore.service.DepartmentService;
import com.dtdhehe.studentscore.service.MajorService;
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
    public String getMajorManage(Model model){
        //查询全部学院
        model.addAttribute("departmentList",departmentService.findAll());
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

    /**
     * 新增专业
     * @param major
     * @return
     */
    @PostMapping("/major")
    @ResponseBody
    public ResultVO saveMajor(Major major){
        Major dbMajor = majorService.findByNo(major.getMajorNo());
        if (dbMajor != null){
            return ResultUtils.failed("该专业编号已存在");
        }
        Integer result = majorService.saveOrUpdate(major);
        return result.equals(ConstantUtils.SUCCESS)?ResultUtils.success("保存成功"):ResultUtils.failed("保存失败,请重新保存");
    }

    /**
     * table查询专业列表
     * @param rows
     * @param page
     * @param majorNo
     * @param majorName
     * @return
     */
    @GetMapping("/queryMajor")
    @ResponseBody
    public TableModel queryMajor(@RequestParam("rows") Integer rows, @RequestParam("page") Integer page,
                                 @RequestParam(value = "majorNo",required = false)String majorNo,
                                 @RequestParam(value = "majorName",required = false)String majorName,
                                 @RequestParam(value = "departmentId",required = false)String departmentId){
        TableModel tableModel = new TableModel();
        Map<String,Object> queryMap = new HashMap<>(8);
        queryMap.put("pageSize",rows);
        queryMap.put("pageNum",page + 1);
        queryMap.put("majorNo",majorNo);
        queryMap.put("majorName",majorName);
        queryMap.put("departmentId",departmentId);
        Map<String,Object> resultMap = majorService.queryMajor(queryMap);
        tableModel.setRows((List) resultMap.get("list"));
        tableModel.setTotal((Integer) resultMap.get("count"));
        return tableModel;
    }

    /**
     * 更新专业
     * @param major
     * @param id
     * @return
     */
    @PutMapping("/major/{id}")
    @ResponseBody
    public ResultVO updateMajor(Major major,@PathVariable("id") String id){
        Major dbMajor = majorService.findByNo(major.getMajorNo());
        if (dbMajor != null && !dbMajor.getId().equals(id)){
            return ResultUtils.failed("该专业编号已存在");
        }
        Integer result = majorService.saveOrUpdate(major);
        return result.equals(ConstantUtils.SUCCESS)?ResultUtils.success("修改成功"):ResultUtils.failed("修改失败,请重新修改");
    }

    /**
     * 删除专业
     * @param id
     * @return
     */
    @DeleteMapping("/major/{id}")
    @ResponseBody
    public ResultVO deleteMajor(@PathVariable("id") String id){
        Integer result = majorService.delete(id);
        return result.equals(ConstantUtils.SUCCESS)?ResultUtils.success("删除成功"):ResultUtils.failed("删除失败,请重新删除");
    }

    /**
     * 根据学院id查询所有专业
     * @param id
     * @return
     */
    @GetMapping("/{id}/major")
    @ResponseBody
    public ResultVO getMajorByDepartmentId(@PathVariable("id") String id){
        List<Major> majorList = majorService.findByDepartmentId(id);
        return (majorList != null)?ResultUtils.success("查询成功",majorList):ResultUtils.failed("查询失败");
    }

}
