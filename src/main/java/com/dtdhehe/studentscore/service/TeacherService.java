package com.dtdhehe.studentscore.service;

import com.dtdhehe.studentscore.entity.Teacher;

import java.util.List;
import java.util.Map;

/**
 * @author 陈姗姗
 * @version 1.0.0
 * @date 2019/11/20 16:16
 * @description
 **/
public interface TeacherService {
    /**
     * 根据id查找教师
     * @param id
     * @return
     */
    Teacher findById(String id);

    /**
     * 根据教师编号查找教师
     * @param tno
     * @return
     */
    Teacher findByTno(String tno);

    /**
     * 新增/修改教师
     * @param teacher
     * @return
     */
    Integer saveOrUpdate(Teacher teacher);

    /**
     * 根据条件查询教师信息
     * @param queryMap
     * @return
     */
    Map<String,Object> queryTeacher(Map<String,Object> queryMap);

    /**
     * 删除教师
     * @param id
     * @return
     */
    Integer delete(String id);

    /**
     * 根据学院id查询所有教师
     * @param departmentId
     * @return
     */
    List<Teacher> findByDepartmentId(String departmentId);

    /**
     * 根据用户id查找教师
     * @param userId
     * @return
     */
    Teacher findByUserId(String userId);

}
