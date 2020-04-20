package com.cs.platform.framework.service;

import com.cs.platform.framework.entity.Tenant;
import com.cs.platform.framework.repository.TenantDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TenantService {
    @Autowired
    private TenantDao tenantDao;
    /**
     * 根绝租户域名查询租户
     *
     * @param domainName domainName
     * @return Tenant
     */
    public Tenant findTenantByDomainName(String domainName) {
        return tenantDao.findTenantByDomainName(domainName);
    }
}
