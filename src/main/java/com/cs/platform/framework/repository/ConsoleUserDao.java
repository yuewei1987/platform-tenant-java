package com.cs.platform.framework.repository;

import com.cs.platform.framework.entity.ConsoleUser;
import com.cs.platform.framework.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface ConsoleUserDao extends JpaRepository<ConsoleUser, String>, JpaSpecificationExecutor<ConsoleUser> {

    @Query("from ConsoleUser where account=?1")
    ConsoleUser findByAccount(String account);
}
