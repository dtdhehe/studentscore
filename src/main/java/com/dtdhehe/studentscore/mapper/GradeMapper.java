package com.dtdhehe.studentscore.mapper;

import com.dtdhehe.studentscore.entity.Grade;
import com.dtdhehe.studentscore.provider.GradeMapperProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * @author 陈姗姗
 * @version 1.0.0
 * @date 2019/11/17 16:02
 * @description
 **/
@Mapper
public interface GradeMapper {

    /**
     * 根据id查找班级
     * @param id
     * @return
     */
    @Select("select t.* from grade t where t.id=#{id}")
    Grade findById(@Param("id") String id);

    /**
     * 根据专业id,班级编号查找班级
     * @param majorId
     * @param gradeNum
     * @return
     */
    @Select("select t.* from grade t where t.major_id=#{majorId} and t.grade_num=#{gradeNum} and t.valid_flag ='1'")
    Grade findByNo(@Param("majorId")String majorId,@Param("gradeNum") Integer gradeNum);

    /**
     * 新增班级
     * @param grade
     * @return
     */
    @Insert("insert into grade(id,valid_flag,create_time,update_time,department_id,major_id,grade_num,grade_name) VALUES(#{id},#{validFlag},#{createTime},#{updateTime},#{departmentId},#{majorId},#{gradeNum},#{gradeName})")
    Integer save(Grade grade);

    /**
     * 更新班级
     * @param grade
     * @return
     */
    @Update("update grade set update_time=#{updateTime},department_id=#{departmentId},major_id=#{majorId},grade_num=#{gradeNum},grade_name=#{gradeName} where id=#{id}")
    Integer update(Grade grade);

    /**
     * 查询班级列表
     * @param queryMap
     * @return
     */
    @SelectProvider(type = GradeMapperProvider.class,method = "queryGrade")
    List<Map<String,Object>> queryGrade(Map<String,Object> queryMap);

    /**
     * 删除班级(逻辑删除,将valid_flag置为0)
     * @param id
     * @param updateTime
     * @return
     */
    @Update("update grade set update_time=#{updateTime},valid_flag='0' where id=#{id}")
    Integer delete(@Param("id") String id,@Param("updateTime") String updateTime);

    /**
     * 根据专业id查询所有班级
     * @param majorId
     * @return
     */
    @Select("select t.* from grade t where t.major_id=#{majorId} and t.valid_flag='1'")
    List<Grade> findByMajorId(@Param("majorId") String majorId);
}
