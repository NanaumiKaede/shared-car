package com.sharedcar.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class Business implements Serializable {
    private static final long serialVersionUID = 5230946831152278119L;
    private Integer id;
    private User user;
    private Integer userId;
    private Car car;
    private Integer carId;
    private String type;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date workTime;
    private String carImg;
    private Boolean response;
    private Double mile;
    private Double energyCost;
    private Address address;
    private Integer addressId;
    private String otherAddress;

    public Business() {
    }

    @Override
    public String toString() {
        return "Business{" +
                "id=" + id +
                ", user=" + user +
                ", car=" + car +
                ", type='" + type + '\'' +
                ", workTime=" + workTime +
                ", carImg='" + carImg + '\'' +
                ", response=" + response +
                ", mile=" + mile +
                ", energyCost=" + energyCost +
                ", address=" + address +
                ", otherAddress='" + otherAddress + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getWorkTime() {
        return workTime;
    }

    public void setWorkTime(Date workTime) {
        this.workTime = workTime;
    }

    public String getCarImg() {
        return carImg;
    }

    public void setCarImg(String carImg) {
        this.carImg = carImg;
    }

    public Boolean getResponse() {
        return response;
    }

    public void setResponse(Boolean response) {
        this.response = response;
    }

    public Double getMile() {
        return mile;
    }

    public void setMile(Double mile) {
        this.mile = mile;
    }

    public Double getEnergyCost() {
        return energyCost;
    }

    public void setEnergyCost(Double energyCost) {
        this.energyCost = energyCost;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getOtherAddress() {
        return otherAddress;
    }

    public void setOtherAddress(String otherAddress) {
        this.otherAddress = otherAddress;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }
}
