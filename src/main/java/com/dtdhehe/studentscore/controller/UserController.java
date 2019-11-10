package com.dtdhehe.studentscore.controller;

import com.dtdhehe.studentscore.entity.User;
import com.dtdhehe.studentscore.service.UserService;
import com.dtdhehe.studentscore.util.ConstantUtils;
import com.dtdhehe.studentscore.util.ResultUtils;
import com.dtdhehe.studentscore.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 陈珊珊
 * @version 1.0
 * @date 2019/11/5 23:36
 * @description
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 注册用户
     * @param user
     * @return
     */
    @PostMapping("/")
    public ResultVO registeUser(User user){
        User dbUser = userService.findByUserName(user.getUserName());
        if (dbUser != null){
            return ResultUtils.failed("该用户名已被使用");
        }
        Integer result = userService.saveOrUpdateUser(user);
        return result.equals(ConstantUtils.SUCCESS)?ResultUtils.success("注册成功"):ResultUtils.failed("注册失败,请重新注册");
    }

}
