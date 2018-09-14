package com.sharedcar.controller;

import com.mysql.cj.util.StringUtils;
import com.sharedcar.pojo.Address;
import com.sharedcar.pojo.Car;
import com.sharedcar.pojo.CustomMessage;
import com.sharedcar.pojo.User;
import com.sharedcar.service.AddressService;
import com.sharedcar.service.BusinessService;
import com.sharedcar.service.CarService;
import com.sharedcar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://127.0.0.1:8080", maxAge = 3600)
@RequestMapping("simple/")
@RestController
public class SimpleController {
    @Autowired
    private AddressService addressService;
    @Autowired
    private UserService userService;
    @Autowired
    private CarService carService;
    @Autowired
    private BusinessService businessService;

    //对地址的操作
    @RequestMapping(value = "address", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> find(
            @RequestParam(value = "id", required = false) Integer id,
            @RequestParam(value = "addressName", required = false) String addressName
    ) {
        List<Address> addresses = addressService.find(id, addressName);
        return new ResponseEntity<>(addresses, HttpStatus.OK);
    }

    @RequestMapping(value = "address", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> add(@RequestBody Address address) {
        int count = addressService.addAddress(address);
        return checkResult(count);
    }

    @RequestMapping(value = "address/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> remove(@PathVariable(value = "id") Integer id) {
        int count = addressService.removeAddress(id);
        return checkResult(count);
    }

    @RequestMapping(value = "address", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> modify(@RequestBody Address address) {
        int count = addressService.modifyAddress(address);
        return checkResult(count);
    }

    //对用户的操作
    @RequestMapping(value = "user", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> loginAndRegister(
            @RequestParam("action") String action,
            @RequestParam("username") String username,
            @RequestParam("password") String password
    ) {
        if ("login".equals(action)) {
            User user = userService.checkLogin(username, password);
            if (user == null) {
                return new ResponseEntity<>(new CustomMessage(500, "用户不存在或密码错误"), HttpStatus.OK);
            }
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        if ("register".equals(action)) {
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            int count = userService.addUser(user);
            return checkResult(count);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @RequestMapping(value = "user", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> modify(@RequestBody User user) {
        if (user.getUserCondition() == null) {
            user.setUserCondition("审核中");
        }
        if (StringUtils.isNullOrEmpty(user.getUsername()) && user.getId() != null) {
            user.setUsername(userService.findUserById(user.getId()).getUsername());
        }
        int count = userService.modify(user);
        return checkResult(count);
    }

    @RequestMapping(value = "user", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> managerUser(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "beginAge", required = false) Integer beginAge,
            @RequestParam(value = "endAge", required = false) Integer endAge,
            @RequestParam(value = "phone", required = false) String phone,
            @RequestParam(value = "idCard", required = false) String idCard,
            @RequestParam(value = "driverId", required = false) String driverId
    ) {
        List<User> users = userService.findUserByParam(name, beginAge, endAge, phone, idCard, driverId);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @RequestMapping(value = "user/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> removeUser(@PathVariable Integer id) {
        int count = userService.removeUser(id);
        return checkResult(count);
    }

    //对汽车的操作
    @RequestMapping(value = "car", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> findCarByParam(
            @RequestParam(value = "carNumber", required = false) String carNumber,
            @RequestParam(value = "addressId", required = false) Integer addressId,
            @RequestParam(value = "beginRE", required = false) Double beginRE,
            @RequestParam(value = "endRE", required = false) Double endRE,
            @RequestParam(value = "carCondition", required = false) String carCondition
    ) {
        return new ResponseEntity<>(carService.findCarByParam(carNumber, addressId, beginRE, endRE, carCondition), HttpStatus.OK);
    }

    @RequestMapping(value = "car", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> modifyCar(@RequestBody Car car) {
        int count = carService.modifyCar(car);
        return checkResult(count);
    }

    @RequestMapping(value = "car", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> addCar(@RequestBody Car car) {
        int count = carService.addCar(car);
        return checkResult(count);
    }

    @RequestMapping(value = "car/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> removeCar(@PathVariable Integer id) {
        int count = carService.removeCar(id);
        return checkResult(count);
    }

    @RequestMapping(value = "business", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> findBusinessCount() {
        return new ResponseEntity<>(businessService.findBusinessCount(), HttpStatus.OK);
    }

    @Scheduled(fixedDelay = 5000)
    public void checkCarCondition() {
        carService.checkCarCondition();
    }

    private static ResponseEntity<?> checkResult(Integer count) {
        CustomMessage customMessage = new CustomMessage();
        if (count == 1) {
            customMessage.setCode(200);
            customMessage.setMessage("成功");
            return new ResponseEntity<>(customMessage, HttpStatus.OK);
        } else {
            customMessage.setCode(500);
            customMessage.setMessage("失败");
            return new ResponseEntity<>(customMessage, HttpStatus.OK);
        }
    }
}
