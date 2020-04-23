package com.cs.platform.app.repository;

import com.cs.platform.app.entity.ClassifyProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClassifyProductDao extends JpaRepository<ClassifyProduct, String>, JpaSpecificationExecutor<ClassifyProduct> {

    @Modifying
    @Query("update ClassifyProduct set delFlag='1' where product.id= ?1")
    void deleteClassifyProductByProductId(String pClassifyProduct);

    @Query("from ClassifyProduct where product.id= ?1 and delFlag='0'")
    List<ClassifyProduct> getClassifyProductByProductId(String pClassifyProduct);

    @Query("from ClassifyProduct where delFlag='0'")
    List<ClassifyProduct> findAll();
}
