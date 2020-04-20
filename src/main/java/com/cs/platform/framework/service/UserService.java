package com.cs.platform.framework.service;

import com.cs.platform.framework.entity.User;
import com.cs.platform.framework.repository.UserDao;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    public User findUser(String account, String tenantId) {
        return userDao.findUser(account, tenantId);
    }
}
