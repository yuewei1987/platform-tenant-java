package com.cs.platform.app.repository;

import com.cs.platform.app.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductDao extends JpaRepository<Product, String>, JpaSpecificationExecutor<Product> {

    @Query("from Product where delFlag='0'")
    List<Product> findAll();

    @Query("from Product where delFlag='0' and state='0' and tenant.tenantId=?1 order by updateDate desc")
    List<Product> findAllState(String tenantId);
}
