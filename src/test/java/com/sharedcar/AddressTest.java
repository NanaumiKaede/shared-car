package com.sharedcar;

import com.sharedcar.pojo.Address;
import com.sharedcar.service.AddressService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AddressTest {
    private AddressService service;

    @BeforeEach
    public void init() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        service = context.getBean("addressService", AddressService.class);
    }

    @Test
    public void find() {
        System.out.println(service.find(null, null));
    }

    @Test
    public void add() {
        Address address = new Address();
        address.setAddressName("福元路大桥桥下停车场");
        address.setMax(10);
        service.addAddress(address);
        System.out.println(address);
    }

    @Test
    public void modify() {
        Address address = service.find(null, "万科城").get(0);
        address.setCurrent(1);
        service.modifyAddress(address);
    }
}
