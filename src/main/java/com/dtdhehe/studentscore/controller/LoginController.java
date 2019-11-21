package com.dtdhehe.studentscore.controller;

import com.dtdhehe.studentscore.entity.User;
import com.dtdhehe.studentscore.service.UserService;
import com.dtdhehe.studentscore.util.ConstantUtils;
import com.dtdhehe.studentscore.util.ResultUtils;
import com.dtdhehe.studentscore.vo.ResultVO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author 陈姗姗
 * @version 1.0.0
 * @date 2019/11/6 18:16
 * @description
 **/
@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    /**
     * 注册用户
     * @param user
     * @return
     */
    @PostMapping("/user")
    public ResultVO registeUser(User user){
        User dbUser = userService.findByUserName(user.getUserName());
        if (dbUser != null){
            return ResultUtils.failed("该用户名已被使用");
        }
        Integer result = userService.saveOrUpdateUser(user);
        return result.equals(ConstantUtils.SUCCESS)?ResultUtils.success("注册成功"):ResultUtils.failed("注册失败,请重新注册");
    }

    /**
     * 登录页面
     * @return
     */
    @GetMapping("/")
    public String index(){
        return "login";
    }

    /**
     * 未登录
     * @return
     */
    @GetMapping("/unLogin")
    public String unLogin(){
        return "login";
    }

    /**
     * 登录页面
     * @param user
     * @return
     */
    @PostMapping("/login")
    @ResponseBody
    public ResultVO login(User user){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(),user.getPassword());
        try {
            subject.login(token);
            return ResultUtils.success("login");
        }catch (UnknownAccountException e){
            e.printStackTrace();
            return ResultUtils.failed("用户名不存在");
        }catch (DisabledAccountException e){
            e.printStackTrace();
            return ResultUtils.failed("该用户未激活");
        }catch (IncorrectCredentialsException e){
            e.printStackTrace();
            return ResultUtils.failed("密码错误");
        }
    }

}
