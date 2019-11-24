package com.dtdhehe.studentscore.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * @author 陈姗姗
 * @version 1.0.0
 * @date 2019/11/24 16:42
 * @description
 **/
public class TeachSubjectMapperProvider {
    private Logger log =  LoggerFactory.getLogger(TeachSubjectMapperProvider.class);

    public String queryTeachSubject(Map<String,Object> queryMap){
        StringBuilder where = new StringBuilder();
        if (queryMap.isEmpty()){
            where.append("t.valid_flag = '1'");
        }else {
            where.append("t.valid_flag = '1'");
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
                where.append(" and t.school_year =#{schoolYear}");
            }
            if (!StringUtils.isEmpty(queryMap.get("schoolTerm"))){
                where.append(" and t.school_term =#{schoolTerm}");
            }
        }
        String sql = "select t.*,d.department_name,m.major_name,g.grade_name,te.name,s.subject_name " +
                " from teach_subject t left join department d on t.department_id = d.id " +
                " left join major m on t.major_id = m.id left join grade g on t.grade_id = g.id " +
                " left join teacher te on t.teacher_id = te.id left join subject s on t.subject_id = s.id where " +
                 where + "order by t.school_year desc,t.school_term desc,t.grade_id asc";
        if (log.isDebugEnabled()){
            log.debug("SQL语句为:" + sql);
        }
        return sql;
    }
}
