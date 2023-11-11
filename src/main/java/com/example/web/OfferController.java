package com.example.web;

import com.example.model.dto.*;
import com.example.model.entity.enums.*;
import com.example.service.BrandService;
import com.example.service.EuroStandardService;
import com.example.service.OfferService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller

public class OfferController {

    private final BrandService brandService;

    private final EuroStandardService standardService;
    private final OfferService offerService;

    public OfferController(BrandService brandService, EuroStandardService standardService, OfferService offerService) {
        this.brandService = brandService;
        this.standardService = standardService;
        this.offerService = offerService;
    }


    @GetMapping("offers/catalog")
    public ModelAndView catalog(ModelAndView modelAndView){
        modelAndView.setViewName("add-offer-catalog");
        return modelAndView;
    }
    @GetMapping("offers/add-car")
    public ModelAndView addCar(ModelAndView modelAndView){

        modelAndView.setViewName("add-car-offer");

        Set<BrandAndModelBindingModel> carBrands = brandService.getAllBrandsAndModels(ModelCategoryEnum.CAR);

        EngineEnum[] engines = EngineEnum.values();


        modelAndView.addObject("carBrands",carBrands);



        return modelAndView;
    }

    @GetMapping("offers/add-truck")
    public ModelAndView addTruck(ModelAndView modelAndView){

        modelAndView.setViewName("add-truck-offer");

        Set<BrandAndModelBindingModel> truckBrands = brandService.getAllBrandsAndModels(ModelCategoryEnum.TRUCK);



        modelAndView.addObject("truckBrands",truckBrands);



        return modelAndView;
    }

    @PostMapping("offers/add-truck")
    public String addTruck(@Valid AddTruckDto offerAddDTO, BindingResult result, RedirectAttributes redirectAttributes){


        if (result.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("offerAddDTO", offerAddDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.offerAddDTO", result);

            return "redirect:/offers/add-car";
        }

        offerService.addTruckOffer(offerAddDTO);

        return  "redirect:/offers/add-truck";
    }


    @GetMapping("offers/add-motorcycle")
    public ModelAndView addMotorcycle(ModelAndView modelAndView){

        modelAndView.setViewName("add-motorcycle-offer");

        Set<BrandAndModelBindingModel> motorcycleBrands = brandService.getAllBrandsAndModels(ModelCategoryEnum.MOTORCYCLE);



        modelAndView.addObject("motorcycleBrands",motorcycleBrands);



        return modelAndView;
    }

    @PostMapping("offers/add-motorcycle")
    public String addMotorCycle(@Valid AddMotorcycleDTO offerAddDTO, BindingResult result, RedirectAttributes redirectAttributes){


        if (result.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("offerAddDTO", offerAddDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.offerAddDTO", result);

            return "redirect:/offers/add-car";
        }

        offerService.addMotorcycleOffer(offerAddDTO);

        return  "redirect:/offers/add-motorcycle";
    }




    @PostMapping("offers/add-car")
    public String addCar(@Valid AddCarOfferDTO offerAddDTO, BindingResult result, RedirectAttributes redirectAttributes){


        if (result.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("offerAddDTO", offerAddDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.offerAddDTO", result);

            return "redirect:/offers/add-car";
        }

        offerService.addCarOffer(offerAddDTO);

        return  "redirect:/offers/add-car";
    }

    @GetMapping("offers/details/{id}")
    public ModelAndView offerDetails(@PathVariable Long id, ModelAndView modelAndView){
        modelAndView.setViewName("details");
        OfferBidingModel offer = offerService.getOffer(id);
        modelAndView.addObject("offer",offer);
        return modelAndView;

    }

    @ModelAttribute
    public AddCarOfferDTO addCarOfferDTO() {
        return new AddCarOfferDTO();
    }



    @ModelAttribute
    public AddTruckDto addTruckDto() {
        return new AddTruckDto();
    }

    @ModelAttribute
    public AddMotorcycleDTO addMotorcycleDTO() {
        return new AddMotorcycleDTO();
    }



    @ModelAttribute("transmissions")
    public TransmissionEnum[] transmissions() {
        return TransmissionEnum.values();
    }
    @ModelAttribute("engines")
    public EngineEnum[] engines() {
        return EngineEnum.values();
    }

    @ModelAttribute("axles")
    public AxlesEnum[] axles() {
        return AxlesEnum.values();
    }

    @ModelAttribute("standards")
    public List<StandardDTO> standards() {
        return standardService.getAllStandardsNames();
    }

    @ModelAttribute("conditions")
    public List<ConditionDTO> conditions() {
        return Arrays.stream(ConditionEnum.values()).map(c -> {
            ConditionDTO condition = new ConditionDTO();
            String conditionName = "";
            switch (c){
                case NEW -> conditionName = "New";
                case USED -> conditionName = "Used";
                case FOR_PARTS -> conditionName ="For Parts";
            }
            condition.setName(conditionName);
            condition.setCondition(c.toString());
            return condition;
        }).collect(Collectors.toList());
    }





}
