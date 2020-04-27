package com.cs.platform.framework.rest;

import com.cs.platform.framework.core.RestObject;
import com.cs.platform.framework.entity.ConsoleConfig;
import com.cs.platform.framework.service.ConsoleConfigService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/api/console/config")
@RequiresPermissions("/ConsoleConfigManage/index")
public class ConsoleConfigRestController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ConsoleConfigService consoleConfigService;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public RestObject get() {
        try {
            return RestObject.newOk("查询成功", consoleConfigService.findConfig());
        } catch (Exception e) {
            logger.error("查询失败", e);
            return RestObject.newError("查询失败");
        }
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public RestObject save(@RequestBody ConsoleConfig consoleConfig) {
        try {
            consoleConfigService.saveConsoleConfig(consoleConfig);
            return RestObject.newOk("保存成功");
        } catch (Exception e) {
            logger.error("保存失败", e);
            return RestObject.newError("保存失败");
        }
    }

}
