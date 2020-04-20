package com.cs.platform.framework.rest;


import com.cs.platform.framework.core.RestObject;
import com.cs.platform.framework.core.UserUtils;
import com.cs.platform.framework.entity.LoginLog;
import com.cs.platform.framework.entity.Tenant;
import com.cs.platform.framework.realm.RestUsernamePasswordToken;
import com.cs.platform.framework.service.LoginLogService;
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
    private LoginLogService loginLogService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public RestObject login(HttpServletRequest request, String username, String password, String domainName) {
        Subject subject = SecurityUtils.getSubject();
        String host = IPUtil.getRemoteAddr(request);
        try {
            //写登录日志
            String address = IPUtil.getIPLocation(host);
            LoginLog log = new LoginLog(UserUtils.getUserId(), host, address);
            String tenantId = "";
            if (StringUtils.isNotEmpty(domainName)) {
                tenantId = tenantService.findTenantByDomainName(domainName).getTenantId();
                log.setTenant(new Tenant(tenantId));
            }
            RestUsernamePasswordToken token = new RestUsernamePasswordToken(username, password, host, tenantId);
            subject.login(token);
            loginLogService.save(log);
            return RestObject.newOk("", UserUtils.getUserProfile());
        } catch (Exception ex) {
            logger.error("登录失败", ex);
        }
        return RestObject.newError("");
    }
}
