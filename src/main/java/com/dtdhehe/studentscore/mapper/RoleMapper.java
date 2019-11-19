package com.dtdhehe.studentscore.mapper;

import com.dtdhehe.studentscore.entity.Role;
import com.dtdhehe.studentscore.entity.UserRole;
import org.apache.ibatis.annotations.Insert;
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

    /**
     * 根据权限名称查询权限id
     * @param roleName
     * @return
     */
    @Select("SELECT t.id from role t where t.valid_flag='1' and t.role_name=#{roleName}")
    Role findByRoleName(@Param("roleName")String roleName);

    /**
     * 保存用户-权限表
     * @param userRole
     * @return
     */
    @Insert("insert into user_role(id,valid_flag,create_time,update_time,user_id,role_id) VALUES(#{id},#{validFlag},#{createTime},#{updateTime},#{userId},#{roleId})")
    Integer saveUserRole(UserRole userRole);
}
