package com.example.repository;


import com.example.model.entity.BrandEntity;
import com.example.model.entity.ModelEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelRepository extends JpaRepository<ModelEntity, Long> {


    ModelEntity findByNameAndBrand(String name, BrandEntity brand);
    ModelEntity findByBrand( BrandEntity brand);
}
