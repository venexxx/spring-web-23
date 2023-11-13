package com.example.service.impl;

import com.example.model.dto.AddCarOfferDTO;
import com.example.model.dto.AddMotorcycleDTO;
import com.example.model.dto.AddTruckDto;
import com.example.model.dto.OfferBidingModel;
import com.example.model.entity.OfferEntity;
import com.example.model.entity.UserEntity;
import com.example.model.entity.UserRoleEntity;
import com.example.model.entity.enums.AxlesEnum;
import com.example.model.entity.enums.ConditionEnum;
import com.example.model.entity.enums.EngineEnum;
import com.example.model.entity.enums.TransmissionEnum;
import com.example.repository.OfferRepository;
import com.example.service.*;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OfferServiceImpl implements OfferService {
    private final ModelMapper mapper;

    private final OfferRepository repository;
    private final ModelService modelService;
    private final EuroStandardService standardService;
    private final UserRoleService roleService;

    private final UserService userService;



    public OfferServiceImpl(ModelMapper mapper, OfferRepository repository, ModelService modelService, EuroStandardService standardService, UserRoleService roleService, UserService userService) {
        this.mapper = mapper;
        this.repository = repository;
        this.modelService = modelService;
        this.standardService = standardService;
        this.roleService = roleService;
        this.userService = userService;

    }

    @Override
    public void addCarOffer(AddCarOfferDTO offerAddDTO) {

        OfferEntity offer = map(offerAddDTO);
        repository.save(offer);
    }

    @Override
    public Set<OfferBidingModel> getVipOffers() {
        UserRoleEntity role = roleService.getById(2L);
        Set<OfferBidingModel> vips = repository.findAllVIPOffers().stream().map(o -> mapper.map(o, OfferBidingModel.class)).collect(Collectors.toSet());
        return vips;

    }

    @Override
    public Set<OfferBidingModel> getAllOffers(String cName) {
        Set<OfferBidingModel> offers = repository.findOffers(cName).stream().map(o -> mapper.map(o, OfferBidingModel.class)).collect(Collectors.toSet());
        return offers;
    }



    @Override
    public OfferBidingModel getOffer(Long id) {
        OfferEntity offerEntity = repository.findById(id).orElse(null);
        OfferBidingModel offer = mapper.map(offerEntity, OfferBidingModel.class);
        offer.setPosBy(offerEntity.getPostBy().getId());
        return offer;
    }

    @Override
    public void addTruckOffer(AddTruckDto offerAddDTO) {
        OfferEntity offer = map(offerAddDTO);
        repository.save(offer);
    }

    @Override
    public void addMotorcycleOffer(AddMotorcycleDTO offerAddDTO) {
        OfferEntity offer = map(offerAddDTO);
        repository.save(offer);
    }

    @Override
    public Set<OfferBidingModel> getOfferByCategory(String category) {
        Set<OfferBidingModel> offers = new LinkedHashSet<>();
        offers = repository.findOffers(category).stream().map(o -> mapper.map(o, OfferBidingModel.class)).collect(Collectors.toSet());
        return offers;

    }

    @Override
    public String delete(Long Id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        OfferEntity offerForDelete = repository.findById(Id).orElse(null);

        UserEntity user = userService.getByUserEmail(name);
        if (user.getId() == offerForDelete.getPostBy().getId() || auth.getAuthorities().toArray()[0].toString().equals("ROLE_ADMIN")){
            repository.delete(offerForDelete);
            return "redirect:/";
        }else {
            return "redirect:/users/login";
        }

    }

    @Override
    public Set<OfferBidingModel> getMyOffers() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        UserEntity user = userService.getByUserEmail(name);
        Set<OfferBidingModel> myOffers = repository.myOffers(user.getId()).stream().map(o -> mapper.map(o, OfferBidingModel.class)).collect(Collectors.toSet());
        return myOffers;
    }

    @Override
    public Set<OfferBidingModel> getOffersById(Long id) {
        return repository.myOffers(id).stream().map(o -> mapper.map(o, OfferBidingModel.class)).collect(Collectors.toSet());
    }

    public OfferEntity map(AddCarOfferDTO offerAddDTO){
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
        offer.setSecondImageUrl(offerAddDTO.getSecondImageUrl());
        offer.setPostBy(userService.getByUserEmail(name));
        offer.setCubicCentimeters(offerAddDTO.getCubicCentimeters());
        offer.setItVIP(auth.getAuthorities().toArray()[0].toString().equals("ROLE_VIP") || auth.getAuthorities().toArray()[0].toString().equals("ROLE_ADMIN") );

        return offer;
    }
    public OfferEntity map(AddTruckDto offerAddDTO){
        OfferEntity offer = new OfferEntity();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        String name = auth.getName();

        offer.setModel(modelService.getByNameAndBrand(offerAddDTO.getModel(),"TRUCK"));
        offer.setPrice(offerAddDTO.getPrice());
        offer.setConditionName(ConditionEnum.valueOf(offerAddDTO.getConditionName()));
        offer.setHorsePower(offerAddDTO.getHorsePower());
        offer.setStandard(standardService.getStandard(offerAddDTO.getStandard()));
        offer.setAxles(AxlesEnum.valueOf(offerAddDTO.getAxles()));
        offer.setTransmission(TransmissionEnum.valueOf(offerAddDTO.getTransmission()));
        offer.setYear(offerAddDTO.getYear());
        offer.setMileage(offerAddDTO.getMileage());
        offer.setDescription(offerAddDTO.getDescription());
        offer.setImageUrl(offerAddDTO.getImageUrl());
        offer.setSecondImageUrl(offerAddDTO.getSecondImageUrl());
        offer.setPostBy(userService.getByUserEmail(name));
        offer.setCubicCentimeters(offerAddDTO.getCubicCentimeters());
        offer.setItVIP(auth.getAuthorities().toArray()[0].toString().equals("ROLE_VIP") || auth.getAuthorities().toArray()[0].toString().equals("ROLE_ADMIN"));

        return offer;
    }

    public OfferEntity map(AddMotorcycleDTO offerAddDTO){
        OfferEntity offer = new OfferEntity();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        String name = auth.getName();

        offer.setModel(modelService.getByNameAndBrand(offerAddDTO.getModel(),"MOTORCYCLE"));
        offer.setPrice(offerAddDTO.getPrice());
        offer.setConditionName(ConditionEnum.valueOf(offerAddDTO.getConditionName()));
        offer.setHorsePower(offerAddDTO.getHorsePower());
        offer.setMotoHours(offerAddDTO.getMotoHours());
        offer.setStandard(standardService.getStandard(offerAddDTO.getStandard()));
        offer.setTransmission(TransmissionEnum.valueOf(offerAddDTO.getTransmission()));
        offer.setYear(offerAddDTO.getYear());
        offer.setMileage(offerAddDTO.getMileage());
        offer.setDescription(offerAddDTO.getDescription());
        offer.setImageUrl(offerAddDTO.getImageUrl());
        offer.setSecondImageUrl(offerAddDTO.getSecondImageUrl());
        offer.setPostBy(userService.getByUserEmail(name));
        offer.setCubicCentimeters(offerAddDTO.getCubicCentimeters());
        offer.setItVIP(auth.getAuthorities().toArray()[0].toString().equals("ROLE_VIP") || auth.getAuthorities().toArray()[0].toString().equals("ROLE_ADMIN"));

        return offer;
    }
}
