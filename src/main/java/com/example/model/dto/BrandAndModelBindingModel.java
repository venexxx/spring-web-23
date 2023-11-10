package com.example.model.dto;


import java.util.LinkedHashSet;
import java.util.Set;

public class BrandAndModelBindingModel {


    private String name;

    private String category;


    private Set<String> models = new LinkedHashSet<>();

    public Set<String> getModels() {
        return models;
    }

    public void setModels(Set<String> models) {
        this.models = models;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }




}
