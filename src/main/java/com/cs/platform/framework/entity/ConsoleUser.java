package com.cs.platform.framework.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 运维人员用户实体
 */
@Entity
@Table(name = "pub_console_user")
public class ConsoleUser implements Serializable {

    /**
     * FIXME
     */
    private static final long serialVersionUID = 4065015704814193325L;

    /**
     * 用户id
     */
    @Id
    @GeneratedValue(generator = "UIDGenerator")
    @GenericGenerator(name = "UIDGenerator", strategy = "com.cs.platform.framework.persistence.AssignedUIDGenerator")
    @Column(length = 40)
    private String id;

    /**
     * 用户姓名
     */
    @NotNull(message = "用户姓名不能为空")
    @NotBlank(message = "用户姓名不能为空")
    @Column(length = 40)
    private String name;

    /**
     * 登录名
     */
    @NotNull(message = "登录名不能为空")
    @NotBlank(message = "登录名不能为空")
    @Column(length = 60)
    private String account;

    /**
     * 密码
     */
    @NotNull(message = "密码不能为空")
    @Column(length = 80)
    @JsonIgnore
    private String password;

    /**
     * 头像id
     */
    private String avatarId;

    /**
     * 新密码
     */
    @Transient
    @JsonIgnore
    private String newPassword;
    /**
     * 邮箱
     */
    @Column(length = 50)
    private String email;
    /**
     * 电话
     */
    @Column(length = 15)
    private String phone;
    /**
     * 性别 ： 0为男 1为女
     */
    @Column(length = 1)
    @NotNull(message = "性别不能为空")
    @NotBlank(message = "性别不能为空")
    private String gender;
    /**
     * 排序
     */
    @JsonIgnore
    private int sort;
    /**
     * 最后登陆日期
     */
    @JsonIgnore
    private Date lastLoginDate;

    /**
     * 构造函数
     */
    public ConsoleUser() {

    }

    /**
     * 构造函数
     *
     * @param id userId
     */
    public ConsoleUser(String id) {
        this.id = id;
    }

    /**
     * @return newPassword
     */
    public String getNewPassword() {
        return newPassword;
    }

    /**
     * @param newPassword 要设置的 newPassword
     */
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    /**
     * @return id
     */
    public String getId() {
        return id;
    }


    /**
     * @param id 要设置的 id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }


    /**
     * @param name 要设置的 name
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * @return account
     */
    public String getAccount() {
        return account;
    }


    /**
     * @param account 要设置的 account
     */
    public void setAccount(String account) {
        this.account = account;
    }


    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }


    /**
     * @param password 要设置的 password
     */
    public void setPassword(String password) {
        this.password = password;
    }


    /**
     * @return avatarId
     */
    public String getAvatarId() {
        return avatarId;
    }

    /**
     * @param avatarId 要设置的 avatarId
     */
    public void setAvatarId(String avatarId) {
        this.avatarId = avatarId;
    }

    /**
     * @return email
     */
    public String getEmail() {
        return email;
    }


    /**
     * @param email 要设置的 email
     */
    public void setEmail(String email) {
        this.email = email;
    }


    /**
     * @return phone
     */
    public String getPhone() {
        return phone;
    }


    /**
     * @param phone 要设置的 phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }


    /**
     * @return gender
     */
    public String getGender() {
        return gender;
    }


    /**
     * @param gender 要设置的 gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }


    /**
     * @return sort
     */
    public int getSort() {
        return sort;
    }


    /**
     * @param sort 要设置的 sort
     */
    public void setSort(int sort) {
        this.sort = sort;
    }


    /**
     * @return lastLoginDate
     */
    public Date getLastLoginDate() {
        return lastLoginDate;
    }


    /**
     * @param lastLoginDate 要设置的 lastLoginDate
     */
    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }


}
