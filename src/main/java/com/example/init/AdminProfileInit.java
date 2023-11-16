package com.example.init;

import com.example.model.entity.UserEntity;
import com.example.model.entity.UserRoleEntity;
import com.example.model.entity.enums.UserRoleEnum;
import com.example.repository.RoleRepository;
import com.example.repository.UserRepository;
import com.example.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component

public class AdminProfileInit implements CommandLineRunner {


    private final UserRepository userRepository;
    private final UserService userService;

    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;



    public AdminProfileInit(UserRepository userRepository, UserService userService, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
//        UserEntity adminUser = new UserEntity();
//        if (userRepository.count() ==  0){
//            adminUser.setId(1L);
//            adminUser.setFirstName("Admin");
//            adminUser.setLastName("Adminov");
//            adminUser.setEmail("admin@example.com");
//            adminUser.setRoleId(1L);
//            adminUser.setPassword(passwordEncoder.encode("topsecred"));
//            adminUser.setBanned(false);
//            adminUser.setProfilePictureUrl("https://freesvg.org/img/abstract-user-flat-4.png");
//            userRepository.save(adminUser);
//
//
//        }
    }
}
