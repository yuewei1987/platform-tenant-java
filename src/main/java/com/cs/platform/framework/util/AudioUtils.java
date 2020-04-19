package com.cs.platform.framework.util;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class AudioUtils {
    protected static Logger logger = LoggerFactory.getLogger(AudioUtils.class);
    private static String accessToken = null;


    private static Cache<String, String> cache = CacheBuilder.newBuilder().expireAfterWrite(28, TimeUnit.DAYS).maximumSize(Long.MAX_VALUE).build();


    public static synchronized String getAccessToken() {
        accessToken = cache.getIfPresent("ACCESSTOKEN");
        if (accessToken == null) {
            try {
                String url = "https://openapi.baidu.com/oauth/2.0/token?grant_type=client_credentials&client_id=KG93EGGSPwCqiWwqOTOc11LL&client_secret=VoTiT2CGUysppWtwrA89xaKdtk1wrUaG";
                Request rq = new Request.Builder().url(url).get().build();
                OkHttpClient client = OkHttpUtils.getIgnoreSSLClient();
                Response response = client.newCall(rq).execute();
                String body = response.body().string();
                logger.info("body = {}", body);
                Map map = (Map) JsonMapper.nonDefaultMapper().fromJson(body, Map.class);
                accessToken = map.get("access_token").toString();
                cache.put("ACCESSTOKEN", accessToken);
                logger.info("accessToken = {}", accessToken);
            } catch (IOException e) {
                logger.error("getAccessToken", e);
            }
        }
        return accessToken;
    }

    public static byte[] text2audio(String text) {
        accessToken = getAccessToken();
        try {
            //传递tex参数时做2次urlencode编码。
            text = URLEncoder.encode(text, "UTF-8").toLowerCase();
            text = URLEncoder.encode(text, "UTF-8").toLowerCase();
            String url = "http://tsn.baidu.com/text2audio?tex=" + text + "&lan=zh&cuid=123&ctp=1&tok=" + getAccessToken();
            Request rq = new Request.Builder().url(url).get().build();
            OkHttpClient client = OkHttpUtils.getIgnoreSSLClient();
            Response response = client.newCall(rq).execute();
            return response.body().bytes();
        } catch (IOException e) {
            logger.error("text2audio", e);
        }
        return null;
    }


    public static void main(String[] args) {
        AudioUtils.getAccessToken();
    }
}
