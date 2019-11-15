package com.dtdhehe.studentscore.entity;

import lombok.Data;

/**
 * @author 陈姗姗
 * @version 1.0.0
 * @date 2019/11/14 17:31
 * @description
 **/
@Data
public class Major {
    private String id;

    private String validFlag;

    private String createTime;

    private String updateTime;

    private String departmentId;

    private String majorNo;

    private String majorName;

    private String departmentName;
}
