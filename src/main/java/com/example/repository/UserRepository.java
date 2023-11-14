package com.example.repository;


import com.example.model.entity.OfferEntity;
import com.example.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
  Optional<UserEntity> findByEmail(String email);

  @Query(value = "select * from users as u where u.role_id != 1", nativeQuery = true)
  List<UserEntity> allUsers();

  @Query(value = "select * from users as u where u.is_banned = true", nativeQuery = true)
  List<UserEntity> findAllBannedUsers();
}