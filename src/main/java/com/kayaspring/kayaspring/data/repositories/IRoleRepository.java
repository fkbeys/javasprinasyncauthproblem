package com.kayaspring.kayaspring.data.repositories;

import com.kayaspring.kayaspring.entities.enums.UserRoleEnums;
import com.kayaspring.kayaspring.entities.models.User.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRoleRepository extends JpaRepository<UserRole, Long> {
    Optional<UserRole> findByName(UserRoleEnums name);
}