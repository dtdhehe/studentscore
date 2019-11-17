package com.dtdhehe.studentscore.entity;

import lombok.Data;

/**
 * @author 陈姗姗
 * @version 1.0.0
 * @date 2019/11/15 17:53
 * @description
 **/
@Data
public class Grade {
    private String id;

    private String validFlag;

    private String createTime;

    private String updateTime;

    private String departmentId;

    private String majorId;

    private Integer gradeNum;

    private String gradeName;
}
