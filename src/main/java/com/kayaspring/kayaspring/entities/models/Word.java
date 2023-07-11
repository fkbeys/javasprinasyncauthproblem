package com.kayaspring.kayaspring.entities.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;


@Entity
@Table(name = "word")
public class Word extends BaseClass {

    public int languageId;
    public String catogoryIds; // a word may be in multiple categories
    public String name;

    @Override
    public String toString() {
        return "word";
    }
}
