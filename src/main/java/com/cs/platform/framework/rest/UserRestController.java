package com.cs.platform.framework.rest;

import com.cs.platform.framework.core.RestObject;
import com.cs.platform.framework.core.UserUtils;
import com.cs.platform.framework.entity.User;
import com.cs.platform.framework.service.UserGroupService;
import com.cs.platform.framework.service.UserService;
import com.cs.platform.framework.util.Passwrods;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author
 * @description
 * @date 2019/6/14
 */
@Controller
@RequestMapping(value = "/api/user")
@RequiresPermissions("/userManage/index")
public class UserRestController {

  private final Logger logger = LoggerFactory.getLogger(getClass());

  @Autowired
  private UserService userService;

  @Autowired
  private UserGroupService userGroupService;

  @RequestMapping(value = "/page", method = RequestMethod.GET)
  @ResponseBody
  public RestObject get(String type, String serchkey, @RequestParam(value = "page", defaultValue = "1") int pageNumber, @RequestParam(value = "page.size", defaultValue = "10") int pageSize) {
    try {
      return RestObject.newOk("查询用户成功", userService.findPageUser(type, serchkey, pageNumber, pageSize));
    } catch (Exception e) {
      logger.error("查询用户失败", e);
      return RestObject.newError("查询用户失败");
    }
  }

  @RequestMapping(value = "/save", method = RequestMethod.POST)
  @ResponseBody
  public synchronized RestObject save(@RequestBody User entity) {
    try {
      // 如果新密码为空，则不更换密码
      if (StringUtils.isNotBlank(entity.getPassword1())) {
        if (StringUtils.isNotEmpty(entity.getUserId())) {
          userService.updatePasswordById(Passwrods.entryptPassword(entity.getPassword1()), entity.getUserId());
        } else {
          entity.setPassword(Passwrods.entryptPassword(entity.getPassword1()));
        }
      }
      if (userService.findUser(entity.getAccount(), entity.getUserId(), UserUtils.getTenantId()) != null || userService.findUser(entity.getPhone(), entity.getUserId(), UserUtils.getTenantId()) != null) {
        return RestObject.newError("账号或手机号码已经存在");
      }
      entity.setType(User.TYPE_INNER_NORMAL);
      userService.save(entity);
      return RestObject.newOk("保存用户成功");
    } catch (Exception e) {
      logger.error("保存用户失败", e);
      return RestObject.newError("保存用户失败");
    }
  }

  @RequestMapping(value = "/delete/{userId}", method = RequestMethod.POST)
  @ResponseBody
  public RestObject delete(@PathVariable("userId") String userId) {
    try {
      if (StringUtils.isEmpty(userId)) {
        return RestObject.newError("参数不能为空");
      }
      User user = userService.findUserByUserId(userId);
      if (user == null) {
        return RestObject.newError("用户不存在");
      }
      user.setDelFlag("1");
      userService.save(user);
      return RestObject.newOk("删除用户成功");
    } catch (Exception e) {
      logger.error("删除用户失败", e);
      return RestObject.newError("删除用户失败");
    }
  }

  @RequestMapping(value = "/get/{userId}", method = RequestMethod.GET)
  @ResponseBody
  public RestObject get(@PathVariable("userId") String userId) {
    try {
      if (StringUtils.isEmpty(userId)) {
        return RestObject.newError("参数不能为空");
      }
      return RestObject.newOk("查询用户成功", userService.findUserByUserId(userId));
    } catch (Exception e) {
      logger.error("查询用户失败", e);
      return RestObject.newError("查询用户失败");
    }
  }

  @RequestMapping(value = "/getAllGroup", method = RequestMethod.GET)
  @ResponseBody
  public RestObject getAllGroup() {
    try {
      return RestObject.newOk("查询成功", userGroupService.getAllGroup());
    } catch (Exception e) {
      logger.error("查询失败", e);
      return RestObject.newError("查询失败");
    }
  }


}
