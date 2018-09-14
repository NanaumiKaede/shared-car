package com.sharedcar.service;

import com.sharedcar.dao.AddressDao;
import com.sharedcar.dao.CarDao;
import com.sharedcar.pojo.Address;
import com.sharedcar.pojo.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {
    @Autowired
    private CarDao carDao;
    @Autowired
    private AddressDao addressDao;

    public List<Car> findCarByParam(String carNumber, Integer addressId, Double beginRE, Double endRE, String carCondition) {
        List<Car> cars = carDao.findCarByParam(carNumber, addressId, beginRE, endRE, carCondition);
        return cars;
    }

    public Car findCarById(Integer id) {
        return carDao.findCarById(id);
    }

    public int modifyCar(Car car) {
        return carDao.modifyCar(car);
    }

    public int addCar(Car car) {
        Integer addressId = car.getAddressId();
        Address address = addressDao.find(addressId, null).get(0);
        address.setCurrent(address.getCurrent() + 1);
        addressDao.modifyAddress(address);
        return carDao.addCar(car);
    }

    public int removeCar(Integer id) {
        return carDao.remove(id);
    }

    public void checkCarCondition() {
        List<Car> cars = carDao.findCarByParam(null, null, null, null, null);
        for (Car car : cars) {
            if (car.getRemainEnergy() < 15) {
                car.setCarCondition("待充电");
                carDao.modifyCar(car);
            } else {
                if (!"正在使用".equals(car.getCarCondition())) {
                    car.setCarCondition("可租赁");
                    carDao.modifyCar(car);
                }
            }
        }
    }
}
