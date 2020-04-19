package com.cs.platform.framework.repository;

import com.cs.platform.framework.entity.ConsoleUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ConsoleUserDao extends JpaRepository<ConsoleUser, String>, JpaSpecificationExecutor<ConsoleUser> {
}
