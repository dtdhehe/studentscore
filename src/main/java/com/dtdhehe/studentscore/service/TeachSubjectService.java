package com.dtdhehe.studentscore.service;

import com.dtdhehe.studentscore.entity.TeachSubject;

import java.util.Map;

/**
 * @author 陈姗姗
 * @version 1.0.0
 * @date 2019/11/24 16:26
 * @description
 **/
public interface TeachSubjectService {
    /**
     * 根据id查找课程
     * @param id
     * @return
     */
    TeachSubject findById(String id);

    /**
     * 根据学科id和班级id查找课程
     * @param subjectId
     * @param gradeId
     * @return
     */
    TeachSubject findBySubjectIdAndGradeId(String subjectId,String gradeId);

    /**
     * 新增/修改课程
     * @param teachSubject
     * @return
     */
    Integer saveOrUpdate(TeachSubject teachSubject);

    /**
     * 根据条件查询课程信息
     * @param queryMap
     * @return
     */
    Map<String,Object> queryTeachSubject(Map<String,Object> queryMap);

    /**
     * 删除课程
     * @param id
     * @return
     */
    Integer delete(String id);
}
