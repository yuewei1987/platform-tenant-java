package com.cs.platform.framework.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

/**
 * 用户实体
 */
@Entity
@Table(name = "pub_user", indexes = {@Index(unique = false, columnList = "account", name = "index_pub_user_account"),
        @Index(unique = false, columnList = "phone", name = "index_pub_user_phone")})
public class User extends BaseEntity {
    /**
     * 内部管理员类型
     */
    public final static String TYPE_INNER_ADMIN = "0";

    /**
     * 内部普通用户类型
     */
    public final static String TYPE_INNER_NORMAL = "1";

    /**
     * 外部用户类型
     */
    public final static String TYPE_EXT = "2";
    /**
     * 用户id
     */
    @Id
    @GeneratedValue(generator = "UIDGenerator")
    @GenericGenerator(name = "UIDGenerator", strategy = "com.cs.platform.framework.persistence.AssignedUIDGenerator")
    @Column(length = 40)
    private String userId;

    /**
     * 用户姓名
     */
    @Column(length = 40)
    private String name;

    /**
     * 用户昵称
     */
    @Column(length = 20)
    private String nick;

    /**
     * 账号
     */
    @Column(length = 50)
    private String account;

    /**
     * 密码 ,密码已经设置为不能update，因为到数据的时候可能会被覆盖，要修改密码可以使用其他脚本方法
     */
    @Column(length = 80, updatable = false)
    @JsonIgnore
    private String password;

    @Transient
    private String password1;

    /**
     * 用户类型。0为内部管理员，1为内部普通用户，2为外部人员
     */
    @Length(min = 1, max = 1)
    @Column(updatable = false)
    private String type;

    /**
     * 电话
     */
    @Column(length = 15)
    private String phone;
    /**
     * 头像
     */
    private String avatarUrl;

    /**
     * 性别 ： 0为男 1为女
     */
    @Length(min = 1, max = 1)
    private String gender;

    /**
     * 部门ids
     */
    @Transient
    private String orgIds; // 所属组织id集合;这个不属于持久化字段，但保存时候需要
    /**
     * 部门名称
     */
    @Transient
    private String orgNames; // 所属组织name集合;这个不属于持久化字段，但保存时候需要
    @Transient
    private String[] groups;

    /**
     * 构造函数
     */
    public User() {
    }

    /**
     * 构造函数
     *
     * @param userId id
     */
    public User(String userId) {
        this.userId = userId;
    }

    public static String getTypeInnerAdmin() {
        return TYPE_INNER_ADMIN;
    }

    public static String getTypeInnerNormal() {
        return TYPE_INNER_NORMAL;
    }

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String[] getGroups() {
        return groups;
    }

    public void setGroups(String[] groups) {
        this.groups = groups;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOrgIds() {
        return orgIds;
    }

    public void setOrgIds(String orgIds) {
        this.orgIds = orgIds;
    }

    public String getOrgNames() {
        return orgNames;
    }

    public void setOrgNames(String orgNames) {
        this.orgNames = orgNames;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
