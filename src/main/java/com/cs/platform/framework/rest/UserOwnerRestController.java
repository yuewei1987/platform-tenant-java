package com.cs.platform.framework.rest;

import com.cs.platform.framework.core.RestObject;
import com.cs.platform.framework.core.UserUtils;
import com.cs.platform.framework.entity.User;
import com.cs.platform.framework.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author
 * @description
 * @date 2019/6/14
 */
@Controller
@RequestMapping(value = "/api/user")
@RequiresPermissions("/userManageOwner/index")
public class UserOwnerRestController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user/info", method = RequestMethod.GET)
    @ResponseBody
    public RestObject getUser() {
        try {
            return RestObject.newOk("查询用户成功", userService.findUserByUserId(UserUtils.getUserId()));
        } catch (Exception e) {
            logger.error("查询用户失败", e);
            return RestObject.newError("查询用户失败");
        }
    }

    @RequestMapping(value = "/info/save", method = RequestMethod.POST)
    @ResponseBody
    public synchronized RestObject save(@RequestBody User entity) {
        try {
            User currUser = userService.findUserByUserId(UserUtils.getUserId());
            if (!StringUtils.equals(entity.getUserId(), UserUtils.getUserId())) {
                return RestObject.newError("您没有权限");
            }
            if (userService.findUser(entity.getAccount(), entity.getUserId()) != null || userService.findUser(entity.getPhone(), entity.getUserId()) != null) {
                return RestObject.newError("账号或手机号码已经存在");
            }
            currUser.setPhone(entity.getPhone());
            currUser.setAccount(entity.getAccount());
            currUser.setName(entity.getName());
            currUser.setNick(entity.getNick());
            currUser.setGender(entity.getGender());
            currUser.setAvatarUrl(entity.getAvatarUrl());
            userService.save(currUser);
            UserUtils.getUserProfile().setUserName(currUser.getName());
            UserUtils.getUserProfile().setAvatarUrl(currUser.getAvatarUrl());
            return RestObject.newOk("保存用户成功");
        } catch (Exception e) {
            logger.error("保存用户失败", e);
            return RestObject.newError("保存用户失败");
        }
    }


}
