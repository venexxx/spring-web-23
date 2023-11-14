package com.example.service;


import com.example.model.dto.UserBindingModel;
import com.example.model.dto.UserRegistrationDTO;
import com.example.model.dto.UserViewDTO;
import com.example.model.entity.UserEntity;

import java.util.List;
import java.util.Set;

public interface UserService {

  void registerUser(UserRegistrationDTO userRegistrationDTO);

  boolean checkForUniqueEmail(String email);
  UserViewDTO getByEmail(String email);
  UserEntity getByUserEmail(String email);

    Set<UserBindingModel> getAllUsers();

    UserBindingModel getById(Long id);

    void banUser(Long id);
    void unbanUser(Long id);

    List<String> deleteBannedUsers();
}
