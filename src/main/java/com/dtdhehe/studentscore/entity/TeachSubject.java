package com.dtdhehe.studentscore.entity;

import lombok.Data;

/**
 * @author 陈姗姗
 * @version 1.0.0
 * @date 2019/11/22 16:36
 * @description
 **/
@Data
public class TeachSubject {
    private String id;

    private String validFlag;

    private String createTime;

    private String updateTime;

    private String departmentId;

    private String majorId;

    private String gradeId;

    private String subjectId;

    private String subjectName;

    private String teacherId;

    private String teacherName;

    private String schoolYear;

    private String schoolTerm;
}
