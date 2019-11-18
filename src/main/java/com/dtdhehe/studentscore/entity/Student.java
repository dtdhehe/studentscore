package com.dtdhehe.studentscore.entity;

import lombok.Data;

/**
 * @author 陈珊珊
 * @version 1.0
 * @date 2019/11/18 22:20
 * @description
 **/
@Data
public class Student {
    private String id;

    private String validFlag;

    private String createTime;

    private String updateTime;

    private String userId;

    private String sno;

    private String birthday;

    private String name;

    private String gradeId;

    private String majorId;

    private String departmentId;
}
