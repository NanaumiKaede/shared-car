package com.sharedcar;

import com.sharedcar.pojo.Address;
import com.sharedcar.pojo.Business;
import com.sharedcar.pojo.Car;
import com.sharedcar.pojo.User;
import com.sharedcar.service.BusinessService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class BusinessTest {
    private BusinessService service;

    @BeforeEach
    public void init() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        service = context.getBean("businessService", BusinessService.class);
    }

    @Test
    public void find() {
        User user = new User();
        user.setId(20);
        user.setName("baiwentian");
        Car car = new Car();
        car.setId(2);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date begin = new Date();
        Date end = new Date();
        try {
            begin = format.parse("2018-08-19");
            end = format.parse("2018-08-21");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<Business> businesses = service.findByParam(null, null, null, null, null, null, null, 1);
        System.out.println(businesses);
    }

    @Test
    public void add() {
        Business business = new Business();
        User user = new User();
        user.setId(20);
        business.setUser(user);
        Car car = new Car();
        car.setId(1);
        business.setCar(car);
        business.setType("租赁");
        Address address = new Address();
        address.setId(4);
        business.setAddress(address);
        System.out.println(service.addBusiness(business));
    }
}
