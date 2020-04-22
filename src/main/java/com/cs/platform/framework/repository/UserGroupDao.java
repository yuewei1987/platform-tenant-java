package com.cs.platform.framework.repository;

import com.cs.platform.framework.entity.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author chensong
 * @description
 * @date 2019/9/10
 */
public interface UserGroupDao extends JpaRepository<UserGroup, String>, JpaSpecificationExecutor<UserGroup> {
    @Query("from UserGroup where delFlag = '0' and tenant.tenantId=?1 order by name")
    List<UserGroup> findAllUserGroup(String tenantId);
}
