package com.dtdhehe.studentscore.controller;

import com.dtdhehe.studentscore.entity.Wrap;
import com.dtdhehe.studentscore.service.WrapService;
import com.dtdhehe.studentscore.util.ConstantUtils;
import com.dtdhehe.studentscore.util.DateUtils;
import com.dtdhehe.studentscore.util.ResultUtils;
import com.dtdhehe.studentscore.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
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
            data.put("src",resultUrl);
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

}
