package com.dtdhehe.studentscore.entity;

import lombok.Data;

/**
 * @author 陈姗姗
 * @version 1.0.0
 * @date 2019/11/19 16:16
 * @description
 **/
@Data
public class UserRole {
    private String id;

    private String validFlag;

    private String createTime;

    private String updateTime;

    private String userId;

    private String roleId;
}
