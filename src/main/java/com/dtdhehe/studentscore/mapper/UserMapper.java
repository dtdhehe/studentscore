package com.dtdhehe.studentscore.mapper;

import com.dtdhehe.studentscore.entity.User;
import org.apache.ibatis.annotations.*;

/**
 * @author 陈珊珊
 * @version 1.0
 * @date 2019/11/5 23:09
 * @description
 **/
@Mapper
public interface UserMapper {

    /**
     * 根据用户名查找用户
     * @param userName
     * @return
     */
    @Select("select t.* from user t where t.user_name=#{userName}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "roleList",column = "id",
                    many = @Many(select = "com.dtdhehe.studentscore.mapper.RoleMapper.findRoleByUserId"))
    })
    User findByUserName(@Param("userName") String userName);

    /**
     * 保存用户
     * @param user
     * @return
     */
    @Insert("insert into user(id,valid_flag,create_time,update_time,user_name,password,email,phone,sex,status) VALUES(#{id},#{validFlag},#{createTime},#{updateTime},#{userName},#{password},#{email},#{phone},#{sex},#{status})")
    Integer save(User user);
}
