package com.cs.platform.framework.rest;

import com.cs.platform.framework.core.RestObject;
import com.cs.platform.framework.core.UserUtils;
import com.cs.platform.framework.entity.User;
import com.cs.platform.framework.service.UserService;
import com.cs.platform.framework.util.Passwrods;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
@RequiresPermissions("/userManagePwd/index")
public class UserPwdRestController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/updatePwd", method = RequestMethod.POST)
    public RestObject upDatePwd(String userId, String oldPwd, String newPwd) {
        try {
            if (!StringUtils.equals(userId, UserUtils.getUserId())) {
                return RestObject.newError("您没有权限");
            }
            User user = userService.findUserByUserId(userId);
            if (user == null) {
                return RestObject.newError("用户不存在");
            }
            if (!Passwrods.validatePassword(oldPwd, user.getPassword())) {
                return new RestObject(100, "旧密码错误");
            }
            userService.updatePasswordById(Passwrods.entryptPassword(newPwd), userId);
            return RestObject.newOk("修改成功");
        } catch (Exception e) {
            logger.error("login", e);
            return RestObject.newError("修改出错");
        }
    }

}
