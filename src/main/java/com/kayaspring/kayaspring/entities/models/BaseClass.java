package com.kayaspring.kayaspring.entities.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

@MappedSuperclass
public class BaseClass {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "id")
    public Long id;

    @NotNull
    @Column(name = "createDate")
    public Date getCreateDate() {
        return this.createDate;
    }
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    private Date createDate;

    @NotNull
    @Column(name = "createUser")
    public Long getCreateUser() {
        return this.createUser;
    }
    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }
    private Long createUser;

}
