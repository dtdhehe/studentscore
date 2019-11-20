package com.dtdhehe.studentscore.mapper;

import com.dtdhehe.studentscore.entity.Subject;
import com.dtdhehe.studentscore.provider.SubjectMapperProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * @author 陈姗姗
 * @version 1.0.0
 * @date 2019/11/20 15:26
 * @description
 **/
@Mapper
public interface SubjectMapper {
    /**
     * 根据id查找学科
     * @param id
     * @return
     */
    @Select("select t.* from subject t where t.id=#{id}")
    Subject findById(@Param("id") String id);

    /**
     * 根据学科编号查找学科
     * @param subjectNo
     * @return
     */
    @Select("select t.* from subject t where t.subject_no=#{subjectNo} and t.valid_flag ='1'")
    Subject findByNo(@Param("subjectNo") String subjectNo);

    /**
     * 新增学科
     * @param subject
     * @return
     */
    @Insert("insert into subject(id,valid_flag,create_time,update_time,subject_no,subject_name,credit) VALUES(#{id},#{validFlag},#{createTime},#{updateTime},#{subjectNo},#{subjectName},#{credit})")
    Integer save(Subject subject);

    /**
     * 更新学科
     * @param subject
     * @return
     */
    @Update("update subject set valid_flag=#{validFlag},update_time=#{updateTime},subject_no=#{subjectNo},subject_name=#{subjectName},credit=#{credit} where id=#{id}")
    Integer update(Subject subject);

    /**
     * 查询学科列表
     * @param queryMap
     * @return
     */
    @SelectProvider(type = SubjectMapperProvider.class,method = "querySubject")
    List<Subject> querySubject(Map<String,Object> queryMap);
}
