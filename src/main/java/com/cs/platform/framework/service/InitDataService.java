package com.cs.platform.framework.service;

import com.cs.platform.framework.entity.ConsoleUser;
import com.cs.platform.framework.repository.ConsoleUserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author
 * @description
 * @date 2019/6/14
 */
@Component
public class InitDataService {
    /**
     * 默认的实例id
     */
    public static final String DEFAULT_USER_ID = "00014a7a496bd41d";
    /**
     * logger
     */
    protected Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ConsoleUserDao consoleUserDao;


    /**
     * 初始化默认实例
     */
    @PostConstruct
    public void init() {
        initDefalutConsoleUser();
    }

    public void initDefalutConsoleUser() {
        ConsoleUser instance = consoleUserDao.findById(DEFAULT_USER_ID).orElse(null);
        if (instance != null) {
            return;
        }
        instance = new ConsoleUser();
        instance.setId(DEFAULT_USER_ID);
        instance.setName("管理员");
        instance.setAccount("yw-admin");
        instance.setPassword("54c820a4c737cd635b83e3e42af94fb4d75405d38d376a15102cc007");
        instance.setPhone("13800000000");
        instance.setGender("0");
        consoleUserDao.saveAndFlush(instance);
        logger.info("初始化【运维用户】数据：" + instance);
    }

}
