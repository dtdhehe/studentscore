package com.dtdhehe.studentscore.mapper;

import com.dtdhehe.studentscore.entity.Wrap;
import com.dtdhehe.studentscore.provider.WrapMapperProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * @author 陈珊珊
 * @version 1.0
 * @date 2019/11/28 23:10
 * @description
 **/
@Mapper
public interface WrapMapper {

    @Select("select t.* from wrap t where t.id = #{id}")
    Wrap findById(@Param("id") String id);

    /**
     * 新增
     * @param wrap
     * @return
     */
    @Insert("insert into wrap(id,valid_flag,create_time,update_time,wrap_name,wrap_desc,wrap_status,img_url) VALUES(#{id},#{validFlag},#{createTime},#{updateTime},#{wrapName},#{wrapDesc},#{wrapStatus},#{imgUrl})")
    Integer save(Wrap wrap);

    /**
     * 更新
     * @param wrap
     * @return
     */
    @Update("update wrap set valid_flag=#{validFlag},update_time=#{updateTime},wrap_name=#{wrapName},wrap_desc=#{wrapDesc},wrap_status=#{wrapStatus},img_url=#{imgUrl} where id=#{id}")
    Integer update(Wrap wrap);

    /**
     * 查询轮播图列表
     * @param queryMap
     * @return
     */
    @SelectProvider(type = WrapMapperProvider.class,method = "queryWrap")
    List<Map<String,Object>> queryWrap(Map<String,Object> queryMap);

}
