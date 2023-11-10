package com.example.model.entity;

import com.example.model.entity.enums.ModelCategoryEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
@Table(name = "brands")
@NamedEntityGraph(
    name = "brandWithModels",
    attributeNodes = @NamedAttributeNode("models")
)
public class BrandEntity extends BaseEntity {
  @Column(nullable = false)
  private String name;

  @OneToMany(
          fetch = FetchType.EAGER, cascade = CascadeType.ALL,
      mappedBy = "brand"
  )
  private List<ModelEntity> models;

  @NotNull
  private String imageURL;

  @Column(name = "model_category")
  private ModelCategoryEnum modelCategoryEnum;

  public ModelCategoryEnum getModelCategoryEnum() {
    return modelCategoryEnum;
  }

  public void setModelCategoryEnum(ModelCategoryEnum modelCategoryEnum) {
    this.modelCategoryEnum = modelCategoryEnum;
  }

  public String getImageURL() {
    return imageURL;
  }

  public void setImageURL(String imageURL) {
    this.imageURL = imageURL;
  }

  public BrandEntity setName(String name) {
    this.name = name;
    return this;
  }

  public String getName() {
    return name;
  }

  public List<ModelEntity> getModels() {
    return models;
  }

  public BrandEntity setModels(List<ModelEntity> models) {
    this.models = models;
    return this;
  }
}
