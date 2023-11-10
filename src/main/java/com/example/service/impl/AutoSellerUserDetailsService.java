package com.example.service.impl;

import com.example.model.entity.UserEntity;
import com.example.model.entity.UserRoleEntity;
import com.example.repository.UserRepository;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class AutoSellerUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;

  public AutoSellerUserDetailsService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }


  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    return userRepository
        .findByEmail(email)
            .map(AutoSellerUserDetailsService::map)
        .orElseThrow(() -> new UsernameNotFoundException("User " + email + " not found!"));
  }




  private static UserDetails map(UserEntity userEntity) {
    return User

        .withUsername(userEntity.getEmail())
        .password(userEntity.getPassword())
        .authorities(userEntity.getRoles().stream().map(AutoSellerUserDetailsService::map).toList())
        .build();
  }

  private static GrantedAuthority map(UserRoleEntity userRoleEntity) {
    return new SimpleGrantedAuthority(
        "ROLE_" + userRoleEntity.getRole().name()
    );
  }
}