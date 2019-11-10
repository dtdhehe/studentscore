package com.dtdhehe.studentscore.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 陈姗姗
 * @version 1.0.0
 * @date 2019/11/10 16:15
 * @description
 **/
@Data
public class TableModel implements Serializable {
    private static final long serialVersionUID = 6315948894116972222L;
    private Integer total = 0;
    private List rows = new ArrayList();
}
