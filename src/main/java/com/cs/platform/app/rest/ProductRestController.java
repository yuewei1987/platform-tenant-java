package com.cs.platform.app.rest;


import com.cs.platform.app.entity.Product;
import com.cs.platform.app.service.ProductService;
import com.cs.platform.framework.core.RestObject;
import com.cs.platform.framework.core.UserUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 产品管理
 */
@Controller
@RequestMapping(value = "/api/product")
@RequiresPermissions("/productManage/index")
public class ProductRestController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    @ResponseBody
    public RestObject get(@RequestParam(value = "page", defaultValue = "1") int pageNumber, @RequestParam(value = "page.size", defaultValue = "10") int pageSize, @RequestParam(defaultValue = "-1") int state, String name) {
        try {
            return RestObject.newOk("查询成功", productService.findPage(pageNumber, pageSize, state, name));
        } catch (Exception e) {
            logger.error("查询失败", e);
            return RestObject.newError("查询失败");
        }
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public RestObject save(@RequestBody Product entity) {
        try {
            productService.save(entity);
            return RestObject.newOk("保存商品成功");
        } catch (Exception e) {
            logger.error("保存商品失败", e);
            return RestObject.newError("保存商品失败");
        }
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public RestObject delete(@PathVariable("id") String id) {
        try {
            if (StringUtils.isEmpty(id)) {
                return RestObject.newError("参数不能为空");
            }
            Product entity = productService.find(id);
            if (entity == null) {
                return RestObject.newError("不存在");
            }
            entity.setDelFlag("1");
            productService.save(entity);
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
            return RestObject.newOk("查询成功", productService.find(id));
        } catch (Exception e) {
            logger.error("查询失败", e);
            return RestObject.newError("查询失败");
        }
    }

    @RequestMapping(value = "/state/{id}/{state}", method = RequestMethod.POST)
    @ResponseBody
    public RestObject get(@PathVariable("id") String id, @PathVariable("state") String state) {
        try {
            productService.updateState(id, state);
            return RestObject.newOk("更新成功");
        } catch (Exception e) {
            logger.error("更新失败", e);
            return RestObject.newError("更新失败");
        }
    }

    @RequestMapping(value = "/get/product", method = RequestMethod.GET)
    @ResponseBody
    public RestObject getAllProduct() {
        try {
            return RestObject.newOk("", productService.findAllState(UserUtils.getTenantId()));
        } catch (Exception e) {
            logger.error("查询product失败", e);
            return RestObject.newError("查询product失败");
        }
    }
}
