package com.example.service.impl;

import com.example.model.dto.OfferAddDTO;
import com.example.model.entity.OfferEntity;
import com.example.model.entity.enums.ConditionEnum;
import com.example.model.entity.enums.EngineEnum;
import com.example.model.entity.enums.TransmissionEnum;
import com.example.repository.OfferRepository;
import com.example.service.EuroStandardService;
import com.example.service.ModelService;
import com.example.service.OfferService;
import com.example.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class OfferServiceImpl implements OfferService {
    private final ModelMapper mapper;
    private final OfferRepository repository;
    private final ModelService modelService;
    private final EuroStandardService standardService;

    private final UserService userService;



    public OfferServiceImpl(ModelMapper mapper, OfferRepository repository, ModelService modelService, EuroStandardService standardService, UserService userService) {
        this.mapper = mapper;
        this.repository = repository;
        this.modelService = modelService;
        this.standardService = standardService;
        this.userService = userService;

    }

    @Override
    public void addOffer(OfferAddDTO offerAddDTO) {

        OfferEntity offer = map(offerAddDTO);
        repository.save(offer);
    }

    public OfferEntity map(OfferAddDTO offerAddDTO){
        OfferEntity offer = new OfferEntity();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();

        offer.setModel(modelService.getByNameAndBrand(offerAddDTO.getModel(),"CAR"));
        offer.setPrice(offerAddDTO.getPrice());
        offer.setConditionName(ConditionEnum.valueOf(offerAddDTO.getConditionName()));
        offer.setHorsePower(offerAddDTO.getHorsePower());
        offer.setStandard(standardService.getStandard(offerAddDTO.getStandard()));
        offer.setEngine(EngineEnum.valueOf(offerAddDTO.getEngine()));
        offer.setTransmission(TransmissionEnum.valueOf(offerAddDTO.getTransmission()));
        offer.setYear(offerAddDTO.getYear());
        offer.setMileage(offerAddDTO.getMileage());
        offer.setDescription(offerAddDTO.getDescription());
        offer.setImageUrl(offerAddDTO.getImageUrl());
        offer.setPostBy(userService.getByUserEmail(name));
        offer.setCubicCentimeters(offerAddDTO.getCubicCentimeters());
        return offer;
    }
}
