package com.cs.platform.framework.repository;

import com.cs.platform.framework.entity.Config;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ConfigDao extends PagingAndSortingRepository<Config, String>, JpaSpecificationExecutor<Config> {

  @Query("from Config where delFlag='0' and ckey=?1 and tenant.tenantId=?2")
  Config findOne(String ckey, String tenantId);
}
