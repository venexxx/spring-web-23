package com.example.web;


import com.example.model.dto.UserRegistrationDTO;
import com.example.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequestMapping("/users")
@Controller
public class UserRegistrationController {
  private final UserService userService;

  public UserRegistrationController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/register")
  public String register() {
    return "auth-register";
  }



  @PostMapping("/register")
  public String register(@Valid UserRegistrationDTO userRegistrationDTO, BindingResult result, RedirectAttributes redirectAttributes) {

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

  @ModelAttribute
  public UserRegistrationDTO userRegistrationDTO() {
    return new UserRegistrationDTO();
  }


}