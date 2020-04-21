package com.cs.platform.framework.service;

import com.cs.platform.framework.entity.User;
import com.cs.platform.framework.entity.UserGroup;
import com.cs.platform.framework.entity.UserUserGroup;
import com.cs.platform.framework.repository.UserDao;
import com.cs.platform.framework.repository.UserUserGroupDao;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
        entity = userDao.save(entity);
        List<UserUserGroup> glist = Lists.newArrayList();
        if (groups != null) {
            for (String id : groups) {
                UserUserGroup ug = new UserUserGroup();
                ug.setUser(entity);
                UserGroup userGroup = new UserGroup();
                userGroup.setId(id);
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

    public User findUser(String account, String tenantId) {
        return userDao.findUser(account, tenantId);
    }
}
