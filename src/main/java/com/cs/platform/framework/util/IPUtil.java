package com.cs.platform.framework.util;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class IPUtil {
    protected static Logger logger = LoggerFactory.getLogger(IPUtil.class);

    public static String getRemoteAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    public static String getIPLocation(String ip) {
        try {
            if (StringUtils.equals("0:0:0:0:0:0:0:1", ip)) {
                return "本地登录";
            }
            String url = "https://sp0.baidu.com/8aQDcjqpAAV3otqbppnN2DJv/api.php?query=" + ip
                    + "&co=&resource_id=6006&t=1521081783380&ie=utf8&oe=gbk&cb=op_aladdin_callback&format=json&tn=baidu&cb=jQuery110206924479429727319_1521075228782&_=1521075228798";
            Request rq = new Request.Builder().url(url).get().build();
            OkHttpClient client = OkHttpUtils.getIgnoreSSLClient();
            Response response = client.newCall(rq).execute();
            String body = response.body().string();
            if (StringUtils.isNotEmpty(body) && body.indexOf("[") != -1) {
                body = body.substring(body.indexOf("[") + 1, body.lastIndexOf("]"));
                Map map = (Map) JsonMapper.nonDefaultMapper().fromJson(body, Map.class);
                return map == null || map.get("location") == null ? null : map.get("location").toString();
            }
        } catch (Exception e) {
            logger.error("getIPLocation", e);
        }
        return null;
    }
}
