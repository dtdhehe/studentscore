package com.dtdhehe.studentscore.controller;

import com.dtdhehe.studentscore.entity.Wrap;
import com.dtdhehe.studentscore.service.WrapService;
import com.dtdhehe.studentscore.util.ConstantUtils;
import com.dtdhehe.studentscore.util.DateUtils;
import com.dtdhehe.studentscore.util.ResultUtils;
import com.dtdhehe.studentscore.vo.ResultVO;
import com.dtdhehe.studentscore.vo.TableModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 陈姗姗
 * @version 1.0.0
 * @date 2019/11/28 17:52
 * @description
 **/
@Controller
@RequestMapping("/wrap")
public class WrapController {

    @Autowired
    private WrapService wrapService;

    /**
     * 轮播图管理
     * @return
     */
    @GetMapping("/getWrapManage")
    public String getWrapManage(){
        return "wrap/wrap";
    }

    /**
     * 轮播图新增编辑页面
     * @return
     */
    @GetMapping("/getWrapInfo")
    public String getWrapInfo(@RequestParam(value = "id",required = false)String id, Model model){
        Wrap wrap = new Wrap();
        if (!StringUtils.isEmpty(id)){
            //id不为空，则为编辑。查询该对象
            wrap = wrapService.findById(id);
        }
        model.addAttribute("wrap",wrap);
        return "wrap/dialog";
    }

    /**
     * 上传图片
     * @param file
     * @return
     */
    @PostMapping("/upload")
    @ResponseBody
    public Map<String,Object> uploadImg(MultipartFile file){
        File f = new File("D:/uploads");
        if (!f.exists()){
            f.mkdirs();
        }
        Map<String,Object> resultMap = new HashMap<>();
        try {
            String fileName = file.getOriginalFilename();
            String fileNewName = DateUtils.getCurrentDateTime()+fileName;
            //新文件路径
            String resultUrl = "D:/uploads/"+ fileNewName;
            File upFile = new File(resultUrl);
            file.transferTo(upFile);
            Map<String,Object> data = new HashMap<>();
            //因为浏览器原因，设置虚拟路径为   /uploads/
            data.put("src","/uploads/"+fileNewName);
            resultMap.put("code",0);
            resultMap.put("msg","上传成功");
            resultMap.put("data",data);
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("code",1);
            resultMap.put("msg","上传失败");
            resultMap.put("data",null);
        }
        return resultMap;
    }

    /**
     * 新增轮播图
     * @param wrap
     * @return
     */
    @PostMapping("/")
    @ResponseBody
    public ResultVO saveTeachSubject(Wrap wrap){
        Integer result = wrapService.saveOrUpdate(wrap);
        return result.equals(ConstantUtils.SUCCESS)? ResultUtils.success("保存成功"):ResultUtils.failed("保存失败,请重新保存");
    }

    /**
     * table查询轮播图列表
     * @param rows
     * @param page
     * @param wrapName
     * @return
     */
    @GetMapping("/queryWrap")
    @ResponseBody
    public TableModel queryWrap(@RequestParam("rows") Integer rows, @RequestParam("page") Integer page,
                                  @RequestParam(value = "wrapName",required = false)String wrapName,
                                  @RequestParam(value = "wrapStatus",required = false)String wrapStatus){
        TableModel tableModel = new TableModel();
        Map<String,Object> queryMap = new HashMap<>(16);
        queryMap.put("pageSize",rows);
        queryMap.put("pageNum",page + 1);
        queryMap.put("wrapName",wrapName);
        queryMap.put("wrapStatus",wrapStatus);
        Map<String,Object> resultMap = wrapService.queryWrap(queryMap);
        tableModel.setRows((List) resultMap.get("list"));
        tableModel.setTotal((Integer) resultMap.get("count"));
        return tableModel;
    }

    /**
     * 更新轮播图
     * @param wrap
     * @return
     */
    @PutMapping("/{id}")
    @ResponseBody
    public ResultVO updateWrap(Wrap wrap){
        Integer result = wrapService.saveOrUpdate(wrap);
        return result.equals(ConstantUtils.SUCCESS)?ResultUtils.success("修改成功"):ResultUtils.failed("修改失败,请重新修改");
    }

    /**
     * 删除轮播图
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @ResponseBody
    public ResultVO deleteWrap(@PathVariable("id") String id){
        Integer result = wrapService.delete(id);
        return result.equals(ConstantUtils.SUCCESS)?ResultUtils.success("删除成功"):ResultUtils.failed("删除失败,请重新删除");
    }

}
