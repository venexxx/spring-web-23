package com.example.web;


import com.example.model.dto.OfferBidingModel;
import com.example.model.dto.UserBindingModel;
import com.example.model.dto.UserRegistrationDTO;
import com.example.model.entity.UserEntity;
import com.example.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Set;

@RequestMapping("/users")
@Controller
public class UserController {
  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/register")
  public String register() {
    return "auth-register";
  }



  @PostMapping("/register")
  public String register(@Valid @ModelAttribute("userRegistrationDTO") UserRegistrationDTO userRegistrationDTO, BindingResult result, RedirectAttributes redirectAttributes) {

    boolean userHasUniqueEmail = userService.checkForUniqueEmail(userRegistrationDTO.getEmail());

    if (!userRegistrationDTO.getPassword().equals(userRegistrationDTO.getConfirmPassword())) {
      result.addError(
              new FieldError(
                      "differentConfirmPassword",
                      "confirmPassword",
                      "Passwords must be the same."));
    }

    if (!userHasUniqueEmail) {
      result.addError(
              new FieldError(
                      "emailIsNotUnique",
                      "email",
                      "User with this email already exist."));


    }


    if (result.hasErrors()) {
      redirectAttributes
              .addFlashAttribute("userRegistrationDTO", userRegistrationDTO)
              .addFlashAttribute("org.springframework.validation.BindingResult.userRegistrationDTO", result);

      return "redirect:/users/register";
    }

    this.userService.registerUser(userRegistrationDTO);
    return "redirect:/users/login";
  }

  @GetMapping("/login")
  public String login() {
    return "auth-login";
  }

  @PostMapping("/login-error")
  public String onFailure(
          @ModelAttribute("email") String email,
          Model model) {

    model.addAttribute("email", email);
    model.addAttribute("bad_credentials", "true");

    return "auth-login";
  }


  @GetMapping("/all")
  public ModelAndView allUsers(ModelAndView modelAndView){
    modelAndView.setViewName("all-users");
    modelAndView.addObject("users",userService.getAllUsers());
    return modelAndView;
  }

  // <- I made this method to be with Rest in my api directory! ->



//  GetMapping("/details/{id}")
//  public ModelAndView userDetails(@PathVariable Long id, ModelAndView modelAndView){
//    modelAndView.setViewName("user-details");
//    UserBindingModel user = userService.getById(id);
//    modelAndView.addObject("user",user);
//    return modelAndView;
//
//  }

    @GetMapping("/seller-details/{id}")
  public ModelAndView userDetails(@PathVariable Long id, ModelAndView modelAndView){
    modelAndView.setViewName("user-details");
    UserBindingModel user = userService.getById(id);
    modelAndView.addObject("user",user);
    return modelAndView;

  }


  @GetMapping("/ban-user/{id}")
  public ModelAndView banUser(@PathVariable Long id, ModelAndView modelAndView){
    modelAndView.setViewName("redirect:/users/all");
    userService.banUser(id);
    return modelAndView;

  }

  @GetMapping("/unban-user/{id}")
  public ModelAndView unbanUser(@PathVariable Long id, ModelAndView modelAndView){
    modelAndView.setViewName("redirect:/users/all");
    userService.unbanUser(id);
    return modelAndView;

  }

  @GetMapping("/banned")
  public ModelAndView bannedUser(ModelAndView modelAndView){
    modelAndView.setViewName("banned");
    return modelAndView;

  }

  @GetMapping("make-vip/{id}")
  public ModelAndView makeUseVip(@PathVariable Long id, ModelAndView modelAndView){
    userService.makeUserVip(id);
    modelAndView.setViewName("redirect:/users/all");
    return modelAndView;
  }

  @GetMapping("make-user/{id}")
  public ModelAndView makeUseUser(@PathVariable Long id, ModelAndView modelAndView){
    userService.makeUserUser(id);
    modelAndView.setViewName("redirect:/users/all");
    return modelAndView;
  }



  @ModelAttribute("userRegistrationDTO")
  public UserRegistrationDTO userRegistrationDTO() {
    return new UserRegistrationDTO();
  }






}