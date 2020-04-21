package com.cs.platform.framework.core;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.UnknownSessionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 用户工具类
 */
public class UserUtils {

    /**
     * 日志类
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(UserUtils.class);


    /**
     * 获取用户的profile
     *
     * @return UserProfile
     */
    public static UserProfile getUserProfile() {
        UserProfile prof = null;
        try {
            // 从shiro获取userprofile
            prof = (UserProfile) SecurityUtils.getSubject().getPrincipal();
        } catch (UnknownSessionException e) {
            LOGGER.error("无法找到当前登录会话", e);
            prof = null;
        }
        if (prof == null) {
            prof = new UserProfile();
        }
        return prof;
    }

    /**
     * 获取登录帐号
     *
     * @return loginAccount
     */
    public static String getLoginAccount() {
        UserProfile prof = getUserProfile();
        return prof.getLoginAccount();
    }


    public static String getType() {
        UserProfile prof = getUserProfile();
        return prof.getType();
    }

    /**
     * 获取用户Id
     *
     * @return UserId
     */
    public static String getUserId() {
        UserProfile prof = getUserProfile();
        return prof.getUserId();
    }

    public static String getTenantId() {
        UserProfile prof = getUserProfile();
        return prof.getTenantId();
    }

    /**
     * 获取用户Name
     *
     * @return UserId
     */
    public static String getUserName() {
        UserProfile prof = getUserProfile();
        return prof.getUserName();
    }
}
