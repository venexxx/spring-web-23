package com.example.model.entity;

import com.example.model.entity.enums.ModelCategoryEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="models")
public class ModelEntity extends BaseEntity {

  private String name;

  @Enumerated(EnumType.STRING)
  private ModelCategoryEnum category;

  @ManyToOne
  private BrandEntity brand;

  public String getName() {
    return name;
  }

  public ModelEntity setName(String name) {
    this.name = name;
    return this;
  }

  public ModelCategoryEnum getCategory() {
    return category;
  }

  public ModelEntity setCategory(ModelCategoryEnum category) {
    this.category = category;
    return this;
  }

  public BrandEntity getBrand() {
    return brand;
  }

  public ModelEntity setBrand(BrandEntity brand) {
    this.brand = brand;
    return this;
  }

  @Override
  public String toString() {
    return getBrand().getModelCategoryEnum().toString() + " " + getBrand().getName() +" " + getName();
  }
}