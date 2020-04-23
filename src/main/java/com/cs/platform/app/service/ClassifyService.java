package com.cs.platform.app.service;

import com.cs.platform.app.entity.Classify;
import com.cs.platform.app.repository.ClassifyDao;
import com.cs.platform.framework.core.UserUtils;
import com.cs.platform.framework.entity.Banner;
import com.cs.platform.framework.entity.Tenant;
import com.cs.platform.framework.persistence.DynamicSpecifications;
import com.cs.platform.framework.persistence.SearchFilter;
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
public class ClassifyService {

    private static Map<String, List<Classify>> CLASSIFY_DATA = Maps.newHashMap();
    @Autowired
    private ClassifyDao classifyDao;
    @Autowired
    private ProductService productService;

    /**
     * 创建缓存
     */
    public synchronized void reloadCache() {
        CLASSIFY_DATA.clear();
        List<Classify> list = classifyDao.findAll();
        if (list != null) {
            for (Classify classify : list) {
                if (StringUtils.equals(classify.getDelFlag(), "1")) {
                    continue;
                }
                List<Classify> temp = CLASSIFY_DATA.get(classify.getTenant().getTenantId());
                if (temp == null) {
                    temp = Lists.newArrayList();
                    CLASSIFY_DATA.put(classify.getTenant().getTenantId(), temp);
                }
                temp.add(classify);
            }
        }
    }

    public List<Classify> getAll(String tenantId) {
        return CLASSIFY_DATA.get(tenantId);
    }

    public Page<Classify> findPage(int pageNumber, int pageSize) {
        PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize, new Sort(Sort.Direction.DESC, "sortIndex"));
        Map<String, SearchFilter> filters = Maps.newHashMap();
        filters.put("delFlag", new SearchFilter("delFlag", SearchFilter.Operator.EQ, "0"));
        filters.put("tenant.tenantId", new SearchFilter("tenant.tenantId", SearchFilter.Operator.EQ, UserUtils.getTenantId()));
        Specification<Classify> spec = DynamicSpecifications.bySearchFilter(filters.values(), Classify.class);
        Page<Classify> page = classifyDao.findAll(spec, pageRequest);
        List<Classify> list = page.getContent();
        for (Classify ci : list) {
            ci.setpCount(productService.getProductCountByClassify(ci.getId()));
        }

        return page;
    }

    @Transactional
    public void save(Classify entity) {
        entity.setTenant(new Tenant(UserUtils.getTenantId()));
        classifyDao.save(entity);
        reloadCache();
    }


    @Transactional
    public void delete(String id) {
        classifyDao.deleteById(id);
        reloadCache();
    }

    public Classify find(String id) {
        return classifyDao.findById(id).orElse(null);
    }
}
