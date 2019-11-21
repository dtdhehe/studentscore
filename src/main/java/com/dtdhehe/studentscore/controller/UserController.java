package com.dtdhehe.studentscore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 陈珊珊
 * @version 1.0
 * @date 2019/11/5 23:36
 * @description
 **/
@Controller
@RequestMapping("/user")
public class UserController {

    /**
     * 个人信息管理
     * @return
     */
    @GetMapping("/getUserManage")
    public String getUserManage(Model model){
        return "user-manage/userinfo/userinfo";
    }

    /**
     * 个人信息新增编辑页面
     * @return
     */
    @GetMapping("/getUserInfo")
    public String getUserInfo(@RequestParam(value = "id",required = false)String id, Model model){
        return "user-manage/userinfo/dialog";
    }

}
