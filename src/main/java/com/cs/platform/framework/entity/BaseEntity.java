package com.cs.platform.framework.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;


@MappedSuperclass
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public abstract class BaseEntity implements Serializable {

    /**
     * FIXME
     */
    private static final long serialVersionUID = 1L;

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
     * FIXME
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_id")
    @NotFound(action = NotFoundAction.IGNORE)
    @JsonIgnore
    @NotNull(message = "租户id不能为空")
    protected Tenant tenant;

    /**
     * 构造函数
     */
    public BaseEntity() {
        super();
        this.delFlag = "0";
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }
}
