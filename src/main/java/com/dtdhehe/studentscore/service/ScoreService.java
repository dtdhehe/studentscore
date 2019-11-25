package com.dtdhehe.studentscore.service;

import com.dtdhehe.studentscore.entity.Score;

import java.util.Map;

/**
 * @author 陈姗姗
 * @version 1.0.0
 * @date 2019/11/25 10:59
 * @description
 **/
public interface ScoreService {

    /**
     * 根据条件查询成绩信息
     * @param queryMap
     * @return
     */
    Map<String,Object> queryScore(Map<String,Object> queryMap);

    /**
     * 根据学生id和授课表id查询成绩
     * @param tid
     * @param tsid
     * @return
     */
    Map<String,Object> queryScoreForEdit(String tid,String tsid);

    /**
     * 录入或修改成绩
     * @param score
     * @return
     */
    Integer saveOrUpdate(Score score);

    /**
     * 删除成绩
     * @param id
     * @return
     */
    Integer delete(String id);

}
