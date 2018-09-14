package com.sharedcar;

import com.sharedcar.pojo.Car;
import com.sharedcar.service.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class CarTest {
    private CarService service;

    @BeforeEach
    public void init() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        service = context.getBean("carService", CarService.class);
    }

    @Test
    public void find() {
        List<Car> cars = service.findCarByParam("", null, null, null, null);
        System.out.println(cars);
    }

    @Test
    public void add() {
        Car car = new Car();
        car.setAddressId(2);
        car.setType("电能驱动");
        car.setCarNumber("湘A99999");
        System.out.println(service.addCar(car));
        System.out.println(car);
    }
}
