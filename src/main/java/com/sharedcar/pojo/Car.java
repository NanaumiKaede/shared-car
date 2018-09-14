package com.sharedcar.pojo;

import java.io.Serializable;

public class Car implements Serializable {
    private static final long serialVersionUID = 5469291110783923800L;
    private Integer id;
    private Integer addressId;
    private String carNumber;
    private String type;
    private Double remainEnergy;
    private String carCondition;
    private Double totalMile;
    private Double totalEnergy;
    private Double totalIncome;
    private Double totalCost;

    public Car() {
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", addressId=" + addressId +
                ", carNumber='" + carNumber + '\'' +
                ", type='" + type + '\'' +
                ", remainEnergy=" + remainEnergy +
                ", carCondition='" + carCondition + '\'' +
                ", totalMile=" + totalMile +
                ", totalEnergy=" + totalEnergy +
                ", totalIncome=" + totalIncome +
                ", totalCost=" + totalCost +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getRemainEnergy() {
        return remainEnergy;
    }

    public void setRemainEnergy(Double remainEnergy) {
        this.remainEnergy = remainEnergy;
    }

    public String getCarCondition() {
        return carCondition;
    }

    public void setCarCondition(String carCondition) {
        this.carCondition = carCondition;
    }

    public Double getTotalMile() {
        return totalMile;
    }

    public void setTotalMile(Double totalMile) {
        this.totalMile = totalMile;
    }

    public Double getTotalEnergy() {
        return totalEnergy;
    }

    public void setTotalEnergy(Double totalEnergy) {
        this.totalEnergy = totalEnergy;
    }

    public Double getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(Double totalIncome) {
        this.totalIncome = totalIncome;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }
}
