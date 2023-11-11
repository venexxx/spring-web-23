package com.example.web;

import com.example.model.dto.OfferBidingModel;
import com.example.service.OfferService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Set;

@Controller
public class HomeController {

    private final OfferService offerService;

    public HomeController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping("/")
    public ModelAndView home(ModelAndView modelAndView){
        modelAndView.setViewName("index");
       Set<OfferBidingModel> vipOffers =  offerService.getVipOffers();
       modelAndView.addObject("vipOffers",vipOffers);
       return modelAndView;

    }
}
