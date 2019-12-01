package com.dtdhehe.studentscore.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * @author 陈姗姗
 * @version 1.0.0
 * @date 2019/11/25 11:25
 * @description
 **/
public class ScoreMapperProvider {

    private Logger log =  LoggerFactory.getLogger(ScoreMapperProvider.class);

    public String queryScore(Map<String,Object> queryMap){
        StringBuilder where = new StringBuilder();
        if (queryMap.isEmpty()){
            where.append("t.valid_flag = '1' and ts.id is not NULL");
        }else {
            where.append("t.valid_flag = '1' and ts.id is not NULL");
            if (!StringUtils.isEmpty(queryMap.get("sno"))){
                where.append(" and t.sno like concat('%',#{sno},'%')");
            }
            if (!StringUtils.isEmpty(queryMap.get("departmentId"))){
                where.append(" and t.department_id =#{departmentId}");
            }
            if (!StringUtils.isEmpty(queryMap.get("majorId"))){
                where.append(" and t.major_id =#{majorId}");
            }
            if (!StringUtils.isEmpty(queryMap.get("gradeId"))){
                where.append(" and t.grade_id =#{gradeId}");
            }
            if (!StringUtils.isEmpty(queryMap.get("subjectName"))){
                where.append(" and s.subject_name like concat('%',#{subjectName},'%')");
            }
            if (!StringUtils.isEmpty(queryMap.get("teacherName"))){
                where.append(" and te.name like concat('%',#{teacherName},'%')");
            }
            if (!StringUtils.isEmpty(queryMap.get("schoolYear"))){
                where.append(" and ts.school_year =#{schoolYear}");
            }
            if (!StringUtils.isEmpty(queryMap.get("schoolTerm"))){
                where.append(" and ts.school_term =#{schoolTerm}");
            }
            if (!StringUtils.isEmpty(queryMap.get("tid"))){
                where.append(" and t.id =#{tid}");
            }
            if (!StringUtils.isEmpty(queryMap.get("tsid"))){
                where.append(" and ts.id =#{tsid}");
            }
            if (!StringUtils.isEmpty(queryMap.get("teid"))){
                where.append(" and te.id =#{teid}");
            }
        }
        String sql = "SELECT t.id tid,ts.id tsid,sc.id sid,s.subject_name,te.`name` tname,d.department_name,m.major_name,g.grade_name,\n" +
                "t.sno,t.name sname,s.credit,ts.school_year,ts.school_term,\n" +
                "sc.normal_score,sc.midterm_score,sc.endterm_score,sc.total_score\n" +
                "FROM student t\n" +
                "LEFT JOIN grade g ON t.grade_id=g.id\n" +
                "LEFT JOIN major m on t.major_id=m.id\n" +
                "LEFT JOIN department d ON t.department_id=d.id\n" +
                "LEFT JOIN teach_subject ts ON t.grade_id=ts.grade_id\n" +
                "LEFT JOIN `subject` s ON ts.subject_id=s.id\n" +
                "LEFT JOIN teacher te ON ts.teacher_id=te.id\n" +
                "LEFT JOIN score sc ON t.id = sc.student_id AND ts.id = sc.teach_subject_id AND sc.valid_flag='1'\n" +
                "WHERE " + where;
        if ("1".equals(queryMap.get("orderType"))){
            sql = sql + " ORDER BY t.sno ASC";
        }else {
            sql = sql + " ORDER BY sc.total_score DESC";
        }
        if (log.isDebugEnabled()){
            log.debug("SQL语句为:" + sql);
        }
        return sql;
    }

}
