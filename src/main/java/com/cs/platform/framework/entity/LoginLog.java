package com.cs.platform.framework.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 用户实体
 */
@Entity
@Table(name = "pub_login_log", indexes = {
        @javax.persistence.Index(columnList = "userId", name = "index_pub_login_log_user_id")})
public class LoginLog extends BaseEntity {

    @Id
    @GeneratedValue(generator = "UIDGenerator")
    @GenericGenerator(name = "UIDGenerator", strategy = "com.cs.platform.framework.persistence.AssignedUIDGenerator")
    @Column(length = 40)
    private String id;

    @Column(length = 40)
    private String userId;

    private String ip;

    private String address;

    @Transient
    private String name;

    public LoginLog() {
    }

    public LoginLog(String userId, String ip, String address) {
        this.userId = userId;
        this.ip = ip;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}