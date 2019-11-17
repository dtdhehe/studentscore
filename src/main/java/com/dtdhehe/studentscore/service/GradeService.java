package com.dtdhehe.studentscore.service;

import com.dtdhehe.studentscore.entity.Grade;

import java.util.Map;

/**
 * @author 陈姗姗
 * @version 1.0.0
 * @date 2019/11/17 15:57
 * @description
 **/
public interface GradeService {
    /**
     * 根据id查找班级
     * @param id
     * @return
     */
    Grade findById(String id);

    /**
     * 根据专业id,班级编号查找专业
     * @param majorId
     * @param gradeNum
     * @return
     */
    Grade findByNo(String majorId,Integer gradeNum);

    /**
     * 新增/修改班级
     * @param grade
     * @return
     */
    Integer saveOrUpdate(Grade grade);

    /**
     * 根据条件查询班级信息
     * @param queryMap
     * @return
     */
    Map<String,Object> queryGrade(Map<String,Object> queryMap);

    /**
     * 删除班级
     * @param id
     * @return
     */
    Integer delete(String id);
}
