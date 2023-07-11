package com.kayaspring.kayaspring.data.repositories;

import com.kayaspring.kayaspring.entities.models.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ILanguageRepository extends JpaRepository<Language, Long>, JpaSpecificationExecutor<Language> {


}

