package com.cs.platform.framework.rest;


import com.cs.platform.framework.core.RestObject;
import com.cs.platform.framework.core.UserUtils;
import com.cs.platform.framework.entity.Banner;
import com.cs.platform.framework.service.BannerService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 分类管理
 */
@Controller
@RequestMapping(value = "/api/banner")
@RequiresPermissions("/bannerManage/index")
public class BannerRestController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private BannerService bannerService;

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    @ResponseBody
    public RestObject get(@RequestParam(value = "page", defaultValue = "1") int pageNumber, @RequestParam(value = "page.size", defaultValue = "10") int pageSize) {
        try {
            return RestObject.newOk("查询成功", bannerService.findPage(pageNumber, pageSize));
        } catch (Exception e) {
            logger.error("查询失败", e);
            return RestObject.newError("查询失败");
        }
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public RestObject save(@RequestBody Banner entity) {
        try {
            bannerService.save(entity);
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
            Banner entity = bannerService.find(id);
            if (entity == null) {
                return RestObject.newError("不存在");
            }
            bannerService.delete(id);
            return RestObject.newOk("删除成功");
        } catch (Exception e) {
            logger.error("删除失败", e);
            return RestObject.newError("删除失败");
        }
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    @ResponseBody
    public RestObject get(@PathVariable("id") String id) {
        try {
            return RestObject.newOk("查询成功", bannerService.find(id));
        } catch (Exception e) {
            logger.error("查询失败", e);
            return RestObject.newError("查询失败");
        }
    }

    @RequestMapping(value = "/get/banner", method = RequestMethod.GET)
    @ResponseBody
    public RestObject getAllBanner() {
        try {
            return RestObject.newOk("", bannerService.getAll(UserUtils.getTenantId()));
        } catch (Exception e) {
            logger.error("查询banner失败", e);
            return RestObject.newError("查询banner失败");
        }
    }
}
