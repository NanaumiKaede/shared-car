package com.sharedcar.dao;

import com.sharedcar.pojo.Business;
import com.sharedcar.pojo.Car;
import com.sharedcar.pojo.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BusinessDao {
    List<Business> findByParam(
            @Param("id") Integer id,
            @Param("user") User user,
            @Param("car") Car car,
            @Param("type") String type,
            @Param("beginTime") Date beginTime,
            @Param("endTime") Date endTime,
            @Param("carCondition") String carCondition,
            @Param("start") Integer start
    );

    @Select("select count(*) from business")
    int findBusinessCount();

    @Insert("insert into business(user_id,car_id,type,work_time) values(#{userId},#{carId},#{type},sysdate())")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    int addBusiness(Business business);

    int modifyBusiness(Business business);
}
