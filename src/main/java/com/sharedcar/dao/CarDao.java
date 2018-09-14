package com.sharedcar.dao;

import com.sharedcar.pojo.Address;
import com.sharedcar.pojo.Car;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarDao {
    //管理员通过条件查询
    List<Car> findCarByParam(
            @Param("carNumber") String carNumber,
            @Param("addressId") Integer addressId,
            @Param("beginRE") Double beginRE,
            @Param("endRE") Double endRE,
            @Param("carCondition") String carCondition
    );

    @Select("select * from car where id=#{id}")
    Car findCarById(@Param("id") Integer id);

    //管理员端修改信息
    @Update("update car set address_id=#{addressId},remain_energy=#{remainEnergy},car_condition=#{carCondition} where id=#{id}")
    int modifyCar(Car car);

    //管理员端添加新汽车信息
    @Insert("insert into car(address_id,car_number,type) values(#{addressId},#{carNumber},#{type})")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    int addCar(Car car);

    //管理员端删除汽车信息
    @Delete("delete from car where id=#{id}")
    int remove(Integer cId);
}
