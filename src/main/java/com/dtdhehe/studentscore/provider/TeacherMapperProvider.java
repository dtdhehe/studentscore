package com.dtdhehe.studentscore.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * @author 陈姗姗
 * @version 1.0.0
 * @date 2019/11/20 16:37
 * @description
 **/
public class TeacherMapperProvider {

    private Logger log =  LoggerFactory.getLogger(TeacherMapperProvider.class);

    public String queryTeacher(Map<String,Object> queryMap){
        StringBuilder where = new StringBuilder();
        if (queryMap.isEmpty()){
            where.append("t.valid_flag = '1'");
        }else {
            where.append("t.valid_flag = '1'");
            if (!StringUtils.isEmpty(queryMap.get("departmentId"))){
                //学院id
                where.append(" and t.department_id = #{departmentId}");
            }
            if (!StringUtils.isEmpty(queryMap.get("tno"))){
                //编号
                where.append(" and t.tno like concat(#{tno},'%')");
            }
            if (!StringUtils.isEmpty(queryMap.get("name"))){
                //教师姓名
                where.append(" and t.name like concat('%',#{name},'%')");
            }
        }
        String sql = "select t.*,d.department_name from student t "
                +" left join department d on t.department_id=d.id where "
                + where + "order by t.tno asc";
        if (log.isDebugEnabled()){
            log.debug("SQL语句为:" + sql);
        }
        return sql;
    }

}
