package com.example.model.dto;


import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public class OfferAddDTO {

    @NotEmpty(message = "Description is required!")
    private String description;
    @NotEmpty(message = "Brand is required!")
    private String model;

    @NotEmpty(message = "Engine type is required!")
    private String engine;
    @NotEmpty(message = "Transmission type is required!")
    private String transmission;

    @NotEmpty(message = "Vehicle image URL  is required!")
    private String imageUrl;

    @Positive(message = "Vehicle mileage is required!")
    private int mileage;

    @Positive(message = "Price must grater than 0!")
    @NotNull(message = "Description is required!")
    private BigDecimal price;


    @Min(value = 1930,message = "Year must be grater than 1930!")
    private int year;

    @Positive(message = "Horse power must be grater than!")

    private int horsePower;


    @Positive(message = "Cubic centimeters must be grater than 0!")
    private int cubicCentimeters;


    @NotNull(message = "Condition is required!")
    private String conditionName;



    @NotEmpty(message = "Euro Standard is required!")
    private String standard;


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

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }
}