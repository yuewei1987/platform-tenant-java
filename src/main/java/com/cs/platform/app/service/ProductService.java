package com.cs.platform.app.service;

import com.cs.platform.app.entity.Classify;
import com.cs.platform.app.entity.ClassifyProduct;
import com.cs.platform.app.entity.Product;
import com.cs.platform.app.repository.ClassifyProductDao;
import com.cs.platform.app.repository.ProductDao;
import com.cs.platform.framework.core.UserUtils;
import com.cs.platform.framework.entity.Tenant;
import com.cs.platform.framework.persistence.DynamicSpecifications;
import com.cs.platform.framework.persistence.SearchFilter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
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
import java.util.Set;

/**
 * @author
 * @description
 * @date 2019/6/14
 */
@Component
public class ProductService {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private ClassifyProductDao classifyProductDao;

    private static Map<String, Set<String>> PRODUCT_ALL_DATA = Maps.newHashMap();
    private static Map<String, Set<String>> PRODUCT_DATA = Maps.newHashMap();

    public int getProductCountByClassify(String id) {
        Set<String> set = PRODUCT_ALL_DATA.get(id);
        return set == null ? 0 : set.size();
    }

    /**
     * 创建缓存
     */
    public synchronized void reloadCache() {
        PRODUCT_DATA.clear();
        PRODUCT_ALL_DATA.clear();
        List<ClassifyProduct> list = classifyProductDao.findAll();
        if (list != null) {
            for (ClassifyProduct cp : list) {
                Set<String> clist = PRODUCT_ALL_DATA.get(cp.getClassify().getId());
                if (clist == null) {
                    clist = Sets.newHashSet();
                    PRODUCT_ALL_DATA.put(cp.getClassify().getId(), clist);
                }
                clist.add(cp.getProduct().getId());

                if (cp.getProduct().getState() == 0) {
                    Set<String> clist1 = PRODUCT_DATA.get(cp.getClassify().getId());
                    if (clist1 == null) {
                        clist1 = Sets.newHashSet();
                        PRODUCT_DATA.put(cp.getClassify().getId(), clist1);
                    }
                    clist1.add(cp.getProduct().getId());
                }
            }
        }
    }

    public List<Product> findAllState(String tenantId) {
        return productDao.findAllState(tenantId);
    }

    public Page<Product> findPage(int pageNumber, int pageSize, int state, String name) {
        PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize, new Sort(Sort.Direction.DESC, "createDate"));
        Map<String, SearchFilter> filters = Maps.newHashMap();
        filters.put("delFlag", new SearchFilter("delFlag", SearchFilter.Operator.EQ, "0"));
        filters.put("tenant.tenantId", new SearchFilter("tenant.tenantId", SearchFilter.Operator.EQ, UserUtils.getTenantId()));
        if (state != -1) {
            filters.put("state", new SearchFilter("state", SearchFilter.Operator.EQ, state));
        }
        if (StringUtils.isNotEmpty(name)) {
            filters.put("name", new SearchFilter("name", SearchFilter.Operator.LIKE, "%" + name + "%"));
        }
        Specification<Product> spec = DynamicSpecifications.bySearchFilter(filters.values(), Product.class);
        return productDao.findAll(spec, pageRequest);
    }

    @Transactional
    public void save(Product entity) {
        if (StringUtils.isNotEmpty(entity.getId())) {
            List<ClassifyProduct> cpList = classifyProductDao.getClassifyProductByProductId(entity.getId());
            if (cpList != null) {
                for (ClassifyProduct cpp : cpList) {
                    Set<String> clist = PRODUCT_ALL_DATA.get(cpp.getClassify().getId());
                    clist.remove(entity.getId());
                    Set<String> clist1 = PRODUCT_DATA.get(cpp.getClassify().getId());
                    clist1.remove(entity.getId());
                }
            }
            classifyProductDao.deleteClassifyProductByProductId(entity.getId());
        }
        String[] ids = entity.getClassifyProducts();
        entity.setTenant(new Tenant(UserUtils.getTenantId()));
        entity = productDao.save(entity);
        List<ClassifyProduct> list = Lists.newArrayList();
        for (String id : ids) {
            ClassifyProduct temp = new ClassifyProduct();
            temp.setProduct(entity);
            Classify classify = new Classify();
            classify.setId(id);
            temp.setClassify(classify);
            temp.setTenant(new Tenant(UserUtils.getTenantId()));
            list.add(temp);

            if (StringUtils.equals("0", entity.getDelFlag())) {
                Set<String> clist = PRODUCT_ALL_DATA.get(id);
                if (clist == null) {
                    clist = Sets.newHashSet();
                    PRODUCT_ALL_DATA.put(id, clist);
                }
                clist.add(entity.getId());

                if (entity.getState() == 0) {
                    Set<String> clist1 = PRODUCT_DATA.get(id);
                    if (clist1 == null) {
                        clist1 = Sets.newHashSet();
                        PRODUCT_DATA.put(id, clist1);
                    }
                    clist1.add(entity.getId());
                }
            }
        }
        classifyProductDao.saveAll(list);
    }

    @Transactional
    public void updateState(String id, String state) {
        Product product = productDao.findById(id).orElse(null);
        if (product != null) {
            product.setState(Integer.parseInt(state));
            productDao.save(product);

            List<ClassifyProduct> list = classifyProductDao.getClassifyProductByProductId(id);
            for (ClassifyProduct cp : list) {
                Set<String> clist1 = PRODUCT_DATA.get(cp.getClassify().getId());
                if (Integer.parseInt(state) == 0) {
                    clist1.add(id);
                } else {
                    //  下架
                    clist1.remove(id);
                }
            }
        }
    }

    public Product find(String id) {
        Product product = productDao.findById(id).orElse(null);
        if (product != null) {
            List<ClassifyProduct> list = classifyProductDao.getClassifyProductByProductId(id);
            String[] ids = new String[list.size()];
            String[] names = new String[list.size()];
            for (int i = 0; i < list.size(); i++) {
                ids[i] = list.get(i).getClassify().getId();
                names[i] = list.get(i).getClassify().getName();
            }
            product.setClassifyProducts(ids);
            product.setClassifyProductNames(names);

        }
        return product;
    }


    public Page<ClassifyProduct> findPage(String classifyId, int pageNumber, int pageSize) {
        PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize, new Sort(Sort.Direction.DESC, "product.createDate"));
        Map<String, SearchFilter> filters = Maps.newHashMap();
        filters.put("classifyId", new SearchFilter("classify.id", SearchFilter.Operator.EQ, classifyId));
        filters.put("delFlag", new SearchFilter("delFlag", SearchFilter.Operator.EQ, "0"));
        Specification<ClassifyProduct> spec = DynamicSpecifications.bySearchFilter(filters.values(), ClassifyProduct.class);
        return classifyProductDao.findAll(spec, pageRequest);
    }
}
