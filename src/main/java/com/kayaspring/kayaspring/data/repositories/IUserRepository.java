package com.kayaspring.kayaspring.data.repositories;

import com.kayaspring.kayaspring.entities.models.User.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<AppUser, Long>, JpaSpecificationExecutor<AppUser> {

    AppUser findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

}

