package com.cs.platform.framework.realm;

import org.apache.shiro.authc.UsernamePasswordToken;

public class RestUsernamePasswordToken extends UsernamePasswordToken {
    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 企业号
     */
    private String tenantName;


    /**
     * 构造函数（默认开启验证码）
     */
    public RestUsernamePasswordToken() {
        super();
    }

    /**
     * 构造函数
     *
     * @param username   username
     * @param password   password
     * @param rememberMe rememberMe
     * @param host       host
     * @param tenantId   tenantId
     */
    public RestUsernamePasswordToken(String username, String password, boolean rememberMe, String host, String tenantId) {
        super(username, password, rememberMe, host);
        this.tenantId = tenantId;
    }

    public RestUsernamePasswordToken(String username, String password, String host, String tenantId) {
        super(username, password, host);
        this.tenantId = tenantId;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }
}
