package com.dtdhehe.studentscore.entity;

import lombok.Data;

/**
 * @author 陈姗姗
 * @version 1.0.0
 * @date 2019/11/18 10:39
 * @description
 **/
@Data
public class Subject {
    private String id;

    private String validFlag;

    private String createTime;

    private String updateTime;

    private String subjectNo;

    private String subjectName;
}
