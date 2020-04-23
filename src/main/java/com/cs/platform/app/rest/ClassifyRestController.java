package com.cs.platform.app.rest;


import com.cs.platform.app.entity.Classify;
import com.cs.platform.app.service.ClassifyService;
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
 * 分类管理
 */
@Controller
@RequestMapping(value = "/api/classify")
@RequiresPermissions("/typeManage/index")
public class ClassifyRestController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ClassifyService classifyService;
    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    @ResponseBody
    public RestObject get(@RequestParam(value = "page", defaultValue = "1") int pageNumber, @RequestParam(value = "page.size", defaultValue = "10") int pageSize) {
        try {
            return RestObject.newOk("查询成功", classifyService.findPage(pageNumber, pageSize));
        } catch (Exception e) {
            logger.error("查询失败", e);
            return RestObject.newError("查询失败");
        }
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public RestObject save(@RequestBody Classify entity) {
        try {
            classifyService.save(entity);
            return RestObject.newOk("保存分类成功");
        } catch (Exception e) {
            logger.error("保存分类失败", e);
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
            if (productService.getProductCountByClassify(id) > 0) {
                return RestObject.newError("删除失败，请先移除此分类下的所有商品！");
            }
            Classify entity = classifyService.find(id);
            if (entity == null) {
                return RestObject.newError("分类不存在");
            }
            entity.setDelFlag("1");
            classifyService.save(entity);
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
            return RestObject.newOk("查询成功", classifyService.find(id));
        } catch (Exception e) {
            logger.error("查询失败", e);
            return RestObject.newError("查询失败");
        }
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ResponseBody
    public RestObject getAll() {
        try {
            return RestObject.newOk("查询成功", classifyService.getAll(UserUtils.getTenantId()));
        } catch (Exception e) {
            logger.error("查询失败", e);
            return RestObject.newError("查询失败");
        }
    }
}
