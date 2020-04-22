package com.cs.platform.framework.service;

import com.cs.platform.framework.core.UserUtils;
import com.cs.platform.framework.entity.Menu;
import com.cs.platform.framework.entity.Tenant;
import com.cs.platform.framework.entity.UserGroup;
import com.cs.platform.framework.entity.UserGroupMenu;
import com.cs.platform.framework.persistence.DynamicSpecifications;
import com.cs.platform.framework.persistence.SearchFilter;
import com.cs.platform.framework.repository.UserGroupDao;
import com.cs.platform.framework.repository.UserGroupMenuDao;
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

@Component
public class UserGroupService {
  @Autowired
  private UserGroupDao userGroupDao;
  @Autowired
  private UserGroupMenuDao userGroupMenuDao;

  public Page<UserGroup> findPage(String serchkey, int pageNumber, int pageSize) {
    PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize, new Sort(Sort.Direction.DESC, "createDate"));
    Map<String, SearchFilter> filters = Maps.newHashMap();
    filters.put("name", new SearchFilter("name", SearchFilter.Operator.LIKE, "%" + serchkey + "%"));
    filters.put("delFlag", new SearchFilter("delFlag", SearchFilter.Operator.EQ, "0"));
    filters.put("tenant.tenantId", new SearchFilter("tenant.tenantId", SearchFilter.Operator.EQ, UserUtils.getTenantId()));
    Specification<UserGroup> spec = DynamicSpecifications.bySearchFilter(filters.values(), UserGroup.class);
    return userGroupDao.findAll(spec, pageRequest);
  }

  @Transactional
  public void save(UserGroup entity) {
    String[] menus = entity.getMenus();
    String[] halfHenus = entity.getHalfHenus();
    entity.setTenant(new Tenant(UserUtils.getTenantId()));
    entity = userGroupDao.save(entity);
    userGroupMenuDao.deleteUserGroupMenuByGroupId(entity.getId());
    List<UserGroupMenu> list = Lists.newArrayList();
    if (menus != null) {
      for (String id : menus) {
        if (StringUtils.isEmpty(id)) {
          continue;
        }
        UserGroupMenu um = new UserGroupMenu();
        um.setUserGroup(entity);
        um.setMenu(new Menu(id));
        um.setTenant(new Tenant(UserUtils.getTenantId()));
        um.setHalf(true);
        list.add(um);
      }
    }
    if (halfHenus != null) {
      for (String id : halfHenus) {
        if (StringUtils.isEmpty(id)) {
          continue;
        }
        UserGroupMenu um = new UserGroupMenu();
        um.setUserGroup(entity);
        um.setMenu(new Menu(id));
        um.setHalf(false);
        um.setTenant(new Tenant(UserUtils.getTenantId()));
        list.add(um);
      }
    }
    userGroupMenuDao.saveAll(list);
  }

  public UserGroup find(String id) {
    return userGroupDao.findById(id).orElse(null);
  }

  public List<Menu> getMenuByGroupId(String groupId) {
    List<Menu> result = Lists.newArrayList();
    List<UserGroupMenu> list = userGroupMenuDao.getMenuByGroupId(groupId);
    if (list != null) {
      for (UserGroupMenu gm : list) {
        if (gm.isHalf()) {
          result.add(gm.getMenu());
        }
      }
    }
    return result;
  }

  public List<UserGroup> getAllGroup() {
    return userGroupDao.findAllUserGroup(UserUtils.getTenantId());
  }
}
