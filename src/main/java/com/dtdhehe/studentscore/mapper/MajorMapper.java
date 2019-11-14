package com.dtdhehe.studentscore.mapper;

import com.dtdhehe.studentscore.entity.Major;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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

}
