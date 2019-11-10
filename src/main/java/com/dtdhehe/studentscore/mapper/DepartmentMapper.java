package com.dtdhehe.studentscore.mapper;

import com.dtdhehe.studentscore.entity.Department;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * @author 陈姗姗
 * @version 1.0.0
 * @date 2019/11/9 23:27
 * @description
 **/
@Mapper
public interface DepartmentMapper {

    /**
     * 根据学院编号查找学院
     * @param departmentNo
     * @return
     */
    @Select("select t.* from department t where t.department_no=#{departmentNo}")
    Department findByNo(@Param("departmentNo") String departmentNo);

    /**
     * 新增学院
     * @param department
     * @return
     */
    @Insert("insert into department(id,valid_flag,create_time,update_time,department_no,department_name) VALUES(#{id},#{validFlag},#{createTime},#{updateTime},#{departmentNo},#{departmentName})")
    Integer save(Department department);

    /**
     * 查询学院列表
     * @param queryMap
     * @return
     */
    @SelectProvider(type = DepartmentMapperProvider.class,method = "queryDepartment")
    List<Department> queryDepartment(Map<String,Object> queryMap);
}
