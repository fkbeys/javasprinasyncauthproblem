package com.kayaspring.kayaspring.entities.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

@Entity
@Table(name = "category")
public class Category extends BaseClass {


    public Category() {

    }

    public Category(String name, Integer headerId, Integer subId, long createUserId) {
        this.name = name;
        this.headerId = headerId;
        this.subId = subId;
        this.id = this.id == null ? 0 : this.id;
        this.setCreateUser(createUserId);
        this.setCreateDate(new Date());
    }

    @NotNull
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    @NotNull
    private Integer headerId;
    @Column(nullable = false)
    @NotNull
    private Integer subId;
    private String imagePath;




    public @NotNull String getName() {
        return name;
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }

    public @NotNull Integer getHeaderId() {
        return headerId;
    }

    public void setHeaderId(Integer headerId) {
        this.headerId = headerId;
    }

    public Integer getSubId() {
        return subId;
    }

    public void setSubId(Integer subId) {
        this.subId = subId;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return "category";
    }
}
