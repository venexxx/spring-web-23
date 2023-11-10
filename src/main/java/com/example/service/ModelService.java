package com.example.service;

import com.example.model.dto.AddModelDTO;
import com.example.model.entity.ModelEntity;

public interface ModelService {
    boolean checkForTheSameModel(AddModelDTO addModelDTO);

    void addModel(AddModelDTO addModelDTO);

    ModelEntity getByNameAndBrand(String model,String categoryName);
}
