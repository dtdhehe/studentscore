package com.dtdhehe.studentscore.entity;

import lombok.Data;

/**
 * @author 陈姗姗
 * @version 1.0
 * @date 2019/11/5 23:13
 * @description
 **/
@Data
public class Role {
    private String id;

    private String validFlag;

    private String createTime;

    private String updateTime;

    private String roleName;
}
