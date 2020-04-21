package com.cs.platform.framework.service;


import com.cs.platform.framework.core.UserUtils;
import com.cs.platform.framework.entity.LoginLog;
import com.cs.platform.framework.entity.User;
import com.cs.platform.framework.persistence.DynamicSpecifications;
import com.cs.platform.framework.persistence.SearchFilter;
import com.cs.platform.framework.repository.LoginLogDao;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class LoginLogService {
    @Autowired
    private LoginLogDao loginLogDao;

    public void save(LoginLog log) {
        loginLogDao.save(log);
    }

    public Page<LoginLog> findPage(int pageNumber, int pageSize) {
        PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize, new Sort(Sort.Direction.DESC, "createDate"));
        Map<String, SearchFilter> filters = Maps.newHashMap();
        if (!StringUtils.equals(UserUtils.getType(), User.TYPE_INNER_ADMIN)) {
            filters.put("userId", new SearchFilter("userId", SearchFilter.Operator.EQ, UserUtils.getUserId()));
        }
        filters.put("delFlag", new SearchFilter("delFlag", SearchFilter.Operator.EQ, "0"));
        filters.put("tenant.tenantId", new SearchFilter("tenant.tenantId", SearchFilter.Operator.EQ, UserUtils.getTenantId()));
        Specification<LoginLog> spec = DynamicSpecifications.bySearchFilter(filters.values(), LoginLog.class);
        Page<LoginLog> page = loginLogDao.findAll(spec, pageRequest);
        List<LoginLog> list = page.getContent();
        if (!list.isEmpty() && StringUtils.equals(UserUtils.getType(), User.TYPE_INNER_ADMIN)) {
            for (LoginLog log : list) {
                log.setName(UserService.getUserName(log.getUserId()));
            }
        }
        return page;
    }
}
