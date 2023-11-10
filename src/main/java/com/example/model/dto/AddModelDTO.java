package com.example.model.dto;

import jakarta.validation.constraints.NotBlank;

public class AddModelDTO {
    @NotBlank(message = "Model name can not be empty!")
    private String name;
    @NotBlank(message = "You must to select brand!")
    private String brand;
    private String category;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
