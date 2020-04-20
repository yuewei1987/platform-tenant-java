package com.cs.platform.framework.core;

import com.cs.platform.framework.entity.Menu;

import java.io.Serializable;
import java.util.List;

/**
 * FIXME 类注释信息(此标记自动生成,注释填写完成后请删除)
 */
public class UserProfile implements Serializable {

    /**
     * FIXME
     */
    private static final long serialVersionUID = -2761248791769538367L;

    /**
     * 用户ID,内部唯一标识
     */
    private String userId;

    private String type;

    /**
     * 用户名
     */
    private String userName;

    private List<Menu> menus;

    private String avatarUrl;

    /**
     * 用户登录帐号
     */
    private String loginAccount;

    private String jsessionid;

    private String tenantId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLoginAccount() {
        return loginAccount;
    }

    public void setLoginAccount(String loginAccount) {
        this.loginAccount = loginAccount;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    public String getJsessionid() {
        return jsessionid;
    }

    public void setJsessionid(String jsessionid) {
        this.jsessionid = jsessionid;
    }
}
