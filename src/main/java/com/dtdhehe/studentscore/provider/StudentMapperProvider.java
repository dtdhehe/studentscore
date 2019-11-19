package com.dtdhehe.studentscore.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * @author 陈姗姗
 * @version 1.0.0
 * @date 2019/11/19 15:22
 * @description
 **/
public class StudentMapperProvider {

    private Logger log =  LoggerFactory.getLogger(StudentMapperProvider.class);

    public String queryStudent(Map<String,Object> queryMap){
        StringBuilder where = new StringBuilder();
        if (queryMap.isEmpty()){
            where.append("t.valid_flag = '1'");
        }else {
            where.append("t.valid_flag = '1'");
            if (!StringUtils.isEmpty(queryMap.get("majorId"))){
                //专业id
                where.append(" and t.major_id = #{majorId}");
            }
            if (!StringUtils.isEmpty(queryMap.get("gradeId"))){
                //班级id
                where.append(" and t.grade_id = #{gradeId}");
            }
            if (!StringUtils.isEmpty(queryMap.get("departmentId"))){
                //学院id
                where.append(" and t.department_id = #{departmentId}");
            }
            if (!StringUtils.isEmpty(queryMap.get("sno"))){
                //学号
                where.append(" and t.sno like concat(#{sno},'%')");
            }
            if (!StringUtils.isEmpty(queryMap.get("name"))){
                //学生姓名
                where.append(" and t.name like concat('%',#{name},'%')");
            }
        }
        String sql = "select t.*,g.grade_name,m.major_name,d.department_name from student t "
                +" left join grade g on t.grade_id=g.id "
                +" left join major m on t.major_id=m.id "
                +" left join department d on t.department_id=d.id where "
                + where + "order by m.major_no asc,g.grade_num asc,t.sno asc";
        if (log.isDebugEnabled()){
            log.debug("SQL语句为:" + sql);
        }
        return sql;
    }
}
