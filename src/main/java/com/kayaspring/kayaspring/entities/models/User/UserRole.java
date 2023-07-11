package com.kayaspring.kayaspring.entities.models.User;

import com.kayaspring.kayaspring.entities.enums.UserRoleEnums;
import jakarta.persistence.*;

@Entity
@Table(name = "UserRoles")
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private UserRoleEnums name;

    public UserRole() {
    }

    public UserRole(UserRoleEnums name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserRoleEnums getName() {
        return name;
    }

    public void setName(UserRoleEnums name) {
        this.name = name;
    }
}