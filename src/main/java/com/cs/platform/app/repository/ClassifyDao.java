package com.cs.platform.app.repository;

import com.cs.platform.app.entity.Classify;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClassifyDao extends JpaRepository<Classify, String>, JpaSpecificationExecutor<Classify> {

    @Query("from Classify where delFlag='0' order by sortIndex desc")
    List<Classify> findAll();
}
