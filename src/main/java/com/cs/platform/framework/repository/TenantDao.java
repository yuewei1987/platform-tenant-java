package com.cs.platform.framework.repository;

import com.cs.platform.framework.entity.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface TenantDao extends JpaRepository<Tenant, String>, JpaSpecificationExecutor<Tenant> {

    @Query("from Tenant where domainName=?1 and delFlag='0'")
    Tenant findTenantByDomainName(String domainName);
}
