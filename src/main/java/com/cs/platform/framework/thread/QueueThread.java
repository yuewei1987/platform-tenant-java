package com.cs.platform.framework.thread;

import com.cs.platform.framework.core.SpringContextHolder;
import com.cs.platform.framework.entity.LoginLog;
import com.cs.platform.framework.service.LoginLogService;
import com.cs.platform.framework.service.QueueService;
import com.cs.platform.framework.util.IPUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author chensong
 * @description
 * @date 2019/12/10
 */
public class QueueThread implements Runnable {

  protected static Logger logger = LoggerFactory.getLogger(QueueThread.class);

  /**
   * QueueService
   */
  private QueueService queueService;

  private LoginLogService loginLogService;

  /**
   * 获取队列服务
   *
   * @return QueueService
   */
  private LoginLogService getLoginLogService() {
    if (loginLogService == null) {
      loginLogService = SpringContextHolder.getBean(LoginLogService.class);
    }
    return loginLogService;
  }

  private QueueService getQueueService() {
    if (queueService == null) {
      queueService = SpringContextHolder.getBean(QueueService.class);
    }
    return queueService;
  }

  @Override
  public void run() {
    while (true) {
      try {
        while (!getQueueService().isEmpty()) {
          LoginLog loginLog = getQueueService().poll();
          loginLog.setAddress(IPUtil.getIPLocation(loginLog.getIp()));
          getLoginLogService().save(loginLog);
        }
      } catch (Exception ee) {
        logger.error("QueueThread run", ee);
      } finally {
        try {
          Thread.sleep(1000 * 30L);
        } catch (Exception e) {
          logger.error(e.toString(), e);
        }
      }
    }
  }


}
