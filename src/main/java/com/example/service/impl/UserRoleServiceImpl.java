package com.example.service.impl;

import com.example.model.entity.UserRoleEntity;
import com.example.repository.RoleRepository;
import com.example.service.UserRoleService;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    private final RoleRepository repository;

    public UserRoleServiceImpl(RoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserRoleEntity getById(Long id) {

        return repository.findById(id).orElse(null);
    }
}
