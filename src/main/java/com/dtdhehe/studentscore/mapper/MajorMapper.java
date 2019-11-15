package com.dtdhehe.studentscore.mapper;

import com.dtdhehe.studentscore.entity.Major;
import com.dtdhehe.studentscore.provider.MajorMapperProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * @author 陈姗姗
 * @version 1.0.0
 * @date 2019/11/14 17:39
 * @description
 **/
@Mapper
public interface MajorMapper {

    /**
     * 根据id查找专业
     * @param id
     * @return
     */
    @Select("select t.* from major t where t.id=#{id}")
    Major findById(@Param("id") String id);

    /**
     * 根据学院编号查找学院
     * @param majorNo
     * @return
     */
    @Select("select t.* from major t where t.major_no=#{majorNo} and t.valid_flag ='1'")
    Major findByNo(@Param("majorNo") String majorNo);

    /**
     * 新增专业
     * @param major
     * @return
     */
    @Insert("insert into major(id,valid_flag,create_time,update_time,department_id,major_no,major_name) VALUES(#{id},#{validFlag},#{createTime},#{updateTime},#{departmentId},#{majorNo},#{majorName})")
    Integer save(Major major);

    /**
     * 更新专业
     * @param major
     * @return
     */
    @Update("update major set update_time=#{updateTime},department_id=#{departmentId},major_no=#{majorNo},major_name=#{majorName} where id=#{id}")
    Integer update(Major major);

    /**
     * 查询专业列表
     * @param queryMap
     * @return
     */
    @SelectProvider(type = MajorMapperProvider.class,method = "queryMajor")
    List<Major> queryMajor(Map<String,Object> queryMap);

    /**
     * 删除专业(逻辑删除,将valid_flag置为0)
     * @param id
     * @param updateTime
     * @return
     */
    @Update("update major set update_time=#{updateTime},valid_flag='0' where id=#{id}")
    Integer delete(@Param("id") String id,@Param("updateTime") String updateTime);

}
