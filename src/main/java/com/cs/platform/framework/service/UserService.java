package com.cs.platform.framework.service;

import com.cs.platform.framework.core.UserUtils;
import com.cs.platform.framework.entity.Tenant;
import com.cs.platform.framework.entity.User;
import com.cs.platform.framework.entity.UserGroup;
import com.cs.platform.framework.entity.UserUserGroup;
import com.cs.platform.framework.repository.UserDao;
import com.cs.platform.framework.repository.UserUserGroupDao;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author
 * @description
 * @date 2019/6/14
 */
@Component
public class UserService {
  /**
   * logger
   */
  protected Logger logger = LoggerFactory.getLogger(getClass());

  public static final Map<String, String> CACHE = Maps.newTreeMap();

  @Autowired
  private UserDao userDao;

  @Autowired
  private UserUserGroupDao userUserGroupDao;

  public static String getUserName(String userId) {
    return CACHE.get(userId);
  }

  public void reloadCache() {
    CACHE.clear();
    List<User> list = userDao.findAllUser();
    for (User user : list) {
      CACHE.put(user.getUserId(), user.getName());
    }
  }

  /**
   * 查询用户
   *
   * @param type       用户类型
   * @param serchkey   查询条件
   * @param pageNumber xx
   * @param pageSize   xx
   * @return xx
   */
  @Transactional(readOnly = true)
  public Page<User> findPageUser(String type, String serchkey, int pageNumber, int pageSize) {
    PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize, new Sort(Sort.Direction.DESC, "createDate"));
    Specification<User> spec = buildSpecification(serchkey, type);
    return userDao.findAll(spec, pageRequest);
  }

  /**
   * 构建查询条件
   *
   * @param keyword keyword
   * @param type    type
   * @return spec
   */
  private Specification<User> buildSpecification(final String keyword, final String type) {
    Specification<User> spec = new Specification<User>() {

      @Override
      public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> predicates = Lists.newArrayList();
        if (StringUtils.isNotEmpty(keyword)) {// 根据name like
          String queryKey = convertMySqlString(keyword);
          Path expression = root.get("name");
          Predicate predicateName = builder.like(expression, "%" + queryKey + "%", '/');
          expression = root.get("account");
          Predicate predicateAccount = builder.like(expression, "%" + queryKey + "%", '/');
          predicates.add(builder.or(predicateName, predicateAccount));
        }
        predicates.add(builder.equal(root.get("delFlag"), "0"));
        predicates.add(builder.equal(root.get("tenant").get("tenantId"), UserUtils.getTenantId()));
        if (StringUtils.isNotEmpty(type)) {
          predicates.add(builder.equal(root.get("type"), type));
        } else {// 查询内部人员
          predicates.add(builder.notEqual(root.get("type"), User.TYPE_INNER_ADMIN));
        }
        // 将所有条件用 and 联合起来
        if (predicates.size() > 0) {
          return builder.and(predicates.toArray(new Predicate[predicates.size()]));
        }
        return builder.conjunction();
      }
    };
    return spec;
  }

  /**
   * mysql模糊查询时，如果查询关键字本身包含_和%，需要转义
   *
   * @param queryKey 查询关键字
   * @return 转义字符
   */
  private String convertMySqlString(String queryKey) {
    String[] encodeArr = {"_", "%"};
    String rst = queryKey;
    for (String encode : encodeArr) {
      rst = StringUtils.replace(rst, encode, "/" + encode);
    }
    return rst;
  }


  /**
   * 修改密码
   *
   * @param password password
   * @param userId   userId
   */
  @Transactional
  public void updatePasswordById(String password, String userId) {
    userDao.updatePasswordById(password, new Date(), userId);
  }


  /**
   * 保存内部普通用户
   *
   * @param entity
   */
  @Transactional
  public void save(User entity) {
    String[] groups = entity.getGroups();
    entity.setTenant(new Tenant(UserUtils.getTenantId()));
    entity = userDao.save(entity);
    List<UserUserGroup> glist = Lists.newArrayList();
    if (groups != null) {
      for (String id : groups) {
        UserUserGroup ug = new UserUserGroup();
        ug.setUser(entity);
        UserGroup userGroup = new UserGroup();
        userGroup.setId(id);
        ug.setTenant(new Tenant(UserUtils.getTenantId()));
        ug.setUserGroup(userGroup);
        glist.add(ug);
      }
    }
    userUserGroupDao.deleteUserGroupByuserId(entity.getUserId());
    userUserGroupDao.saveAll(glist);
    CACHE.put(entity.getUserId(), entity.getName());
  }

  public User findUserByUserId(String userId) {
    User user = userDao.findById(userId).orElse(null);
    List<UserUserGroup> list = userUserGroupDao.findUserUserGroupByUserId(userId);
    if (list != null) {
      String[] groups = new String[list.size()];
      int index = 0;
      for (UserUserGroup ug : list) {
        groups[index++] = ug.getUserGroup().getId();
      }
      user.setGroups(groups);
    }
    return user;
  }

  public User findUser(String account, String userId, String tenantId) {
    return userDao.findUser(account, userId, tenantId);
  }

  public User findUser(String account, String tenantId) {
    return userDao.findUser(account, tenantId);
  }
}
