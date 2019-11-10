package com.dtdhehe.studentscore.entity;

import lombok.Data;

import java.util.List;

/**
 * @author 陈珊珊
 * @version 1.0
 * @date 2019/11/5 23:06
 * @description
 **/
@Data
public class User {
    private String id;

    private String validFlag;

    private String createTime;

    private String updateTime;

    private String userName;

    private String password;

    private String email;

    private String phone;

    private String sex;

    private String status;

    private List<Role> roleList;

}
