package com.cs.platform.framework.rest;


import com.cs.platform.framework.core.RestObject;
import com.cs.platform.framework.core.UserUtils;
import com.cs.platform.framework.entity.LoginLog;
import com.cs.platform.framework.entity.Tenant;
import com.cs.platform.framework.realm.RestUsernamePasswordToken;
import com.cs.platform.framework.service.QueueService;
import com.cs.platform.framework.service.TenantService;
import com.cs.platform.framework.util.IPUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/api")
public class LoginRestController {
  private final Logger logger = LoggerFactory.getLogger(getClass());

  @Autowired
  private TenantService tenantService;

  @Autowired
  private QueueService queueService;

  @RequestMapping(value = "/login", method = RequestMethod.POST)
  @ResponseBody
  public RestObject login(HttpServletRequest request, String username, String password, String domainName) {
    Subject subject = SecurityUtils.getSubject();
    String host = IPUtil.getRemoteAddr(request);
    try {
      String tenantId = "";
      if (StringUtils.isNotEmpty(domainName)) {
        tenantId = tenantService.findTenantByDomainName(domainName).getTenantId();
      }
      RestUsernamePasswordToken token = new RestUsernamePasswordToken(username, password, host, tenantId);
      subject.login(token);
      if (StringUtils.isNotEmpty(domainName)) {
        //写登录日志 这儿用异步写，不然有可能影响登录速度
        LoginLog log = new LoginLog(UserUtils.getUserId(), host);
        log.setTenant(new Tenant(tenantId));
        log.setUserId(UserUtils.getUserId());
        queueService.offer(log);
      }
      return RestObject.newOk("", UserUtils.getUserProfile());
    } catch (Exception ex) {
      logger.error("登录失败", ex);
    }
    return RestObject.newError("");
  }

  @RequestMapping(value = "/logout", method = RequestMethod.GET)
  @ResponseBody
  public RestObject logout() {
    Subject subject = SecurityUtils.getSubject();
    subject.logout();
    return RestObject.newOk("退出成功");
  }
}
