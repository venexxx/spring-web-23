package com.example.web;

import com.example.model.dto.AddBrandDTO;
import com.example.model.dto.BrandBindingModel;
import com.example.model.entity.enums.ModelCategoryEnum;
import com.example.service.BrandService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Controller

public class BrandController {

    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }


    @GetMapping("/brands")
    public ModelAndView allBrands(ModelAndView modelAndView){

        modelAndView.setViewName("brands");

        Set<BrandBindingModel> carBrands = brandService.getAllBrands(ModelCategoryEnum.CAR);
        Set<BrandBindingModel> truckBrands = brandService.getAllBrands(ModelCategoryEnum.TRUCK);
        Set<BrandBindingModel> motorcycleBrands = brandService.getAllBrands(ModelCategoryEnum.MOTORCYCLE);

        modelAndView.addObject("carBrands",carBrands);
        modelAndView.addObject("truckBrands",truckBrands);
        modelAndView.addObject("motorcycleBrands",motorcycleBrands);
        return modelAndView;
    }

    @GetMapping("/brands/add")
    public ModelAndView addBrand(ModelAndView modelAndView){
        modelAndView.setViewName("brand-add");
        Set<ModelCategoryEnum> categories = Arrays
                .stream(ModelCategoryEnum.values())
                .collect(Collectors.toSet());

        modelAndView.addObject("categories",categories);
        return modelAndView;
    }


    @PostMapping("/brands/add")
    public ModelAndView addBrand(ModelAndView modelAndView, @Valid AddBrandDTO addBrandDTO, BindingResult result, RedirectAttributes redirectAttributes){

        if (!brandService.checkForSameBrand(addBrandDTO)) {
            result.addError(
                    new FieldError(
                            "brandExist",
                            "brandName",
                            "Brand with this name and category exist."));
            result.addError(
                    new FieldError(
                            "brandExist",
                            "category",
                            "Brand with this name and category exist."));

        }
        if (result.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("addBrandDTO", addBrandDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.addBrandDTO", result);

            modelAndView.setViewName("redirect:/brands/add");
            return modelAndView;
        }
        brandService.addBrand(addBrandDTO);

        modelAndView.setViewName("redirect:/brands");
        return modelAndView;
    }

    @ModelAttribute
    public AddBrandDTO addBrandDTO() {
        return new AddBrandDTO();
    }
}
