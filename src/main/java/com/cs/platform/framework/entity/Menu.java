package com.cs.platform.framework.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

/**
 * 菜单实体
 */
@Entity
@Table(name = "pub_menu")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Menu extends BaseEntity implements Cloneable {
    @Id
    @GeneratedValue(generator = "UIDGenerator")
    @GenericGenerator(name = "UIDGenerator", strategy = "com.cs.platform.framework.persistence.AssignedUIDGenerator")
    @Column(length = 40)
    private String id;

    @Column(length = 40)
    private String name;

    @Column(length = 40)
    private String icon;

    @Column(length = 40)
    private String alias;

    @Column(length = 100)
    @JsonIgnore
    private String component;

    @Column(columnDefinition = "int(2) NOT NULL default '1'")
    @JsonIgnore
    private int sortIndex;

    @Column(length = 40)
    private String pid;

    @Transient
    private List<Menu> child;

    /**
     * 构造函数
     */
    public Menu() {
        super();
    }

    public Menu(String id) {
        super();
        this.delFlag = "0";
        this.id = id;
    }

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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public int getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(int sortIndex) {
        this.sortIndex = sortIndex;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public List<Menu> getChild() {
        return child;
    }

    public void setChild(List<Menu> child) {
        this.child = child;
    }

    @Override
    public Menu clone() {
        Menu stu = null;
        try {
            stu = (Menu) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return stu;
    }
}
