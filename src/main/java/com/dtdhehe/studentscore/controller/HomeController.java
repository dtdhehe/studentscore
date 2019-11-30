package com.dtdhehe.studentscore.controller;

import com.dtdhehe.studentscore.entity.Wrap;
import com.dtdhehe.studentscore.service.WrapService;
import com.dtdhehe.studentscore.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author 陈姗姗
 * @version 1.0.0
 * @date 2019/11/8 23:16
 * @description
 **/
@Controller
public class HomeController {

    @Autowired
    private WrapService wrapService;

    @RequestMapping("/index")
    public String index(){
        return "main";
    }

    @RequestMapping("/home")
    public String home(Model model){
        String now = DateUtils.formatDate(new Date(),"yyyy年MM月dd");
        model.addAttribute("year",now.substring(0,4));
        model.addAttribute("date",now.substring(4,now.length()));
        SimpleDateFormat format = new SimpleDateFormat("EEEE");
        String currSun = format.format(new Date());
        model.addAttribute("currSun",currSun);
        //获得首页轮播图
        List<Wrap> wrapList = wrapService.queryAll();
        model.addAttribute("wrapList",wrapList);
        return "main-content";
    }

}
