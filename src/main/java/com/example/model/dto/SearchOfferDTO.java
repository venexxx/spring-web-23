package com.example.model.dto;

import jakarta.validation.constraints.NotEmpty;

public class SearchOfferDTO {


    @NotEmpty(message = "Category is required!")
    private String category;


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
