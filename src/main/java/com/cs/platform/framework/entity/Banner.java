package com.cs.platform.framework.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 横幅
 */
@Entity
@Table(name = "pub_banner")
public class Banner extends BaseEntity {

    @Id
    @GeneratedValue(generator = "UIDGenerator")
    @GenericGenerator(name = "UIDGenerator", strategy = "com.cs.platform.framework.persistence.AssignedUIDGenerator")
    @Column(length = 40)
    private String id;

    @Column(length = 40)
    private String fid;

    @Column(length = 400)
    private String url;

    @Column(columnDefinition = "int(2) NOT NULL default '1'")
    private int sortIndex;

    @Column(length = 200)
    private String href;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
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

    public void setSortIndex(int sortIndex) {
        this.sortIndex = sortIndex;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}
