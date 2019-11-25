package com.dtdhehe.studentscore.entity;

import lombok.Data;

/**
 * @author 陈姗姗
 * @version 1.0.0
 * @date 2019/11/25 16:28
 * @description
 **/
@Data
public class Score {

    private String id;

    private String validFlag;

    private String createTime;

    private String updateTime;

    private String studentId;

    private String teachSubjectId;

    private Integer normalScore;

    private Integer midtermScore;

    private Integer endtermScore;

    private Integer totalScore;

}
