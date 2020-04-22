package com.cs.platform.framework.service;

import com.cs.platform.framework.entity.LoginLog;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author chensong
 * @description
 * @date 2019/12/10
 */
@Component
public class QueueService {
  /**
   * queue
   */
  private ConcurrentLinkedQueue<LoginLog> queue = new ConcurrentLinkedQueue();

  /**
   * 将指定的元素插入此队列
   *
   * @param obj obj
   */
  public void offer(LoginLog obj) {
    if (obj == null) {
      return;
    }
    queue.offer(obj);
  }

  /**
   * 获取并移除此队列的头
   *
   * @return UserAction
   */
  public LoginLog poll() {
    return queue.poll();
  }

  /**
   * 是否空
   *
   * @return isEmpty
   */
  public boolean isEmpty() {
    return queue.isEmpty();
  }
}
