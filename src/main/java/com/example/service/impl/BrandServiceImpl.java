package com.example.service.impl;

import com.example.model.dto.AddBrandDTO;
import com.example.model.dto.BrandAndModelBindingModel;
import com.example.model.dto.BrandBindingModel;
import com.example.model.entity.BrandEntity;
import com.example.model.entity.ModelEntity;
import com.example.model.entity.enums.ModelCategoryEnum;
import com.example.repository.BrandRepository;
import com.example.repository.ModelRepository;
import com.example.service.BrandService;
import org.modelmapper.ModelMapper;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BrandServiceImpl implements BrandService {


    private final BrandRepository brandRepository;
    private final ModelRepository modelRepository;

    private final ModelMapper mapper;

    private final ApplicationEventMulticaster appEventPublisher;

    public BrandServiceImpl(BrandRepository brandRepository, ModelRepository modelRepository, ModelMapper mapper, ApplicationEventMulticaster applicationEventMulticaster) {
        this.brandRepository = brandRepository;
        this.modelRepository = modelRepository;
        this.mapper = mapper;
        this.appEventPublisher = applicationEventMulticaster;
    }

    @Override
    public void addBrand(AddBrandDTO addBrandDTO) {
        BrandEntity brand = mapper.map(addBrandDTO,BrandEntity.class);

        ModelCategoryEnum category = ModelCategoryEnum.valueOf(addBrandDTO.category);
        brand.setModelCategoryEnum(category);
        brandRepository.save(brand);

    }

    @Override
    public Set<BrandBindingModel> getAllBrands(ModelCategoryEnum category) {
        Set<BrandBindingModel> brands = brandRepository
                .findAllByModelCategoryEnumOrderByName(category)
                .stream().map(brand -> mapper.map(brand,BrandBindingModel.class))
                .collect(Collectors.toSet());

        return brands;
    }

    @Override
    public Set<BrandAndModelBindingModel> getAllBrandsAndModels(ModelCategoryEnum category) {

        Set<BrandAndModelBindingModel> brands = getAllBrands(category).stream().map(brand -> {

                            BrandAndModelBindingModel brandWithModel = new BrandAndModelBindingModel();
                            brandWithModel.setName(brand.getName());
                    brandWithModel.setCategory(brand.getCategory());

                    BrandEntity brandEntity = brandRepository.findByNameAndModelCategoryEnum(brand.getName(),category).orElse(null);
                    assert brandEntity != null;
                            Set<String> models = brandEntity.getModels().stream().map(ModelEntity::getName).collect(Collectors.toSet());

                            brandWithModel.setModels(models);

                            return brandWithModel;


                        }

                )
                .collect(Collectors.toSet());


        return brands;
    }

    @Override
    public Set<BrandBindingModel> getAllBrands() {
        Set<BrandBindingModel> brands = brandRepository
                .findAllSorted()
                .stream().map(brand -> mapper.map(brand,BrandBindingModel.class))
                .collect(Collectors.toSet());

        return brands;
    }

    @Override
    public boolean checkForSameBrand(AddBrandDTO brand) {
        List<BrandEntity> checkList = brandRepository.findAllByName(brand.getBrandName());
        BrandEntity check = new BrandEntity();
        if (checkList.size() == 0){
            return true;
        }
        check = checkList.get(0);
        return !check.getName().equals(brand.getBrandName()) || !check.getModelCategoryEnum().name().equals(brand.getCategory());


    }

    @Override
    public BrandEntity getBrandByNameAndCategory(String name, ModelCategoryEnum category) {
        BrandEntity brand = brandRepository.findByNameAndModelCategoryEnum(name, category).orElse(null);


        return brand;
    }
}
