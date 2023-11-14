package com.example.model.entity;

import com.example.model.entity.enums.UserRoleEnum;
import jakarta.persistence.*;

import java.util.List;


@Table(name = "roles")
@Entity
public class UserRoleEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Enumerated(EnumType.STRING)
  private UserRoleEnum role;







  public Long getId() {
    return id;
  }

  public UserRoleEntity setId(Long id) {
    this.id = id;
    return this;
  }

  public UserRoleEnum getRole() {
    return role;
  }


  public UserRoleEntity setRole(UserRoleEnum role) {
    this.role = role;
    return this;
  }
}