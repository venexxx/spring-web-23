package com.example.web;

import com.example.model.dto.*;
import com.example.model.entity.enums.ConditionEnum;
import com.example.model.entity.enums.EngineEnum;
import com.example.model.entity.enums.ModelCategoryEnum;
import com.example.model.entity.enums.TransmissionEnum;
import com.example.service.BrandService;
import com.example.service.EuroStandardService;

import com.example.service.OfferService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class CarController {

    private final BrandService brandService;
    private final EuroStandardService standardService;
    private final OfferService offerService;

    public CarController(BrandService brandService, EuroStandardService standardService, OfferService offerService) {
        this.brandService = brandService;
        this.standardService = standardService;
        this.offerService = offerService;
    }
    @GetMapping("offers/add-car")
    public ModelAndView addCar(ModelAndView modelAndView){

        modelAndView.setViewName("add-car-offer");

        Set<BrandAndModelBindingModel> carBrands = brandService.getAllBrandsAndModels(ModelCategoryEnum.CAR);

        EngineEnum[] engines = EngineEnum.values();


        modelAndView.addObject("carBrands",carBrands);



        return modelAndView;
    }


    @PostMapping("offers/add-car")
    public String addCar(@Valid OfferAddDTO offerAddDTO, BindingResult result, RedirectAttributes redirectAttributes){


        if (result.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("offerAddDTO", offerAddDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.offerAddDTO", result);

            return "redirect:/offers/add-car";
        }

        offerService.addOffer(offerAddDTO);

        return  "redirect:/offers/add-car";
    }

    @ModelAttribute
    public OfferAddDTO offerAddDTO() {
        return new OfferAddDTO();
    }



    @ModelAttribute("transmissions")
    public TransmissionEnum[] transmissions() {
        return TransmissionEnum.values();
    }
    @ModelAttribute("engines")
    public EngineEnum[] engines() {
        return EngineEnum.values();
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
