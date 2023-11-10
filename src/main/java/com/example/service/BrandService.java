package com.example.service;

import com.example.model.dto.AddBrandDTO;
import com.example.model.dto.BrandAndModelBindingModel;
import com.example.model.dto.BrandBindingModel;
import com.example.model.entity.BrandEntity;
import com.example.model.entity.enums.ModelCategoryEnum;

import java.util.Set;

public interface BrandService {


    void addBrand(AddBrandDTO addBrandDTO);

    Set<BrandBindingModel> getAllBrands(ModelCategoryEnum category);
    Set<BrandAndModelBindingModel> getAllBrandsAndModels(ModelCategoryEnum category);
    Set<BrandBindingModel> getAllBrands();

    boolean checkForSameBrand(AddBrandDTO brand);

    BrandEntity getBrandByNameAndCategory(String name, ModelCategoryEnum category);
}
