package com.example.repository;

import com.example.model.entity.UserRoleEntity;
import com.example.model.entity.enums.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<UserRoleEntity,Long> {


    UserRoleEntity findUserRoleEntitiesByRole(UserRoleEnum roleEnum);
}
