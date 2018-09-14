package com.sharedcar.controller;

import com.sharedcar.pojo.Business;
import com.sharedcar.pojo.CustomMessage;
import com.sharedcar.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "http://127.0.0.1:8080", maxAge = 3600)
@RequestMapping("complex/")
@RestController
public class ComplexController {
    @Autowired
    private BusinessService businessService;

    //业务逻辑
    @RequestMapping(value = "business", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> findByParam(
            @RequestParam(value = "id", required = false) Integer id,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "carNumber", required = false) String carNumber,
            @RequestParam(value = "type", required = false) String type,
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
            @RequestParam(value = "beginTime", required = false) Date beginTime,
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
            @RequestParam(value = "endTime", required = false) Date endTime,
            @RequestParam(value = "carCondition", required = false) String carCondition,
            @RequestParam(value = "pageNo", required = false) Integer pageNo
    ) {
        List<Business> businesses;
        if (carCondition != null && !"".equals(carCondition)) {
            businesses = businessService.findCondition(carCondition);
        } else {
            businesses = businessService.findByParam(id, name, carNumber, type, beginTime, endTime, carCondition, pageNo);
        }
        return new ResponseEntity<>(businesses, HttpStatus.OK);
    }

    @RequestMapping(value = "business", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> addBusiness(@RequestBody Business business) {
        CustomMessage customMessage = new CustomMessage(200, businessService.addBusiness(business));
        return new ResponseEntity<>(customMessage, HttpStatus.OK);
    }
}
