package com.cs.platform.framework.repository;

import com.cs.platform.framework.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author
 * @description
 * @date 2019/6/29
 */
public interface MenuDao extends JpaRepository<Menu, String>, JpaSpecificationExecutor<Menu> {
    @Query(value = "select distinct t1.* from pub_menu t1,pub_user_user_group t2,pub_user_group_menu t3 where t2.user_id=?1 and t3.menu_id=t1.id and t2.group_id=t3.gorup_id", nativeQuery = true)
    List<Menu> findMenuByUserId(String userId);

    @Query("from Menu where delFlag='0' and tenant.tenantId=?1 order by sortIndex")
    List<Menu> findAdminMenu(String tenantId);
}
