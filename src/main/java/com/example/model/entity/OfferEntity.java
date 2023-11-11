package com.example.model.entity;

import com.example.model.entity.enums.AxlesEnum;
import com.example.model.entity.enums.ConditionEnum;
import com.example.model.entity.enums.EngineEnum;
import com.example.model.entity.enums.TransmissionEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;


@Entity
@Table(name = "offers")
public class OfferEntity extends BaseEntity{



  @NotEmpty
  private String description;
  @ManyToOne
  private ModelEntity model;


  @Enumerated(EnumType.STRING)
  private EngineEnum engine;
  @Enumerated(EnumType.STRING)
  private AxlesEnum axles;
  @Enumerated(EnumType.STRING)
  private TransmissionEnum transmission;

  private String imageUrl;

  private String secondImageUrl;

  @Positive
  private int mileage;


  @Positive
  private int motoHours;

  private BigDecimal price;

  @Min(1930)
  private int year;

  @Positive
  private int horsePower;

  @Positive
  private int cubicCentimeters;


  @Enumerated(EnumType.STRING)
  private ConditionEnum conditionName;



  @ManyToOne
  private EuroStandardEntity standard;

  @ManyToOne
  private UserEntity postBy;

  public int getMotoHours() {
    return motoHours;
  }

  public void setMotoHours(int motoHours) {
    this.motoHours = motoHours;
  }

  public AxlesEnum getAxles() {
    return axles;
  }

  public void setAxles(AxlesEnum axles) {
    this.axles = axles;
  }

  public String getSecondImageUrl() {
    return secondImageUrl;
  }

  public void setSecondImageUrl(String secondImageUrl) {
    this.secondImageUrl = secondImageUrl;
  }

  public ConditionEnum getConditionName() {
    return conditionName;
  }

  public void setConditionName(ConditionEnum conditionName) {
    this.conditionName = conditionName;
  }

  public int getCubicCentimeters() {
    return cubicCentimeters;
  }

  public void setCubicCentimeters(int cubicCentimeters) {
    this.cubicCentimeters = cubicCentimeters;
  }



  public EuroStandardEntity getStandard() {
    return standard;
  }

  public void setStandard(EuroStandardEntity standard) {
    this.standard = standard;
  }

  public UserEntity getPostBy() {
    return postBy;
  }

  public void setPostBy(UserEntity postBy) {
    this.postBy = postBy;
  }

  public int getHorsePower() {
    return horsePower;
  }

  public void setHorsePower(int horsePower) {
    this.horsePower = horsePower;
  }

  public boolean isItVIP;

  public boolean isItVIP() {
    return isItVIP;
  }

  public void setItVIP(boolean itVIP) {
    isItVIP = itVIP;
  }

  public String getDescription() {
    return description;
  }

  public OfferEntity setDescription(String description) {
    this.description = description;
    return this;
  }

  public ModelEntity getModel() {
    return model;
  }

  public OfferEntity setModel(ModelEntity model) {
    this.model = model;
    return this;
  }

  public EngineEnum getEngine() {
    return engine;
  }

  public OfferEntity setEngine(EngineEnum engine) {
    this.engine = engine;
    return this;
  }

  public TransmissionEnum getTransmission() {
    return transmission;
  }

  public OfferEntity setTransmission(TransmissionEnum transmission) {
    this.transmission = transmission;
    return this;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public OfferEntity setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
    return this;
  }

  public int getMileage() {
    return mileage;
  }

  public OfferEntity setMileage(int mileage) {
    this.mileage = mileage;
    return this;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public OfferEntity setPrice(BigDecimal price) {
    this.price = price;
    return this;
  }

  public int getYear() {
    return year;
  }

  public OfferEntity setYear(int year) {
    this.year = year;
    return this;
  }


}