package com.cs.platform.framework.service.security.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author
 * @description
 * @date 2019/6/14
 */
public class RestAnonymousFilter extends PathMatchingFilter {
    private final Logger logger = LoggerFactory.getLogger(RestAnonymousFilter.class);

    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        // ***********************************打印cookie测试*************************************************
        if (logger.isInfoEnabled()) {// 如果启用info日志调试
            Cookie cookies[] = httpServletRequest.getCookies();
            String clientIp;
            if (httpServletRequest.getHeader("x-forwarded-for") == null) {
                clientIp = request.getRemoteAddr();
            } else {
                clientIp = httpServletRequest.getHeader("x-forwarded-for");
            }
            StringBuffer sb = new StringBuffer();
            if (cookies != null) {
                Cookie cookie = null;
                sb.append("clientIp:" + clientIp);
                sb.append("  requestUrl:" + httpServletRequest.getRequestURL());
                sb.append("  cookies:[");
                logger.info("******clientIp:" + clientIp + "*********:" + httpServletRequest.getRequestURL());
                for (int i = 0; i < cookies.length; i++) {
                    cookie = cookies[i];
                    if (i > 0) {
                        sb.append(",");
                    }
                    sb.append(cookie.getName());
                    sb.append(":");
                    sb.append(cookie.getValue());
                }
                sb.append("]");
            }
            logger.info(sb.toString());
        }
        // ***********************************打印cookie测试 end*************************************************

        if (httpServletRequest.getRequestURL().indexOf("/api/login") != -1 || httpServletRequest.getRequestURL().indexOf("/api/mobile/login") != -1) {// 登录api不作拦截
            return true;
        }
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        if (session != null && null != subject.getPrincipal()) {// 如果是已经登录状态，则可以直接访问
            return true;
        } else {
            try {
                httpServletRequest.getRequestDispatcher("/open/login/timeout").forward(httpServletRequest, httpServletResponse);
            } catch (Exception e) {
                logger.error("出错了", e);
            }
            return false;
        }
    }
}
