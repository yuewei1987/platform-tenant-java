package com.cs.platform.framework.service;

import com.cs.platform.framework.core.UserUtils;
import com.cs.platform.framework.entity.Menu;
import com.cs.platform.framework.repository.MenuDao;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author
 * @description
 * @date 2019/6/29
 */
@Component
public class MenuService {
    @Autowired
    private MenuDao menuDao;

    public List<Menu> getMenu() {
        List<Menu> list = UserUtils.getUserProfile().getMenus();
        List<Menu> clone = Lists.newArrayList();
        for (Menu menu : list) {
            //是产生新的对象 否则 会重复显示
            clone.add(menu.clone());
        }
        List<Menu> result = Lists.newArrayList();
        buildTree(clone, result);
        return result;
    }

    public List<Menu> getMenu(String userId) {
        if (StringUtils.equals(userId, InitDataService.DEFAULT_USER_ID)) {
            return menuDao.findAdminMenu();
        } else {
            return menuDao.findMenuByUserId(userId);
        }
    }


    private void buildTree(List<Menu> list, List<Menu> result) {
        for (Menu m : list) {
            if (StringUtils.isEmpty(m.getPid())) {
                if (StringUtils.equals(m.getDelFlag(), "0")) {
                    result.add(m);
                    //找下级
                    buildSubTree(m, list);
                }
            }
        }
    }

    private void buildSubTree(Menu menu, List<Menu> list) {
        for (Menu m : list) {
            if (StringUtils.equals(m.getDelFlag(), "1")) {
                continue;
            }
            if (StringUtils.equals(m.getPid(), menu.getId())) {
                List<Menu> child = menu.getChild();
                if (child == null) {
                    child = Lists.newArrayList();
                    menu.setChild(child);
                }
                child.add(m);
                buildSubTree(m, list);
            }
        }
    }

}
