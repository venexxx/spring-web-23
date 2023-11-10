package com.example.service;


import com.example.model.dto.UserRegistrationDTO;
import com.example.model.dto.UserViewDTO;
import com.example.model.entity.UserEntity;

public interface UserService {

  void registerUser(UserRegistrationDTO userRegistrationDTO);

  boolean checkForUniqueEmail(String email);
  UserViewDTO getByEmail(String email);
  UserEntity getByUserEmail(String email);
}
