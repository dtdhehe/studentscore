package com.dtdhehe.studentscore.service;

import com.dtdhehe.studentscore.entity.Subject;

import java.util.Map;

/**
 * @author 陈姗姗
 * @version 1.0.0
 * @date 2019/11/18 10:40
 * @description
 **/
public interface SubjectService {
    /**
     * 根据id查找学科
     * @param id
     * @return
     */
    Subject findById(String id);

    /**
     * 根据学科编号查找学科
     * @param subjectNo
     * @return
     */
    Subject findByNo(String subjectNo);

    /**
     * 新增/修改学科
     * @param subject
     * @return
     */
    Integer saveOrUpdate(Subject subject);

    /**
     * 根据条件查询学科信息
     * @param queryMap
     * @return
     */
    Map<String,Object> querySubject(Map<String,Object> queryMap);

    /**
     * 删除学科
     * @param id
     * @return
     */
    Integer delete(String id);
}
