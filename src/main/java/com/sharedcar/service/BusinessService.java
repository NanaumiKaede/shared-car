package com.sharedcar.service;

import com.mysql.cj.util.StringUtils;
import com.sharedcar.dao.AddressDao;
import com.sharedcar.dao.BusinessDao;
import com.sharedcar.dao.CarDao;
import com.sharedcar.dao.UserDao;
import com.sharedcar.pojo.Address;
import com.sharedcar.pojo.Business;
import com.sharedcar.pojo.Car;
import com.sharedcar.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class BusinessService {
    @Autowired
    private BusinessDao businessDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private CarDao carDao;
    @Autowired
    private AddressDao addressDao;

    public List<Business> findByParam(
            Integer id,
            String name,
            String carNumber,
            String type,
            Date beginTime,
            Date endTime,
            String carCondition,
            Integer pageNo
    ) {
        List<Business> businesses = new ArrayList<>();
        List<User> users = new ArrayList<>();
        pageNo = (pageNo == null) ? 0 : pageNo;
        if (name != null && name != "") {
            users = userDao.findUserByParam(name, null, null, null, null, null);
        }
        List<Car> cars = new ArrayList<>();
        if (carNumber != null && carNumber != "") {
            cars = carDao.findCarByParam(carNumber, null, null, null, null);
        }
        if (users.size() > 0 && cars.size() > 0) {
            for (User user : users) {
                for (Car car : cars) {
                    List<Business> list = businessDao.findByParam(id, user, car, type, beginTime, endTime, carCondition, (pageNo - 1) * 5);
                    for (Business business : list) {
                        businesses.add(business);
                    }
                }
            }
        } else if (users.size() > 0 && cars.size() == 0) {
            for (User user : users) {
                List<Business> list = businessDao.findByParam(id, user, null, type, beginTime, endTime, carCondition, (pageNo - 1) * 5);
                for (Business business : list) {
                    businesses.add(business);
                }
            }
        } else if (users.size() == 0 && cars.size() > 0) {
            businesses = businessDao.findByParam(id, null, cars.get(0), type, beginTime, endTime, carCondition, (pageNo - 1) * 5);
        } else {
            businesses = businessDao.findByParam(id, null, null, type, beginTime, endTime, carCondition, (pageNo - 1) * 5);
        }
        businesses.sort((o1, o2) -> {
            if (o1.getWorkTime().getTime() < o2.getWorkTime().getTime()) {
                return 1;
            } else {
                return -1;
            }
        });
        return businesses;
    }

    public String addBusiness(Business business) {
        Integer userId = business.getUserId();
        User user = userDao.findUserById(userId);
        if ("租赁".equals(business.getType())) {
            String userCondition = user.getUserCondition();
            if ("正在使用".equals(userCondition) || "待追责".equals(userCondition) || "审核中".equals(userCondition)) {
                return "失败!用户状态:" + userCondition;
            }
            Integer carId = business.getCarId();
            Car car = carDao.findCarById(carId);
            String carCondition = car.getCarCondition();
            if ("正在使用".equals(carCondition) || "待充电".equals(carCondition) || "待维修".equals(carCondition)) {
                return "失败!车辆状态:" + carCondition;
            }
            Integer addressId = business.getAddressId();
            Address address = addressDao.find(addressId, null).get(0);
            address.setCurrent(address.getCurrent() - 1);
            addressDao.modifyAddress(address);
            user.setUserCondition("正在使用");
            userDao.modify(user);
            car.setCarCondition("正在使用");
            carDao.modifyCar(car);

            int count1 = businessDao.addBusiness(business);
            int count2 = businessDao.modifyBusiness(business);
            if (count1 != 0 && count2 != 0) {
                return "成功";
            }
        } else if ("归还".equals(business.getType())) {
            if ("正在使用".equals(user.getUserCondition())) {
                //获取地址
                Integer addressId = business.getAddressId();
                Address address = addressDao.find(addressId, null).get(0);
                //获取车辆信息
                List<Business> businesses = businessDao.findByParam(null, user, null, "租赁", null, null, null, 0);
                businesses.sort((o1, o2) -> {
                    if (o1.getWorkTime().getTime() < o2.getWorkTime().getTime()) {
                        return 1;
                    } else {
                        return -1;
                    }
                });
                Integer carId = businesses.get(0).getCar().getId();
                Car car = carDao.findCarById(carId);
                if (car.getRemainEnergy() < business.getEnergyCost()) {
                    return "失败!消耗能源量非法";
                }
                address.setCurrent(address.getCurrent() + 1);
                addressDao.modifyAddress(address);
                car.setAddressId(addressId);
                car.setCarCondition("可租赁");
                car.setRemainEnergy(car.getRemainEnergy() - business.getEnergyCost());
                System.out.println(business);
                System.out.println(car);
                car.setTotalMile(car.getTotalMile() + business.getMile());
                carDao.modifyCar(car);
                //用户状态变更
                user.setUserCondition("正常");
                userDao.modify(user);
                //添加业务记录
                business.setCarId(carId);
                int count1 = businessDao.addBusiness(business);
                int count2 = businessDao.modifyBusiness(business);
                if (count1 != 0 && count2 != 0) {
                    return "成功";
                }
            }
        } else if ("充电".equals(business.getType())) {

        } else if ("维修".equals(business.getType())) {

        }
        return "失败";
    }

    public int findBusinessCount() {
        return businessDao.findBusinessCount();
    }

    public List<Business> findCondition(String carCondition) {
        List<Business> businesses = businessDao.findByParam(null, null, null, null, null, null, carCondition, -5);
        businesses.sort((o1, o2) -> {
            if (o1.getWorkTime().getTime() < o2.getWorkTime().getTime()) {
                return 1;
            } else {
                return -1;
            }
        });
        for (int i = 0; i < businesses.size(); i++) {
            for (int j = i + 1; j < businesses.size(); j++) {
                if (businesses.get(j).getCar().getId() == businesses.get(i).getCar().getId()) {
                    businesses.remove(j);
                    j--;
                }
            }
        }
        return businesses;
    }
}
