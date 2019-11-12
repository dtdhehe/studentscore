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
    @Select("select t.* from department t where t.department_no=#{departmentNo} and t.valid_flag ='1'")
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

    /**
     * 根据学院id查找学院
     * @param id
     * @return
     */
    @Select("select t.* from department t where t.id=#{id}")
    Department findById(@Param("id") String id);

    /**
     * 更新学院
     * @param department
     * @return
     */
    @Update("update department set update_time=#{updateTime},department_no=#{departmentNo},department_name=#{departmentName} where id=#{id}")
    Integer update(Department department);

    /**
     * 删除学院(逻辑删除,将valid_flag置为0)
     * @param id
     * @param updateTime
     * @return
     */
    @Update("update department set update_time=#{updateTime},valid_flag='0' where id=#{id}")
    Integer delete(@Param("id") String id,@Param("updateTime") String updateTime);

}
