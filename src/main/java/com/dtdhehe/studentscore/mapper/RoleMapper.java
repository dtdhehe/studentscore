package com.dtdhehe.studentscore.mapper;

import com.dtdhehe.studentscore.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author 陈珊珊
 * @version 1.0
 * @date 2019/11/05 22:51
 * @description
 **/
@Mapper
public interface RoleMapper {

    /**
     * 根据userId查询该用户所有的角色
     * @param userId
     * @return
     */
    @Select("SELECT t.* from role t LEFT JOIN user_role b ON t.id=b.role_id WHERE b.user_id=#{userId}")
    List<Role> findRoleByUserId(@Param("userId") String userId);
}
