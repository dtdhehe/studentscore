package com.dtdhehe.studentscore.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * @author Xie_东
 * @version 1.0.0
 * @date 2019/11/29 16:05
 * @description
 **/
public class WrapMapperProvider {
    private Logger log =  LoggerFactory.getLogger(WrapMapperProvider.class);

    public String queryWrap(Map<String,Object> queryMap){
        StringBuilder where = new StringBuilder();
        if (queryMap.isEmpty()){
            where.append("t.valid_flag = '1'");
        }else {
            where.append("t.valid_flag = '1'");
            if (!StringUtils.isEmpty(queryMap.get("wrapName"))){
                where.append(" and t.wrap_name like concat(concat('%',#{wrapName}),'%')");
            }
            if (!StringUtils.isEmpty(queryMap.get("wrapStatus"))){
                where.append(" and t.wrap_status = #{wrapStatus}");
            }
        }
        String sql = "select t.* from wrap t where " + where + "order by t.wrap_status desc,t.update_time desc";
        if (log.isDebugEnabled()){
            log.debug("SQL语句为:" + sql);
        }
        return sql;
    }
}
