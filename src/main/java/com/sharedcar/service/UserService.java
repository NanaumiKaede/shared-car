package com.sharedcar.service;

import com.sharedcar.dao.UserDao;
import com.sharedcar.pojo.User;
import com.sharedcar.util.MyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public User checkLogin(String username, String password) {
        User user = userDao.findUserByUsername(username);
        if (user != null) {
            password = MyUtil.getMD5(password);
            if (user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    public User findUserById(Integer id) {
        return userDao.findUserById(id);
    }

    public List<User> findUserByParam(String name, Integer beginAge, Integer endAge, String phone, String idCard, String driverId) {
        return userDao.findUserByParam(name, beginAge, endAge, phone, idCard, driverId);
    }

    public int addUser(User user) {
        User user1 = userDao.findUserByUsername(user.getUsername());
        if (user1 == null) {
            String password = user.getPassword();
            password = MyUtil.getMD5(password);
            user.setPassword(password);
            return userDao.addUser(user);
        } else {
            return 0;
        }
    }

    public int modify(User user) {
        return userDao.modify(user);
    }

    public int removeUser(Integer id) {
        return userDao.removeUser(id);
    }
}
