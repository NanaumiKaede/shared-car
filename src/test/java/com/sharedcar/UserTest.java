package com.sharedcar;

import com.sharedcar.pojo.User;
import com.sharedcar.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class UserTest {
    private UserService service;

    @BeforeEach
    public void init() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        service = context.getBean("userService", UserService.class);
    }

    @Test
    public void findUserByUsername() {
        User user = service.checkLogin("baiwentian", "123456");
        System.out.println(user);
    }

    @Test
    public void findUserByParam() {
        List<User> users = service.findUserByParam("", null, null, null, null, null);
        System.out.println(users);
    }

    @Test
    public void addUser() {
        User user = new User();
        user.setUsername("baiwentian");
        user.setPassword("123456");
        service.addUser(user);
        System.out.println(user);
    }

    @Test
    public void modify() {
        User user = service.checkLogin("baiwentian", "123456");
        user.setName("柏闻天");
        user.setSex("男");
        user.setAge(23);
        user.setPhone("15700178472");
        user.setIdCard("430100123401011234");
        user.setDriverId("430123123123");
        System.out.println(service.modify(user));
    }
}
