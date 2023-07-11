package com.kayaspring.kayaspring.api.middlewares.InitialData;

import com.kayaspring.kayaspring.data.repositories.IRoleRepository;
import com.kayaspring.kayaspring.entities.enums.UserRoleEnums;
import com.kayaspring.kayaspring.entities.models.User.UserRole;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class InsertInitialRoles implements CommandLineRunner {

    private final IRoleRepository roleRepository;

    public InsertInitialRoles(IRoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        insertRoleIfNotExists(UserRoleEnums.ROLE_USER);
        insertRoleIfNotExists(UserRoleEnums.ROLE_MODERATOR);
        insertRoleIfNotExists(UserRoleEnums.ROLE_ADMIN);
    }

    private void insertRoleIfNotExists(UserRoleEnums roleName) {
        Optional<UserRole> optionalRole = roleRepository.findByName(roleName);
        if (!optionalRole.isPresent()) {
            roleRepository.save(new UserRole(roleName));
        }
    }


}