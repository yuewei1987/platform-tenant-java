package com.cs.platform.framework.util;

import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

/**
 * https://tinypng.com/
 */
public class TinyPngUtils {
    protected static Logger logger = LoggerFactory.getLogger(TinyPngUtils.class);

    public static void shrink(File file) {
        //上传文件
        MediaType parse = MediaType.parse("image/jpeg");
        Request rq = new Request.Builder().url("https://tinypng.com/web/shrink").post(RequestBody.create(parse, file)).build();
        OkHttpClient client = OkHttpUtils.getIgnoreSSLClient();
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        try {
            Response response = client.newCall(rq).execute();
            String body = response.body().string();
            logger.info(body);
            Map map = (Map) JsonMapper.nonDefaultMapper().fromJson(body, Map.class);
            map = (Map) map.get("output");

            //下载文件
            file = new File(file.getPath());
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);

            client = OkHttpUtils.getIgnoreSSLClient();
            rq = new Request.Builder().url(map.get("url").toString()).get().build();
            response = client.newCall(rq).execute();
            bos.write(response.body().bytes());

        } catch (IOException e) {
            logger.error("IOException ", e);
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
}
