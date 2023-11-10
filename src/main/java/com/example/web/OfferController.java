package com.example.web;

import com.example.service.BrandService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/offers")
public class OfferController {

    private final BrandService brandService;

    public OfferController(BrandService brandService) {
        this.brandService = brandService;
    }


    @GetMapping("/catalog")
    public ModelAndView catalog(ModelAndView modelAndView){
        modelAndView.setViewName("add-offer-catalog");
        return modelAndView;
    }





}
