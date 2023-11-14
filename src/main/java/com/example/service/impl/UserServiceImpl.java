package com.example.service.impl;


import com.example.model.dto.UserBindingModel;
import com.example.model.dto.UserRegistrationDTO;
import com.example.model.dto.UserViewDTO;
import com.example.model.entity.UserEntity;
import com.example.model.entity.UserRoleEntity;
import com.example.model.entity.enums.UserRoleEnum;
import com.example.repository.RoleRepository;
import com.example.repository.UserRepository;
import com.example.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final ModelMapper modelMapper;

  private final RoleRepository roleRepository;

  public UserServiceImpl(
          UserRepository userRepository,
          PasswordEncoder passwordEncoder, ModelMapper modelMapper, RoleRepository roleRepository) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.modelMapper = modelMapper;
    this.roleRepository = roleRepository;
  }

  @Override
  public void registerUser(
      UserRegistrationDTO userRegistrationDTO) {

    userRepository.save(map(userRegistrationDTO));
  }

  @Override
  public boolean checkForUniqueEmail(String email) {
    if (email.isBlank()){
      return true;
    }
    UserEntity user = userRepository.findByEmail(email).orElse(null);

    return user == null;
  }

  @Override
  public UserViewDTO getByEmail(String email){
    UserEntity user = userRepository.findByEmail(email).orElse(null);

    return modelMapper.map(user,UserViewDTO.class);
  }

  @Override
  public UserEntity getByUserEmail(String email) {
    return userRepository.findByEmail(email).orElse(null);
  }

  @Override
  public Set<UserBindingModel> getAllUsers() {
    return userRepository.allUsers().stream().map(u -> {
      UserBindingModel user = modelMapper.map(u, UserBindingModel.class);
      return user;
    }).collect(Collectors.toSet());
  }

  @Override
  public UserBindingModel getById(Long id) {
    UserEntity user = userRepository.findById(id).orElse(null);
    return modelMapper.map(user, UserBindingModel.class);
  }

  @Override
  public void banUser(Long id) {
    UserEntity byId = userRepository.findById(id).orElse(null);
    userRepository.delete(byId);
    byId.setBanned(true);
    userRepository.save(byId);
  }

  @Override
  public void unbanUser(Long id) {
    UserEntity byId = userRepository.findById(id).orElse(null);
    userRepository.delete(byId);
    byId.setBanned(false);
    userRepository.save(byId);
  }

  @Override
  public List<String> deleteBannedUsers() {
    List<UserEntity> allBannedUsers = userRepository.findAllBannedUsers();

    userRepository.deleteAll(allBannedUsers);
    return allBannedUsers.stream().map(u -> u.getFirstName() + " " + u.getLastName()).toList();
  }

  private UserEntity map(UserRegistrationDTO userRegistrationDTO) {
    UserRoleEntity role = roleRepository.getReferenceById(3L);
    role.setRole(UserRoleEnum.USER);
    List<UserRoleEntity> roles = new ArrayList<>();
    roles.add(role);
    return new UserEntity()
        .setActive(true)
        .setFirstName(userRegistrationDTO.getFirstName())
        .setLastName(userRegistrationDTO.getLastName())
        .setEmail(userRegistrationDTO.getEmail())
        .setBanned(false)
            .setRoleId(3L)

        .setProfilePictureUrl(userRegistrationDTO.getProfilePictureUrl())
        .setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));
  }
}