package com.dtdhehe.studentscore.service;

import com.dtdhehe.studentscore.entity.Department;

import java.util.List;
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

    /**
     * 根据id查找对象
     * @param id
     * @return
     */
    Department findById(String id);

    /**
     * 根据id删除对象(逻辑删除)
     * @param id
     * @return
     */
    Integer delete(String id);

    /**
     * 查询所有学院
     * @return
     */
    List<Department> findAll();
}
