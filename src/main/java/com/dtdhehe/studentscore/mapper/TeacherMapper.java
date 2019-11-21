package com.dtdhehe.studentscore.mapper;

import com.dtdhehe.studentscore.entity.Teacher;
import com.dtdhehe.studentscore.provider.TeacherMapperProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * @author 陈姗姗
 * @version 1.0.0
 * @date 2019/11/20 16:14
 * @description
 **/
@Mapper
public interface TeacherMapper {

    /**
     * 根据id查找教师
     * @param id
     * @return
     */
    @Select("select t.* from teacher t where t.id=#{id}")
    Teacher findById(@Param("id") String id);

    /**
     * 根据学号查找教师
     * @param tno
     * @return
     */
    @Select("select t.* from teacher t where t.tno=#{tno} and t.valid_flag ='1'")
    Teacher findByTno(@Param("tno")String tno);

    /**
     * 新增教师
     * @param teacher
     * @return
     */
    @Insert("insert into teacher(id,valid_flag,create_time,update_time,user_id,tno,birthday,name,department_id) VALUES(#{id},#{validFlag},#{createTime},#{updateTime},#{userId},#{tno},#{birthday},#{name},#{departmentId})")
    Integer save(Teacher teacher);

    /**
     * 更新教师
     * @param teacher
     * @return
     */
    @Update("update teacher set valid_flag=#{validFlag},update_time=#{updateTime},user_id=#{userId},tno=#{tno},birthday=#{birthday},name=#{name},department_id=#{departmentId} where id=#{id}")
    Integer update(Teacher teacher);

    /**
     * 查询教师列表
     * @param queryMap
     * @return
     */
    @SelectProvider(type = TeacherMapperProvider.class,method = "queryTeacher")
    List<Map<String,Object>> queryTeacher(Map<String,Object> queryMap);

    /**
     * 根据学院id查询所有教师
     * @param departmentId
     * @return
     */
    @Select("select t.* from teacher t where t.department_id=#{departmentId} and t.valid_flag='1'")
    List<Teacher> findByDepartmentId(@Param("departmentId") String departmentId);

    /**
     * 根据用户id查找教师
     * @param userId
     * @return
     */
    @Select("select t.* from teacher t where t.user_id=#{userId} and t.valid_flag='1'")
    Teacher findByUserId(@Param("userId") String userId);
}
