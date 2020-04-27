package com.cs.platform.framework.constants;


import com.cs.platform.framework.entity.ConsoleConfig;
import com.cs.platform.framework.util.ConfigDef;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chensong
 * @description
 * @date 2019/10/23
 */
public class ConsoleConfigKeysConstants {
    private final static Logger logger = LoggerFactory.getLogger(ConsoleConfigKeysConstants.class);

    private ConsoleConfigKeysConstants() {
    }

    @ConfigDef(cnName = "是否公有云", showType = "2", cnValue = "false", Desc = "是否公有云，若是公有云，配置将会变化，默认为false", sort = 10)
    public final static String IS_PUBLIC_CLOUD = "is_public_cloud";

    @ConfigDef(cnName = "私有云企业号", showType = "3", cnValue = "cs.com", Desc = "是否公有云，若是公有云，配置将会变化，默认为false", sort = 20)
    public final static String PRIVATE_CLOUD_DOMAIN = "private_cloud_domain";

    /**
     * 配置信息设置
     *
     * @return Map
     */
    public static Map<Object, ConsoleConfig> getConfigInstance() {
        Map<Object, ConsoleConfig> map = new HashMap<Object, ConsoleConfig>();
        Class<ConsoleConfigKeysConstants> classType = ConsoleConfigKeysConstants.class;
        Field[] fields = classType.getDeclaredFields();
        for (Field field : fields) {
            boolean hasAnnotation = field.isAnnotationPresent(ConfigDef.class);
            if (hasAnnotation) {
                ConfigDef annotation = field.getAnnotation(ConfigDef.class);
                ConsoleConfig config = new ConsoleConfig();
                try {
                    config.setCval(annotation.cnValue());
                    config.setCkey(String.valueOf(field.get(field.getName())));
                    config.setCnName(annotation.cnName());
                    config.setShowType(annotation.showType());
                    config.setSort(annotation.sort());
                    config.setRemarks(annotation.Desc());
                    config.setGroup(annotation.group());
                    config.setType(annotation.showType());
                    map.put(field.get(field.getName()), config);

                } catch (IllegalArgumentException e) {
                    logger.error("", e);
                } catch (IllegalAccessException e) {
                    logger.error("", e);
                }
            }
        }
        return map;
    }

}
