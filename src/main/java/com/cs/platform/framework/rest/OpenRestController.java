package com.cs.platform.framework.rest;

import com.cs.platform.framework.constants.ConfigKeysConstants;
import com.cs.platform.framework.core.RestObject;
import com.cs.platform.framework.core.UserUtils;
import com.cs.platform.framework.service.ConfigService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @author
 * @description
 * @date 2019/6/14
 */
@Controller
@RequestMapping(value = "/open")
public class OpenRestController {

  private final Logger logger = LoggerFactory.getLogger(getClass());

  @Value("${file.img.path}")
  private String imgPath;

  @Autowired
  private ConfigService configService;

  /**
   * 用于api会话过期
   */
  @GetMapping("/login/timeout")
  @ResponseBody
  public RestObject loginTimeout() {
    return RestObject.newTimeout("会话过期，请重新登录。");
  }

  @GetMapping("/login/forbidden")
  @ResponseBody
  @ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "没有权限访问此站。")
  public RestObject forbidden() {
    return new RestObject(RestObject.STATUS_CODE_FORBIDDEN, "没有权限访问此站。");
  }

  @GetMapping("/user/get")
  @ResponseBody
  public RestObject getUser() {
    try {
      return RestObject.newOk("查询用户成功", UserUtils.getUserProfile());
    } catch (Exception e) {
      logger.error("查询用户失败", e);
      return RestObject.newError("查询用户失败");
    }
  }

  @GetMapping("/system/title")
  @ResponseBody
  public RestObject getTitle() {
    try {
      return RestObject.newOk("查询系统名称成功", configService.getStringValue(ConfigKeysConstants.SYSTEMPROFILES_TITLE));
    } catch (Exception e) {
      logger.error("查询用户失败", e);
      return RestObject.newError("查询系统名称失败");
    }
  }

  /**
   * 图片预览
   *
   * @param fid
   * @param response
   */
  @RequestMapping(value = "/file/view/{fid}", method = RequestMethod.GET, produces = MediaType.IMAGE_PNG_VALUE)
  public void downloadFile(@PathVariable String fid, HttpServletResponse response) {
    response.setContentType("image/jpeg");
    OutputStream toClient = null;
    try {
      toClient = response.getOutputStream();
      File file = new File(imgPath);
      if (!file.exists()) {
        file.mkdirs();
      }
      if (StringUtils.isNotEmpty(fid) && StringUtils.isNotEmpty(imgPath)) {
        imgPath = StringUtils.endsWith(imgPath, "/") ? imgPath : imgPath + "/";
        InputStream in = new FileInputStream(imgPath + fid + ".png");
        byte[] bytes = new byte[in.available()];
        in.read(bytes);
        toClient.write(bytes);
      }
    } catch (IOException e) {
      logger.error("transfer byte error", e);
    } finally {
      if (toClient != null) {
        try {
          toClient.close();
        } catch (IOException e) {
          logger.error("close", e);
        }
      }
    }
  }

}
