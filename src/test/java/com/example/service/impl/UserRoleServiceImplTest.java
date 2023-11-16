package com.example.service.impl;

import com.example.model.entity.UserRoleEntity;
import com.example.repository.RoleRepository;
import com.example.service.UserRoleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserRoleServiceImplTest {

    @Mock
    private RoleRepository roleRepository;

    private UserRoleService serviceToTest;

    @BeforeEach
    void setUp() {
        serviceToTest = new UserRoleServiceImpl(roleRepository) {
        };
    }


    @Test
    public void testGetByIdReturnsCorrectly(){
      assertNull(serviceToTest.getById(1L));

    }

}