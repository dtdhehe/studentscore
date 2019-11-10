package com.dtdhehe.studentscore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 陈姗姗
 * @version 1.0.0
 * @date 2019/11/8 23:16
 * @description
 **/
@Controller
public class HomeController {

    @RequestMapping("/index")
    public String index(){
        return "main";
    }

}
