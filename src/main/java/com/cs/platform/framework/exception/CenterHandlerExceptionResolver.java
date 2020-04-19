package com.cs.platform.framework.exception;

import com.cs.platform.framework.core.RestObject;
import com.cs.platform.framework.util.JsonMapper;
import com.google.common.net.HttpHeaders;
import org.apache.catalina.connector.ClientAbortException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class CenterHandlerExceptionResolver implements HandlerExceptionResolver {

    /**
     * 日志对象
     */
    protected Logger logger = LoggerFactory.getLogger(getClass());


    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        ModelAndView mv = new ModelAndView();
        String requestURI = request.getRequestURI();
        if (requestURI != null && requestURI.startsWith("/error")) {// 防止500页面继续抛500异常导致循环
            logger.error("统一捕获异常: ", ex);// 把漏网的异常信息记入日志
            return mv;
            // return null;
        }
        if (requestURI != null && requestURI.startsWith(contextPath + "/api")) {
            if (ex instanceof UnauthorizedException || ex instanceof UnauthorizedException || ex instanceof org.apache.shiro.authz.UnauthenticatedException) {
                // Http 1.0 header
                response.setDateHeader(HttpHeaders.EXPIRES, 1L);
                response.addHeader(HttpHeaders.PRAGMA, "no-cache");
                // Http 1.1 header
                response.setHeader(HttpHeaders.CACHE_CONTROL, "no-cache, no-store, max-age=0");
                PrintWriter writer;
                try {
                    writer = response.getWriter();
                    RestObject rs = new RestObject(RestObject.STATUS_CODE_FORBIDDEN, "没有权限");
                    // ajax输出
                    writer.write(JsonMapper.nonDefaultMapper().toJson(rs));
                } catch (IOException e) {
                }
                return mv;
            }
        }
        if (ex instanceof ClientAbortException) {
            logger.error("ClientAbortException", ex);
            return mv;
        }
        if (ex instanceof Exception) {
            logger.error("Exception", ex);
            return mv;
        }
        return mv;
    }

}
