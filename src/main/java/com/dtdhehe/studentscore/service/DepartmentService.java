package com.dtdhehe.studentscore.service;

import com.dtdhehe.studentscore.entity.Department;

import java.util.Map;

/**
 * @author 陈姗姗
 * @version 1.0.0
 * @date 2019/11/9 23:31
 * @description
 **/
public interface DepartmentService {

    /**
     * 根据学院编号查找学院
     * @param departmentNo
     * @return
     */
    Department findByNo(String departmentNo);

    /**
     * 新增/修改学院
     * @param department
     * @return
     */
    Integer saveOrUpdate(Department department);

    /**
     * 根据条件查询学院信息
     * @param queryMap
     * @return
     */
    Map<String,Object> queryDepartment(Map<String,Object> queryMap);
}
