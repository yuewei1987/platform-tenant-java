package com.cs.platform.framework.rest;

import com.cs.platform.framework.core.RestObject;
import com.cs.platform.framework.service.LoginLogService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 登录日志
 * @description
 * @date 2020/1/15
 */
@Controller
@RequestMapping(value = "/api/loginLog")
@RequiresPermissions("/loginLogManage/index")
public class LoginLogRestController {

  private final Logger logger = LoggerFactory.getLogger(getClass());

  @Autowired
  private LoginLogService loginLogService;

  @RequestMapping(value = "/page", method = RequestMethod.GET)
  @ResponseBody
  public RestObject get(@RequestParam(value = "page", defaultValue = "1") int pageNumber, @RequestParam(value = "page.size", defaultValue = "10") int pageSize) {
    try {
      return RestObject.newOk("查询成功", loginLogService.findPage(pageNumber, pageSize));
    } catch (Exception e) {
      logger.error("查询失败", e);
      return RestObject.newError("查询失败");
    }
  }

}
