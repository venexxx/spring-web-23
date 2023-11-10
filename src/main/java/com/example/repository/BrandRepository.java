package com.example.repository;

import com.example.model.entity.BrandEntity;

import com.example.model.entity.enums.ModelCategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface BrandRepository extends JpaRepository<BrandEntity, Long> {



    Set<BrandEntity> findAllByModelCategoryEnumOrderByName(ModelCategoryEnum modelCategoryEnum);


    @Query(value = "select * from brands order by name",
    nativeQuery = true)
    List<BrandEntity> findAllSorted();



    List<BrandEntity> findAllByName(String  name);

    BrandEntity findByName(String  name);


    Optional<BrandEntity> findByNameAndModelCategoryEnum(String name,ModelCategoryEnum category);
}
