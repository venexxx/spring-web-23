package com.example.service;

import com.example.model.dto.AddCarOfferDTO;
import com.example.model.dto.AddMotorcycleDTO;
import com.example.model.dto.AddTruckDto;
import com.example.model.dto.OfferBidingModel;

import java.util.Set;

public interface OfferService {
    void addCarOffer(AddCarOfferDTO offerAddDTO);

    Set<OfferBidingModel> getVipOffers();
    Set<OfferBidingModel> getAllOffers(String categoryName);


    OfferBidingModel getOffer(Long id);

    void addTruckOffer(AddTruckDto offerAddDTO);

    void addMotorcycleOffer(AddMotorcycleDTO offerAddDTO);

    Set<OfferBidingModel> getOfferByCategory(String category);

    String delete(Long id);

    Set<OfferBidingModel> getMyOffers();

    Set<OfferBidingModel> getOffersById(Long id);
}
