package com.dtdhehe.studentscore.service;

import com.dtdhehe.studentscore.entity.Major;

/**
 * @author 陈姗姗
 * @version 1.0.0
 * @date 2019/11/14 17:32
 * @description
 **/
public interface MajorService {

    /**
     * 根据id查找专业
     * @param id
     * @return
     */
    Major findById(String id);

}
