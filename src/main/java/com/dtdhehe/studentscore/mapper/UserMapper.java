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
    @Select("select t.* from user t where t.user_name=#{userName} and t.valid_flag='1'")
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

    /**
     * 根据id查找用户
     * @param id
     * @return
     */
    @Select("select t.* from user t where t.id=#{id}")
    User findById(@Param("id") String id);

    /**
     * 更新用户
     * @param user
     * @return
     */
    @Update("update user set valid_flag=#{validFlag},update_time=#{updateTime},user_name=#{userName},password=#{password},email=#{email},phone=#{phone},sex=#{sex},status=#{status} where id=#{id}")
    Integer update(User user);
}
