package com.dtdhehe.studentscore.mapper;

import com.dtdhehe.studentscore.entity.Score;
import com.dtdhehe.studentscore.provider.ScoreMapperProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * @author 陈姗姗
 * @version 1.0.0
 * @date 2019/11/25 11:12
 * @description
 **/
@Mapper
public interface ScoreMapper {

    /**
     * 查询成绩列表
     * @param queryMap
     * @return
     */
    @SelectProvider(type = ScoreMapperProvider.class,method = "queryScore")
    List<Map<String,Object>> queryScore(Map<String,Object> queryMap);

    /**
     * 根据id查找课程
     * @param id
     * @return
     */
    @Select("select t.* from score t where t.id=#{id}")
    Score findById(@Param("id") String id);

    /**
     * 录入成绩
     * @param score
     * @return
     */
    @Insert("insert into score(id,valid_flag,create_time,update_time,student_id,teach_subject_id,normal_score,midterm_score,endterm_score,total_score) VALUES(#{id},#{validFlag},#{createTime},#{updateTime},#{studentId},#{teachSubjectId},#{normalScore},#{midtermScore},#{endtermScore},#{totalScore})")
    Integer save(Score score);

    /**
     * 更新成绩
     * @param score
     * @return
     */
    @Update("update score set valid_flag=#{validFlag},update_time=#{updateTime},student_id=#{studentId},teach_subject_id=#{teachSubjectId},normal_score=#{normalScore},midterm_score=#{midtermScore},endterm_score=#{endtermScore},total_score=#{totalScore} where id=#{id}")
    Integer update(Score score);

}
