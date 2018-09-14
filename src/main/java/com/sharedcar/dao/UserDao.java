package com.sharedcar.dao;

import com.sharedcar.pojo.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {
    @Select("select * from user where username=#{username}")
    User findUserByUsername(@Param("username") String username);

    @Select("select * from user where id = #{id}")
    User findUserById(@Param("id") Integer id);

    List<User> findUserByParam(
            @Param("name") String name,
            @Param("beginAge") Integer beginAge,
            @Param("endAge") Integer endAge,
            @Param("phone") String phone,
            @Param("idCard") String idCard,
            @Param("driverId") String driverId
    );

    @Insert("insert into user(username,password,reg_time) values(#{username},#{password},sysdate())")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    int addUser(User user);

    @Update("update user set name=#{name},sex=#{sex},age=#{age},phone=#{phone},id_card=#{idCard},driver_id=#{driverId},user_condition=#{userCondition} where username=#{username}")
    int modify(User user);

    @Delete("delete from user where id=#{id}")
    int removeUser(Integer id);
}
