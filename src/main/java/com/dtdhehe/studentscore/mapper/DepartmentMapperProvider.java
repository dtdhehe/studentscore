package com.dtdhehe.studentscore.mapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * @author 陈姗姗
 * @version 1.0.0
 * @date 2019/11/10 16:45
 * @description 学院动态sql类
 **/
public class DepartmentMapperProvider {

    private Logger log =  LoggerFactory.getLogger(DepartmentMapperProvider.class);

    public String queryDepartment(Map<String,Object> queryMap){
        StringBuilder where = new StringBuilder();
        if (queryMap.isEmpty()){
            where.append("t.valid_flag = '1'");
        }else {
            where.append("t.valid_flag = '1'");
            if (!StringUtils.isEmpty(queryMap.get("departmentNo"))){
                where.append(" and t.department_no like concat('%',#{departmentNo})");
            }
            if (!StringUtils.isEmpty(queryMap.get("departmentName"))){
                where.append(" and t.department_name like concat(concat('%',#{departmentName}),'%')");
            }
        }
        String sql = "select t.* from department t where " + where + "order by t.department_no asc";
        if (log.isDebugEnabled()){
            log.debug("SQL语句为:" + sql);
        }
        return sql;
    }

}
