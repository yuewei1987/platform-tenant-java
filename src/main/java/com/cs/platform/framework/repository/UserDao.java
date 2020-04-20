package com.cs.platform.framework.repository;


import com.cs.platform.framework.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;


public interface UserDao extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {

    @Query("from User where delFlag='0'")
    List<User> findAllUser();

    @Query("from User where (account=?1 or phone=?1) and tenant.tenantId=?2 and delFlag='0'")
    User findUser(String account, String tenantId);

    @Query("from User where (account=?1 or phone=?1) and tenant.tenantId=?3 and delFlag='0' and userId <> ?2")
    User findUser(String account, String userId, String tenantId);

    @Modifying
    @Query("update User set password=?1,updateDate=?2 where userId = ?3")
    int updatePasswordById(String newPassword, Date date, String userId);
}
