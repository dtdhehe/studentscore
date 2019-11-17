package com.dtdhehe.studentscore.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * @author 陈姗姗
 * @version 1.0.0
 * @date 2019/11/17 18:00
 * @description
 **/
public class GradeMapperProvider {

    private Logger log =  LoggerFactory.getLogger(MajorMapperProvider.class);

    public String queryGrade(Map<String,Object> queryMap){
        StringBuilder where = new StringBuilder();
        if (queryMap.isEmpty()){
            where.append("t.valid_flag = '1'");
        }else {
            where.append("t.valid_flag = '1'");
            if (!StringUtils.isEmpty(queryMap.get("majorId"))){
                //专业id
                where.append(" and t.major_id = #{majorId}");
            }
            if (!StringUtils.isEmpty(queryMap.get("gradeName"))){
                //班级名称
                where.append(" and t.grade_name like concat('%',#{gradeName},'%')");
            }
            if (!StringUtils.isEmpty(queryMap.get("departmentId"))){
                //学院id
                where.append(" and t.department_id = #{departmentId}");
            }
        }
        String sql = "select t.*,m.major_name,d.department_name from grade t left join major m on t.major_id=m.id "
                +" left join department d on t.department_id=d.id where "
                + where + "order by m.major_no asc,t.grade_num asc";
        if (log.isDebugEnabled()){
            log.debug("SQL语句为:" + sql);
        }
        return sql;
    }

}
