package com.dtdhehe.studentscore.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * @author 陈姗姗
 * @version 1.0.0
 * @date 2019/11/20 15:32
 * @description
 **/
public class SubjectMapperProvider {

    private Logger log =  LoggerFactory.getLogger(SubjectMapperProvider.class);

    public String querySubject(Map<String,Object> queryMap){
        StringBuilder where = new StringBuilder();
        if (queryMap.isEmpty()){
            where.append("t.valid_flag = '1'");
        }else {
            where.append("t.valid_flag = '1'");
            if (!StringUtils.isEmpty(queryMap.get("subjectNo"))){
                where.append(" and t.subject_no like concat('%',#{subjectNo},'%')");
            }
            if (!StringUtils.isEmpty(queryMap.get("subjectName"))){
                where.append(" and t.subject_name like concat('%',#{subjectName},'%')");
            }
        }
        String sql = "select t.* from subject t where "
                + where + "order by t.subject_no asc";
        if (log.isDebugEnabled()){
            log.debug("SQL语句为:" + sql);
        }
        return sql;
    }

}
