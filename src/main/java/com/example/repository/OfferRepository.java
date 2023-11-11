package com.example.repository;

import com.example.model.entity.OfferEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<OfferEntity,Long> {



    @Query(value = "select * from offers where offers.is_itvip is true", nativeQuery = true)
    List<OfferEntity> findAllVIPOffers();
}
