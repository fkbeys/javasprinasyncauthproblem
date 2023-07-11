package com.kayaspring.kayaspring.data.repositories;

import com.kayaspring.kayaspring.entities.models.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface IWordRepository extends JpaRepository<Word, Long>, JpaSpecificationExecutor<Word> {


}

