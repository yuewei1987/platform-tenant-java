package com.cs.platform.framework.service;

import com.cs.platform.framework.core.UserUtils;
import com.cs.platform.framework.entity.Banner;
import com.cs.platform.framework.entity.Tenant;
import com.cs.platform.framework.persistence.DynamicSpecifications;
import com.cs.platform.framework.persistence.SearchFilter;
import com.cs.platform.framework.repository.BannerDao;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author
 * @description
 * @date 2019/6/14
 */
@Component
public class BannerService {

    //    private static List<Banner> BANNER_DATA = Lists.newArrayList();
    private static Map<String, List<Banner>> BANNER_DATA = Maps.newHashMap();
    @Autowired
    private BannerDao bannerDao;

    /**
     * 创建缓存
     */
    public synchronized void reloadCache() {
        BANNER_DATA.clear();
        Sort sort = new Sort(Sort.Direction.DESC, "sortIndex");
        List<Banner> list = bannerDao.findAll(sort);
        if (list != null) {
            for (Banner banner : list) {
                if (StringUtils.equals(banner.getDelFlag(), "1")) {
                    continue;
                }
                List<Banner> temp = BANNER_DATA.get(banner.getTenant().getTenantId());
                if (temp == null) {
                    temp = Lists.newArrayList();
                    BANNER_DATA.put(banner.getTenant().getTenantId(), temp);
                }
                temp.add(banner);
            }
        }
    }

    public List<Banner> getAll(String tenantId) {
        return BANNER_DATA.get(tenantId);
    }

    public Page<Banner> findPage(int pageNumber, int pageSize) {
        PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize, new Sort(Sort.Direction.DESC, "sortIndex"));
        Map<String, SearchFilter> filters = Maps.newHashMap();
        filters.put("tenant.tenantId", new SearchFilter("tenant.tenantId", SearchFilter.Operator.EQ, UserUtils.getTenantId()));
        Specification<Banner> spec = DynamicSpecifications.bySearchFilter(filters.values(), Banner.class);
        return bannerDao.findAll(spec, pageRequest);
    }

    @Transactional
    public void save(Banner entity) {
        entity.setTenant(new Tenant(UserUtils.getTenantId()));
        bannerDao.save(entity);
        reloadCache();
    }


    @Transactional
    public void delete(String id) {
        bannerDao.deleteById(id);
        reloadCache();
    }

    public Banner find(String id) {
        return bannerDao.findById(id).orElse(null);
    }
}
