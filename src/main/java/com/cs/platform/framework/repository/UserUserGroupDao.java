package com.cs.platform.framework.repository;

import com.cs.platform.framework.entity.UserUserGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserUserGroupDao extends JpaRepository<UserUserGroup, String>, JpaSpecificationExecutor<UserUserGroup> {
    @Modifying
    @Query("DELETE from UserUserGroup  where user.userId= ?1")
    void deleteUserGroupByuserId(String userId);

    @Query("from UserUserGroup  where user.userId= ?1 and delFlag = '0'")
    List<UserUserGroup> findUserUserGroupByUserId(String userId);
}
