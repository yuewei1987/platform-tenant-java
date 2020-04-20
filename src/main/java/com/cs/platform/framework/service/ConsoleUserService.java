package com.cs.platform.framework.service;

import com.cs.platform.framework.entity.ConsoleUser;
import com.cs.platform.framework.repository.ConsoleUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsoleUserService {
@Autowired
private ConsoleUserDao consoleUserDao;

    /**
     * 根据account获取
     *
     * @param account 账户
     * @return 对象实例
     */
    public ConsoleUser findByAccount(String account) {
        return consoleUserDao.findByAccount(account);
    }
}
