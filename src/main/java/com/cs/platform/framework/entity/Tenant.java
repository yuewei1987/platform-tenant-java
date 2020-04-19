package com.cs.platform.framework.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * 租户实体
 */
@Entity
@Table(name = "pub_tenant")
public class Tenant {

    /**
     * FIXME
     */
    private static final long serialVersionUID = -3704106617306574668L;
    /**
     * FIXME
     */
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(updatable = false)
    protected Date createDate;// 创建日期
    /**
     * FIXME
     */
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    //@JsonIgnore
    protected Date updateDate;// 更新日期
    /**
     * FIXME
     */
    @Length(min = 1, max = 1)
    @Column(nullable = false, columnDefinition = "varchar(1) default 0")
    @JsonIgnore
    protected String delFlag; // 删除标记（0：正常；1：删除；）

    /**
     * 租户id
     */
    @Id
    @GeneratedValue(generator = "UIDGenerator")
    @GenericGenerator(name = "UIDGenerator", strategy = "com.cs.platform.framework.persistence.AssignedUIDGenerator")
    @Column(length = 40)
    private String tenantId;
    /**
     * 租户代码
     */
    @NotNull(message = "租户代码不能为空")
    @Size(min = 1, max = 40, message = "租户代码长度必须在40个字符以内")
    private String code;
    /**
     * 租户企业名称
     */
    @NotNull(message = "租户企业名称不能为空")
    private String enterName;
    /**
     * 租户企业代码
     */
    @NotNull(message = "租户企业代码不能为空")
    @Size(min = 1, max = 40, message = "租户企业代码长度必须在40个字符以内")
    private String enterCode;
    /**
     * 租户域名
     */
    @Size(min = 1, max = 64, message = "租户域名长度必须在64个字符以内")
    @Column(unique = true)
    @NotNull(message = "租户域名不能为空")
    private String domainName;
    /**
     * 联系人名称
     */
    @NotNull(message = "租户联系人不能为空")
    @Size(min = 1, max = 20, message = "租户联系人长度必须在20个字符以内")
    private String contact;
    /**
     * 租户企业固定电话
     */
    private String tel;
    /**
     * 租户企业移动电话
     */
    private String mobile;
    /**
     * 租户企业传真
     */
    private String fax;
    /**
     * 租户企业电子邮箱
     */
    private String email;
    /**
     * 租户企业地址
     */
    private String address;
    /**
     * 背景头像id
     */
    @Column(length = 40)
    private String avatarId;

    /**
     * 构造函数
     */
    public Tenant() {
        super();
        this.delFlag = "0";
    }

    /**
     * 构造函数
     *
     * @param tenantId id
     */
    public Tenant(String tenantId) {
        this.tenantId = tenantId;
    }

    /**
     * 新增之前
     */
    @PrePersist
    public void prePersist() {
        this.updateDate = new Date();
        this.createDate = this.updateDate;
    }

    /**
     * 修改之前
     */
    @PreUpdate
    public void preUpdate() {
        this.updateDate = new Date();
    }

    /**
     * @return tenantId
     */
    public String getTenantId() {
        return tenantId;
    }

    /**
     * @param tenantId 要设置的 tenantId
     */
    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    /**
     * @return code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code 要设置的 code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return enterName
     */
    public String getEnterName() {
        return enterName;
    }

    /**
     * @param enterName 要设置的 enterName
     */
    public void setEnterName(String enterName) {
        this.enterName = enterName;
    }

    /**
     * @return enterCode
     */
    public String getEnterCode() {
        return enterCode;
    }

    /**
     * @param enterCode 要设置的 enterCode
     */
    public void setEnterCode(String enterCode) {
        this.enterCode = enterCode;
    }

    /**
     * @return domainName
     */
    public String getDomainName() {
        return domainName;
    }

    /**
     * @param domainName 要设置的 domainName
     */
    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    /**
     * @return contact
     */
    public String getContact() {
        return contact;
    }

    /**
     * @param contact 要设置的 contact
     */
    public void setContact(String contact) {
        this.contact = contact;
    }

    /**
     * @return tel
     */
    public String getTel() {
        return tel;
    }

    /**
     * @param tel 要设置的 tel
     */
    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * @return mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * @param mobile 要设置的 mobile
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * @return fax
     */
    public String getFax() {
        return fax;
    }

    /**
     * @param fax 要设置的 fax
     */
    public void setFax(String fax) {
        this.fax = fax;
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
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address 要设置的 address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    public String getAvatarId() {
        return avatarId;
    }

    public void setAvatarId(String avatarId) {
        this.avatarId = avatarId;
    }

}
