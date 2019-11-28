package com.dtdhehe.studentscore.service;

import com.dtdhehe.studentscore.entity.Wrap;

/**
 * @author 陈姗姗
 * @version 1.0.0
 * @date 2019/11/28 17:56
 * @description
 **/
public interface WrapService {

    /**
     * 根据id查找对象
     * @param id
     * @return
     */
    Wrap findById(String id);

}
