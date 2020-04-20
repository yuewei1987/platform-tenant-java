
package com.cs.platform.framework.repository;

import com.cs.platform.framework.entity.LoginLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author chensong
 * @description
 * @date 2020/1/15
 */
public interface LoginLogDao extends JpaRepository<LoginLog, String>, JpaSpecificationExecutor<LoginLog> {


}