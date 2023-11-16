package com.example.init;

import com.example.model.entity.UserEntity;
import com.example.model.entity.UserRoleEntity;
import com.example.model.entity.enums.UserRoleEnum;
import com.example.repository.RoleRepository;
import com.example.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class RoleInit implements CommandLineRunner {

    private final RoleRepository repository;
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public RoleInit(RoleRepository repository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        List<UserRoleEntity> roles = new ArrayList<>();

        if (!(repository.count() > 0)){

            Arrays.stream(UserRoleEnum.values()).forEach(r -> {
                UserRoleEntity role = new UserRoleEntity();
                role.setRole(r);
                roles.add(role);
            });
        }

        UserEntity adminUser = new UserEntity();
        if (userRepository.count() ==  0){
            adminUser.setId(1L);
            adminUser.setFirstName("Admin");
            adminUser.setLastName("Adminov");
            adminUser.setEmail("admin@example.com");
            adminUser.setRoleId(1L);
            adminUser.setPassword(passwordEncoder.encode("topsecred"));
            adminUser.setBanned(false);
            adminUser.setRoles(roles);
            adminUser.setProfilePictureUrl("https://freesvg.org/img/abstract-user-flat-4.png");
            userRepository.save(adminUser);


        }

        repository.saveAll(roles);

    }
}
