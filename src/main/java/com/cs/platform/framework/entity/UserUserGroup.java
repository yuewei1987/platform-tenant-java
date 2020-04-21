package com.cs.platform.framework.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * @author chensong
 * @description
 * @date 2019/7/2
 */
@Entity
@Table(name = "pub_user_user_group")
public class UserUserGroup extends BaseEntity {
    /**
     * 组织id
     */
    @Id
    @GeneratedValue(generator = "UIDGenerator")
    @GenericGenerator(name = "UIDGenerator", strategy = "com.cs.platform.framework.persistence.AssignedUIDGenerator")
    @Column(length = 80)
    private String id;

    /**
     * 组织id
     */
    @ManyToOne
    @JoinColumn(name = "group_id")
    @Where(clause = "del_flag='0'")
    private UserGroup userGroup;
    /**
     * 人员id
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    @Where(clause = "del_flag='0'")
    private User user;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
