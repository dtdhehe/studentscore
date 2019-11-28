package com.dtdhehe.studentscore.entity;

import lombok.Data;

/**
 * @author 陈姗姗
 * @version 1.0.0
 * @date 2019/11/28 17:55
 * @description
 **/
@Data
public class Wrap {

    private String id;

    private String validFlag;

    private String createTime;

    private String updateTime;

    private String wrapName;

    private String wrapDesc;

    private String wrapStatus;

    private String imgUrl;
}
