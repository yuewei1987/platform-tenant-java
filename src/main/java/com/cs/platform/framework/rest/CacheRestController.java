package com.cs.platform.framework.rest;


import com.cs.platform.framework.core.RestObject;
import com.cs.platform.framework.core.UserUtils;
import com.cs.platform.framework.service.BannerService;
import com.cs.platform.framework.service.MenuService;
import com.cs.platform.framework.service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 缓存管理
 */
@Controller
@RequestMapping(value = "/api/cache")
@RequiresPermissions("/cacheManage/index")
public class CacheRestController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MenuService menuService;

    @Autowired
    private UserService userService;

    @Autowired
    private BannerService bannerService;

    @RequestMapping(value = "/clear/{type}", method = RequestMethod.POST)
    @ResponseBody
    public RestObject save(@PathVariable String type) {
        try {
            switch (type) {
                case "menu": {
                    UserUtils.getUserProfile().setMenus(menuService.getMenu(userService.findUserByUserId(UserUtils.getUserId())));
                }
                case "banner": {
                    bannerService.reloadCache();
                }
            }
            return RestObject.newOk("清除成功");
        } catch (Exception e) {
            logger.error("清除失败", e);
            return RestObject.newError("清除失败");
        }
    }

}
