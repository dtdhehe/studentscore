package com.dtdhehe.studentscore.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * @author 陈姗姗
 * @version 1.0.0
 * @date 2019/11/15 11:19
 * @description
 **/
public class MajorMapperProvider {

    private Logger log =  LoggerFactory.getLogger(MajorMapperProvider.class);

    public String queryMajor(Map<String,Object> queryMap){
        StringBuilder where = new StringBuilder();
        if (queryMap.isEmpty()){
            where.append("t.valid_flag = '1'");
        }else {
            where.append("t.valid_flag = '1'");
            if (!StringUtils.isEmpty(queryMap.get("majorNo"))){
                where.append(" and t.major_no like concat('%',#{majorNo},'%')");
            }
            if (!StringUtils.isEmpty(queryMap.get("majorName"))){
                where.append(" and t.major_name like concat('%',#{majorName},'%')");
            }
            if (!StringUtils.isEmpty(queryMap.get("departmentName"))){
                where.append(" and d.department_name like concat('%',#{departmentName},'%')");
            }
        }
        String sql = "select t.*,d.department_name from major t left join department d on t.department_id=d.id where "
                + where + "order by t.major_no asc";
        if (log.isDebugEnabled()){
            log.debug("SQL语句为:" + sql);
        }
        return sql;
    }

}
