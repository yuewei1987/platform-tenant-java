package com.cs.platform.framework.rest;

import com.cs.platform.framework.core.RestObject;
import com.cs.platform.framework.entity.UserGroup;
import com.cs.platform.framework.service.UserGroupService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/api/userGroup")
@RequiresPermissions("/userGroupManage/index")
public class UserGroupRestController {
  private final Logger logger = LoggerFactory.getLogger(getClass());

  @Autowired
  private UserGroupService userGroupService;


  @RequestMapping(value = "/page", method = RequestMethod.GET)
  @ResponseBody
  public RestObject get(String serchkey, @RequestParam(value = "page", defaultValue = "1") int pageNumber, @RequestParam(value = "page.size", defaultValue = "10") int pageSize) {
    try {
      return RestObject.newOk("查询成功", userGroupService.findPage(serchkey, pageNumber, pageSize));
    } catch (Exception e) {
      logger.error("查询失败", e);
      return RestObject.newError("查询失败");
    }
  }

  @RequestMapping(value = "/save", method = RequestMethod.POST)
  @ResponseBody
  public synchronized RestObject save(@RequestBody UserGroup entity) {
    try {
      userGroupService.save(entity);
      return RestObject.newOk("保存成功");
    } catch (Exception e) {
      logger.error("保存失败", e);
      return RestObject.newError("失败");
    }
  }

  @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
  @ResponseBody
  public RestObject delete(@PathVariable("id") String id) {
    try {
      if (StringUtils.isEmpty(id)) {
        return RestObject.newError("参数不能为空");
      }
      UserGroup entity = userGroupService.find(id);
      if (entity == null) {
        return RestObject.newError("不存在");
      }
      entity.setDelFlag("1");
      userGroupService.save(entity);
      return RestObject.newOk("删除成功");
    } catch (Exception e) {
      logger.error("删除失败", e);
      return RestObject.newError("删除失败");
    }
  }

  @RequestMapping(value = "/get/{groupId}", method = RequestMethod.GET)
  @ResponseBody
  public RestObject get(@PathVariable("groupId") String groupId) {
    try {
      return RestObject.newOk("查询成功", userGroupService.getMenuByGroupId(groupId));
    } catch (Exception e) {
      logger.error("查询失败", e);
      return RestObject.newError("查询失败");
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
