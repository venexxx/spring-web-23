package com.example.web;

import com.example.model.dto.AddModelDTO;
import com.example.model.dto.BrandModelDTO;
import com.example.model.entity.enums.ModelCategoryEnum;
import com.example.service.BrandService;
import com.example.service.ModelService;
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
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class ModelAddController {

    private final ModelService modelService;
    private final BrandService brandService;

    public ModelAddController(ModelService modelService, BrandService brandService) {
        this.modelService = modelService;
        this.brandService = brandService;
    }

    @GetMapping("model-add")
    public ModelAndView addModel(ModelAndView modelAndView){
        modelAndView.setViewName("model-add");
        Set<ModelCategoryEnum> categories = Arrays
                .stream(ModelCategoryEnum.values())
                .collect(Collectors.toSet());
        modelAndView.addObject("categories",categories);
        Set<BrandModelDTO> brands = new HashSet<>();

        BrandModelDTO cars = new BrandModelDTO("CAR",brandService.getAllBrands(ModelCategoryEnum.CAR));
        BrandModelDTO trucks = new BrandModelDTO("TRUCK", brandService.getAllBrands(ModelCategoryEnum.TRUCK));
        BrandModelDTO motorcycles = new BrandModelDTO("MOTORCYCLE", brandService.getAllBrands(ModelCategoryEnum.MOTORCYCLE));

        brands.add(cars);
        brands.add(trucks);
        brands.add(motorcycles);

        modelAndView.addObject("brands",brands);



        return modelAndView;
    }

    @PostMapping("/model/add")
    public ModelAndView addModel(ModelAndView modelAndView, @Valid AddModelDTO addModelDTO, BindingResult result, RedirectAttributes redirectAttributes){

        String category = addModelDTO.getBrand().split(" ")[2];
        String brand = addModelDTO.getBrand().split(" ")[0];

        addModelDTO.setCategory(category);
        addModelDTO.setBrand(brand);

        boolean hasSameModel = modelService.checkForTheSameModel(addModelDTO);

        if (hasSameModel) {
            result.addError(
                    new FieldError(
                            "modelExist",
                            "brand",
                            "Model in this brand or category exist."));

        }
        if (result.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("addModelDTO", addModelDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.addModelDTO", result);

            modelAndView.setViewName("redirect:/model-add");
            return modelAndView;
        }
        modelAndView.setViewName("redirect:/");

        this.modelService.addModel(addModelDTO);
        return modelAndView;


    }

    @ModelAttribute
    public AddModelDTO addModelDTO() {
        return new AddModelDTO();
    }


}
