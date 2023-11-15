package com.example.repository;

import com.example.model.entity.OfferEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<OfferEntity,Long> {



    @Query(value = "select o.id,o.axles,o.moto_hours, o.condition_name,o.cubic_centimeters,o.description,o.engine,o.horse_power,o.image_url,o.second_image_url,o.mileage,o.price,o.transmission,o.year,o.model_id,o.post_by_id,o.standard_id,o.is_itvip from offers as o join users as u on o.post_by_id = u.id where u.role_id = 2", nativeQuery = true)
    List<OfferEntity> findAllVIPOffers();



    @Query(value = "select o.id,o.axles,o.moto_hours, o.condition_name,o.cubic_centimeters,o.description,o.engine,o.horse_power,o.image_url,o.second_image_url,o.mileage,o.price,o.transmission,o.year,o.model_id,o.post_by_id,o.standard_id,o.is_itvip from offers as o join models as m on o.model_id = m.id where m.category = :categoryName order by o.is_itvip",nativeQuery = true)
    List<OfferEntity> findOffers(@Param("categoryName") String category);



    @Query(value = "select * from offers as o where o.post_by_id = :id", nativeQuery = true)
    List<OfferEntity> myOffers(@Param("id") Long id);
}

