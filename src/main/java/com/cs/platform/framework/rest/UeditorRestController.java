package com.cs.platform.framework.rest;
/**
 * @author chensong
 * @description
 * @date 2019/9/10
 */

import com.baidu.ueditor.ActionEnter;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Author: BaiDing
 * @Date: 2018/8/30 11:58
 * @Email: liujiabaiding@foxmail.com
 */
@RestController
@RequestMapping(value = "")
public class UeditorRestController {

    private static final Logger logger = LoggerFactory.getLogger(UeditorRestController.class);


    @Value("${file.img.path}")
    private String imgPath;

    @Value("${server.servlet.context-path}")
    private String contextPath;


    @RequestMapping("/ueditor/ueditorConfig")
    public void ueditorConfig(HttpServletRequest request, HttpServletResponse response, MultipartFile upfile) {
        response.setContentType("application/json");
        String rootPath = request.getSession().getServletContext().getRealPath("/");
        try {
            String exec = "";

            String actionType = request.getParameter("action");
            logger.info("actionType = {}",actionType);
            logger.info("upfile = {}",upfile);
            if ("uploadimage".equals(actionType) && !upfile.isEmpty()) {
                // 做图片上传操作
                exec = uploadImage(upfile);
            } else if ("uploadvideo".equals(actionType) && !upfile.isEmpty()) {
                // 做视频上传操作
                exec = uploadVideo(upfile);
            } else {
                request.setCharacterEncoding("utf-8");
                exec = new ActionEnter(request, rootPath).exec();
            }
            PrintWriter writer = response.getWriter();
            writer.write(exec);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            logger.error("UeditorController#ueditorConfig exception:", e);
        }
    }

    private String uploadImage(MultipartFile file) {
        JSONObject jsonResult = null;
        try {
            String fileName = file.getOriginalFilename();
            String extraName = fileName.substring(fileName.lastIndexOf("."));
            File f = new File(imgPath);
            //如果目录不存在
            if (!f.exists()) {
                //创建目录
                f.mkdirs();
            }
            String fid = UUID.randomUUID().toString();
            f = new File(imgPath, fid + ".png");
            file.transferTo(f);
            jsonResult = new JSONObject(resultMap("SUCCESS", contextPath + "/open/file/view/" + fid, file.getSize(), fileName, fileName, extraName));

        } catch (Exception e) {
            logger.warn("UeditorController#uploadImage exception:", e);
            jsonResult = new JSONObject(resultMap("文件上传失败", "", 0, "", "", ""));
        }
        return jsonResult.toString();
    }

    private String uploadVideo(MultipartFile file) {
        JSONObject jsonResult = null;
        try {
            String fileName = file.getOriginalFilename();
            String extraName = fileName.substring(fileName.lastIndexOf("."));
            File f = new File(imgPath);
            //如果目录不存在
            if (!f.exists()) {
                //创建目录
                f.mkdirs();
            }
            String fid = UUID.randomUUID().toString();
            f = new File(imgPath, fid + ".mp4");
            file.transferTo(f);
            jsonResult = new JSONObject(resultMap("SUCCESS", contextPath + "/open/file/video/" + fid, file.getSize(), fileName, fileName, extraName));

        } catch (Exception e) {
            logger.warn("UeditorController#uploadImage exception:", e);
            jsonResult = new JSONObject(resultMap("文件上传失败", "", 0, "", "", ""));
        }
        return jsonResult.toString();
    }

    private Map<String, Object> resultMap(String state, String url, long size, String title, String original, String type) {
        Map<String, Object> result = new HashMap<>();
        result.put("state", state);
        result.put("original", original);
        result.put("size", size);
        result.put("title", title);
        result.put("type", type);
        result.put("url", url);
        return result;
    }

}

