package com.kayaspring.kayaspring.entities.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "language")
public class Language extends BaseClass {

    @NotBlank(message = "Name is mandatory")
    public String name;

    @Override
    public String toString() {
        return "language";
    }
}
