package com.dtdhehe.studentscore.mapper;

import com.dtdhehe.studentscore.entity.Student;
import com.dtdhehe.studentscore.provider.StudentMapperProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * @author 陈珊珊
 * @version 1.0
 * @date 2019/11/18 22:33
 * @description
 **/
@Mapper
public interface StudentMapper {
    /**
     * 根据id查找学生
     * @param id
     * @return
     */
    @Select("select t.* from student t where t.id=#{id}")
    Student findById(@Param("id") String id);

    /**
     * 根据学号查找学生
     * @param sno
     * @return
     */
    @Select("select t.* from student t where t.sno=#{sno} and t.valid_flag ='1'")
    Student findBySno(@Param("sno")String sno);

    /**
     * 新增学生
     * @param student
     * @return
     */
    @Insert("insert into student(id,valid_flag,create_time,update_time,user_id,sno,birthday,name,grade_id,major_id,department_id) VALUES(#{id},#{validFlag},#{createTime},#{updateTime},#{userId},#{sno},#{birthday},#{name},#{gradeId},#{majorId},#{departmentId})")
    Integer save(Student student);

    /**
     * 更新学生
     * @param student
     * @return
     */
    @Update("update student set update_time=#{updateTime},user_id=#{userId},sno=#{sno},birthday=#{birthday},name=#{name},grade_id=#{gradeId},major_id=#{majorId},department_id=#{departmentId} where id=#{id}")
    Integer update(Student student);

    /**
     * 查询学生列表
     * @param queryMap
     * @return
     */
    @SelectProvider(type = StudentMapperProvider.class,method = "queryStudent")
    List<Map<String,Object>> queryStudent(Map<String,Object> queryMap);

    /**
     * 删除学生(逻辑删除,将valid_flag置为0)
     * @param id
     * @param updateTime
     * @return
     */
    @Update("update student set update_time=#{updateTime},valid_flag='0' where id=#{id}")
    Integer delete(@Param("id") String id,@Param("updateTime") String updateTime);

    /**
     * 根据班级id查询所有学生
     * @param gradeId
     * @return
     */
    @Select("select t.* from student t where t.grade_id=#{gradeId} and t.valid_flag='1'")
    List<Student> findByGradeId(@Param("gradeId") String gradeId);
}
