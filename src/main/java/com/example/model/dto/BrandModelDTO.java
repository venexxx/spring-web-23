package com.example.model.dto;

import java.util.Set;

public class BrandModelDTO {

    private String category;

    private Set<BrandBindingModel> brands;


    public BrandModelDTO(String category, Set<BrandBindingModel> brands) {
        this.category = category;
        this.brands = brands;
    }



    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Set<BrandBindingModel> getBrands() {
        return brands;
    }

    public void setBrands(Set<BrandBindingModel> brands) {
        this.brands = brands;
    }
}
