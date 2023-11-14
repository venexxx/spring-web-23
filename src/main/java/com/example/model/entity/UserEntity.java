package com.example.model.entity;

import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity{

  @Column(unique = true)
  private String email;

  @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
  @JoinTable(
      name = "users_roles",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "role_id"))
  private List<UserRoleEntity> roles = new ArrayList<>();



  private Long roleId;
  private String password;

  private String firstName;

  private String lastName;

  private boolean active;

  private String profilePictureUrl;
  private boolean isBanned;





  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL,
          mappedBy = "postBy")
  private Set<OfferEntity> offers = new LinkedHashSet<>();

  public boolean isBanned() {
    return isBanned;
  }

  public UserEntity setBanned(boolean banned) {
    isBanned = banned;
    return this;
  }

  public Long getRoleId() {
    return roleId;
  }

  public UserEntity setRoleId(Long roleId) {
    this.roleId = roleId;
    return this;
  }

  public String getProfilePictureUrl() {
    return profilePictureUrl;
  }

  public UserEntity setProfilePictureUrl(String profilePictureUrl) {
    this.profilePictureUrl = profilePictureUrl;
    return this;
  }

  public Set<OfferEntity> getOffers() {
    return offers;
  }

  public void setOffers(Set<OfferEntity> offers) {
    this.offers = offers;
  }

  public String getEmail() {
    return email;
  }

  public UserEntity setEmail(String email) {
    this.email = email;
    return this;
  }

  public String getPassword() {
    return password;
  }

  public UserEntity setPassword(String password) {
    this.password = password;
    return this;
  }

  public String getFirstName() {
    return firstName;
  }

  public UserEntity setFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public String getLastName() {
    return lastName;
  }

  public UserEntity setLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public boolean isActive() {
    return active;
  }

  public UserEntity setActive(boolean active) {
    this.active = active;
    return this;
  }

  public List<UserRoleEntity> getRoles() {
    return roles;
  }

  public UserEntity setRoles(List<UserRoleEntity> roles) {
    this.roles = roles;
    return this;
  }
}