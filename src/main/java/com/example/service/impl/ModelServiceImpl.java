package com.example.service.impl;

import com.example.model.dto.AddModelDTO;
import com.example.model.entity.BrandEntity;
import com.example.model.entity.ModelEntity;
import com.example.model.entity.enums.ModelCategoryEnum;
import com.example.repository.ModelRepository;
import com.example.service.BrandService;
import com.example.service.ModelService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ModelServiceImpl implements ModelService {
    private final ModelRepository modelRepository;
    private final ModelMapper mapper;
    private final BrandService brandService;

    public ModelServiceImpl(ModelRepository modelRepository, ModelMapper mapper, BrandService brandService) {
        this.modelRepository = modelRepository;
        this.mapper = mapper;
        this.brandService = brandService;
    }

    @Override
    public boolean checkForTheSameModel(AddModelDTO addModelDTO) {
        BrandEntity checkBrand = brandService.getBrandByNameAndCategory(addModelDTO.getBrand(),ModelCategoryEnum.valueOf(addModelDTO.getCategory()));
        ModelEntity check = modelRepository.findByNameAndBrand(addModelDTO.getName(),checkBrand);
        if (check != null){
            return true;
        }else {
            return false;
        }

    }

    @Override
    public void addModel(AddModelDTO addModelDTO) {

        modelRepository.save(map(addModelDTO));


    }

    @Override
    public ModelEntity getByNameAndBrand(String model,String categoryName) {
        String brandName = model.split(" ")[0];
        String modelName = model.split(" ")[1];
        ModelCategoryEnum category = ModelCategoryEnum.valueOf(categoryName);
        BrandEntity brand = brandService.getBrandByNameAndCategory(brandName,category);

        return modelRepository.findByNameAndBrand(modelName, brand);
    }

    private ModelEntity map(AddModelDTO addModelDTO) {
        ModelEntity model = mapper.map(addModelDTO,ModelEntity.class);
        ModelCategoryEnum category = ModelCategoryEnum.valueOf(addModelDTO.getCategory());
        BrandEntity brand = brandService.getBrandByNameAndCategory(addModelDTO.getBrand(),category);

        model.setCategory(category);
        model.setBrand(brand);



        return model ;
    }
}
