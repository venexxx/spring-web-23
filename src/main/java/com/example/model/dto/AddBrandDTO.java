package com.example.model.dto;

import jakarta.validation.constraints.NotBlank;

public class AddBrandDTO {
    @NotBlank(message = "Brand name can not be empty!")
    private String brandName;

    @NotBlank(message = "Image url name can not be empty!")
    private String imageURL;
    @NotBlank(message = "You must to select Category!")
    public String category;

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }



    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
