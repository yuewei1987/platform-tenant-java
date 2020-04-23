package com.cs.platform.app.entity;

import com.cs.platform.framework.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "pub_classify")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Classify extends BaseEntity {
    @Id
    @GeneratedValue(generator = "UIDGenerator")
    @GenericGenerator(name = "UIDGenerator", strategy = "com.cs.platform.framework.persistence.AssignedUIDGenerator")
    @Column(length = 40)
    private String id;

    @Column(length = 10)
    private String name;

    @Column(length = 400)
    private String url;

    @Column(columnDefinition = "int(2) NOT NULL default '1'")
    private int sortIndex;
    /**
     * 商品个数
     */
    @Transient
    private int pCount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getSortIndex() {
        return sortIndex;
    }

    public int getpCount() {
        return pCount;
    }

    public void setpCount(int pCount) {
        this.pCount = pCount;
    }

    public void setSortIndex(int sortIndex) {
        this.sortIndex = sortIndex;
    }
}
