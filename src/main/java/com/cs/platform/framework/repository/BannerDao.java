package com.cs.platform.framework.repository;

import com.cs.platform.framework.entity.Banner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Banner DAO
 */
public interface BannerDao extends JpaRepository<Banner, String>, JpaSpecificationExecutor<Banner> {


}
