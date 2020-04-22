package com.cs.platform.framework.repository;

import com.cs.platform.framework.entity.Menu;
import com.cs.platform.framework.entity.UserGroupMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author chensong
 * @description
 * @date 2019/9/10
 */
public interface UserGroupMenuDao extends JpaRepository<UserGroupMenu, String>, JpaSpecificationExecutor<UserGroupMenu> {

    @Modifying
    @Query("DELETE from UserGroupMenu  where userGroup.id= ?1")
    void deleteUserGroupMenuByGroupId(String groupId);

    @Query("from UserGroupMenu  where userGroup.id= ?1 and delFlag='0'")
    List<UserGroupMenu> getMenuByGroupId(String groupId);
}
