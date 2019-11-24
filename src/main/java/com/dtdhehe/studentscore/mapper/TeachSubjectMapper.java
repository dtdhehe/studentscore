package com.dtdhehe.studentscore.mapper;

import com.dtdhehe.studentscore.entity.TeachSubject;
import com.dtdhehe.studentscore.provider.TeachSubjectMapperProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * @author 陈姗姗
 * @version 1.0.0
 * @date 2019/11/24 16:33
 * @description
 **/
@Mapper
public interface TeachSubjectMapper {
    /**
     * 根据id查找课程
     * @param id
     * @return
     */
    @Select("select t.*,s.subject_name,te.name teacher_name from teach_subject t left join subject s on t.subject_id = s.id left join teacher te on t.teacher_id = te.id where t.id=#{id}")
    TeachSubject findById(@Param("id") String id);

    /**
     * 根据学科id和班级id查找课程
     * @param subjectId
     * @param gradeId
     * @return
     */
    @Select("select t.* from teach_subject t where t.subject_id=#{subjectId} and t.grade_id=#{gradeId} and t.valid_flag ='1'")
    TeachSubject findBySubjectIdAndGradeId(@Param("subjectId") String subjectId,@Param("gradeId") String gradeId);

    /**
     * 新增课程
     * @param teachSubject
     * @return
     */
    @Insert("insert into teach_subject(id,valid_flag,create_time,update_time,department_id,major_id,grade_id,subject_id,teacher_id,school_year,school_term) VALUES(#{id},#{validFlag},#{createTime},#{updateTime},#{departmentId},#{majorId},#{gradeId},#{subjectId},#{teacherId},#{schoolYear},#{schoolTerm})")
    Integer save(TeachSubject teachSubject);

    /**
     * 更新课程
     * @param teachSubject
     * @return
     */
    @Update("update teach_subject set valid_flag=#{validFlag},update_time=#{updateTime},department_id=#{departmentId},major_id=#{majorId},grade_id=#{gradeId},subject_id=#{subjectId},teacher_id=#{teacherId},school_year=#{schoolYear},school_term=#{schoolTerm} where id=#{id}")
    Integer update(TeachSubject teachSubject);

    /**
     * 查询课程列表
     * @param queryMap
     * @return
     */
    @SelectProvider(type = TeachSubjectMapperProvider.class,method = "queryTeachSubject")
    List<Map<String,Object>> queryTeachSubject(Map<String,Object> queryMap);
}
