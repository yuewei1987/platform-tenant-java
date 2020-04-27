package com.cs.platform.framework.repository;

import com.cs.platform.framework.entity.ConsoleConfig;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ConsoleConfigDao extends PagingAndSortingRepository<ConsoleConfig, String>, JpaSpecificationExecutor<ConsoleConfig> {

  @Query("from ConsoleConfig where ckey=?1")
  ConsoleConfig findOne(String ckey);
}
