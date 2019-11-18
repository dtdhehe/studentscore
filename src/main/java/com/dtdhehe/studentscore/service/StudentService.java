package com.dtdhehe.studentscore.service;

import com.dtdhehe.studentscore.entity.Student;

import java.util.List;
import java.util.Map;

/**
 * @author 陈珊珊
 * @version 1.0
 * @date 2019/11/18 22:25
 * @description
 **/
public interface StudentService {
    /**
     * 根据id查找学生
     * @param id
     * @return
     */
    Student findById(String id);

    /**
     * 根据学号查找学生
     * @param sno
     * @return
     */
    Student findBySno(String sno);

    /**
     * 新增/修改学生
     * @param student
     * @return
     */
    Integer saveOrUpdate(Student student);

    /**
     * 根据条件查询专业信息
     * @param queryMap
     * @return
     */
    Map<String,Object> queryStudent(Map<String,Object> queryMap);

    /**
     * 删除学生
     * @param id
     * @return
     */
    Integer delete(String id);

    /**
     * 根据班级id查询所有学生
     * @param id
     * @return
     */
    List<Student> findByGradeId(String id);
}
