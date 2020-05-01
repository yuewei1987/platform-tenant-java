package com.cs.platform.framework.rest;

import com.cs.platform.framework.core.RestObject;
import com.cs.platform.framework.util.TinyPngUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Controller
@RequestMapping(value = "/api/upload/file")
public class FileRestController {
    private static final Logger logger = LoggerFactory.getLogger(FileRestController.class);

    @Value("${file.img.path}")
    private String imgPath;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public RestObject upload(MultipartFile file) {
        try {
            File f = new File(imgPath);
            //如果目录不存在
            if (!f.exists()) {
                //创建目录
                f.mkdirs();
            }
            String fid = UUID.randomUUID().toString();
            f = new File(imgPath, fid + ".png");
            file.transferTo(f);

            TinyPngUtils.shrink(f);

//            PicUtils.commpressPicForScale(f.getAbsolutePath(), f.getAbsolutePath(), 1000, 0.8, 1200, 1200); // 图片小于1000kb
//            logger.info(StringUtils.equals(contextPath, "/") ? "" : contextPath);
            return RestObject.newOk("", (StringUtils.equals(contextPath, "/") ? "" : contextPath) + "/open/file/view/" + fid);
        } catch (Exception e) {
            logger.error("", e);
        }
        return RestObject.newError("");
    }
}
