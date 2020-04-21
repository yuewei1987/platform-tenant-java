package com.cs.platform.framework.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 用户组实体
 */
@Entity
@Table(name = "pub_user_group")
public class UserGroup extends BaseEntity {

    @Id
    @GeneratedValue(generator = "UIDGenerator")
    @GenericGenerator(name = "UIDGenerator", strategy = "com.cs.platform.framework.persistence.AssignedUIDGenerator")
    @Column(length = 40)
    private String id;


    @Column(length = 200)
    private String name;

    @Transient
    private String[] menus;

    @Transient
    private String[] halfHenus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String[] getMenus() {
        return menus;
    }

    public String[] getHalfHenus() {
        return halfHenus;
    }

    public void setHalfHenus(String[] halfHenus) {
        this.halfHenus = halfHenus;
    }

    public void setMenus(String[] menus) {
        this.menus = menus;
    }

    public void setName(String name) {
        this.name = name;
    }
}
