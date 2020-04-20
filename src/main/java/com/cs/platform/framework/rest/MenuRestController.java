package com.cs.platform.framework.rest;

import com.cs.platform.framework.core.RestObject;
import com.cs.platform.framework.service.MenuService;
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
@RequestMapping(value = "/api/menu")
public class MenuRestController {

  private final Logger logger = LoggerFactory.getLogger(getClass());

  @Autowired
  private MenuService menuService;

  @RequestMapping(value = "/get", method = RequestMethod.GET)
  @ResponseBody
  public RestObject getMenu() {
    try {
      return RestObject.newOk("", menuService.getMenu());
    } catch (Exception e) {
      logger.error("查询菜单失败", e);
      return RestObject.newError("查询菜单失败");
    }
  }


}
