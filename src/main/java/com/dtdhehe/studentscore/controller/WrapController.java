package com.dtdhehe.studentscore.controller;

import com.dtdhehe.studentscore.entity.Wrap;
import com.dtdhehe.studentscore.service.WrapService;
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

}
