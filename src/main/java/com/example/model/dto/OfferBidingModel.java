package com.example.model.dto;

import com.example.model.entity.EuroStandardEntity;
import com.example.model.entity.ModelEntity;
import com.example.model.entity.enums.ConditionEnum;
import com.example.model.entity.enums.EngineEnum;
import com.example.model.entity.enums.TransmissionEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public class OfferBidingModel {


    private Long id;
    private Long posBy;
    private String description;

    private String model;


    private String engine;

    private String transmission;

    private String imageUrl;
    private String secondImageUrl;

    private int mileage;

    private BigDecimal price;

    private int year;

    private int horsePower;


    private int cubicCentimeters;
    private String standard;

    private String axles;
    private int motoHours;

    public Long getPosBy() {
        return posBy;
    }

    public void setPosBy(Long posBy) {
        this.posBy = posBy;
    }

    public String getAxles() {
        return axles;
    }

    public int getMotoHours() {
        return motoHours;
    }

    public void setMotoHours(int motoHours) {
        this.motoHours = motoHours;
    }

    public void setAxles(String axles) {
        this.axles = axles;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    private String conditionName;

    private boolean isVip;

    public String getSecondImageUrl() {
        return secondImageUrl;
    }

    public void setSecondImageUrl(String secondImageUrl) {
        this.secondImageUrl = secondImageUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isVip() {
        return isVip;
    }

    public void setVip(boolean vip) {
        isVip = vip;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }

    public int getCubicCentimeters() {
        return cubicCentimeters;
    }

    public void setCubicCentimeters(int cubicCentimeters) {
        this.cubicCentimeters = cubicCentimeters;
    }

    public String getConditionName() {
        return conditionName;
    }

    public void setConditionName(String conditionName) {
        this.conditionName = conditionName;
    }
}
