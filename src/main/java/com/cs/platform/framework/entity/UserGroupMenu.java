package com.cs.platform.framework.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * 用户组菜单实体
 */
@Entity
@Table(name = "pub_user_group_menu")
public class UserGroupMenu extends BaseEntity {

    @Id
    @GeneratedValue(generator = "UIDGenerator")
    @GenericGenerator(name = "UIDGenerator", strategy = "com.cs.platform.framework.persistence.AssignedUIDGenerator")
    @Column(length = 40)
    private String id;

    @ManyToOne
    @JoinColumn(name = "gorup_id")
    @Where(clause = "del_flag='0'")
    private UserGroup userGroup;

    @ManyToOne
    @JoinColumn(name = "menu_id")
    @Where(clause = "del_flag='0'")
    private Menu menu;

    /**
     * 是否是叶子叶点
     */
    private boolean half;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UserGroup getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(UserGroup userGroup) {
        this.userGroup = userGroup;
    }

    public Menu getMenu() {
        return menu;
    }

    public boolean isHalf() {
        return half;
    }

    public void setHalf(boolean half) {
        this.half = half;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }
}
