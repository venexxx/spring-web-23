package com.example.init;

import com.example.model.entity.UserRoleEntity;
import com.example.model.entity.enums.UserRoleEnum;
import com.example.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class RoleInit implements CommandLineRunner {

    private final RoleRepository repository;

    public RoleInit(RoleRepository repository) {
        this.repository = repository;
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

        repository.saveAll(roles);

    }
}
