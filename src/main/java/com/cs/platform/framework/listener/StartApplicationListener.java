package com.cs.platform.framework.listener;

import com.cs.platform.framework.service.BannerService;
import com.cs.platform.framework.service.UserService;
import com.cs.platform.framework.thread.QueueThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author
 * @description
 * @date 2019/6/4
 */
@Component
public class StartApplicationListener implements ApplicationListener<ApplicationReadyEvent> {
  protected Logger logger = LoggerFactory.getLogger(getClass());

  @Autowired
  private UserService userService;
  @Autowired
  private BannerService bannerService;

  @Override
  public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
    logger.info("开始创建缓存..............................................");
    userService.reloadCache();
    bannerService.reloadCache();
    Executor executor = Executors.newCachedThreadPool();
    executor.execute(new QueueThread());
    logger.info("创建缓存成功..............................................");
  }
}
