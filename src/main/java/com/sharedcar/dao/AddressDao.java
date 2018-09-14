package com.sharedcar.dao;

import com.sharedcar.pojo.Address;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressDao {
    List<Address> find(
            @Param("id") Integer id,
            @Param("addressName") String addressName
    );

    @Insert("insert into address(address_name,max) values(#{addressName},#{max})")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    int addAddress(Address address);

    @Delete("delete from address where id=#{id}")
    int removeAddress(Integer id);

    @Update("update address set address_name=#{addressName},max=#{max},current=#{current} where id=#{id}")
    int modifyAddress(Address address);
}
