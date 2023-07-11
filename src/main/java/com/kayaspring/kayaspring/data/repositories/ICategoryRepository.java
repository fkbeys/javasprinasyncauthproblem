package com.kayaspring.kayaspring.data.repositories;

import com.kayaspring.kayaspring.entities.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, Long>, JpaSpecificationExecutor<Category> {


}

