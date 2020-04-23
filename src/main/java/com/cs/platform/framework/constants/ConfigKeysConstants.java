package com.cs.platform.framework.constants;


import com.cs.platform.framework.entity.Config;
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
public class ConfigKeysConstants {
  private final static Logger logger = LoggerFactory.getLogger(ConfigKeysConstants.class);

  private ConfigKeysConstants() {
  }

  @ConfigDef(cnName = "系统名称", showType = "3", cnValue = "XX平台", Desc = "自定义系统名称", sort = 10)
  public final static String SYSTEMPROFILES_TITLE = "systemprofiles_title";

  /**
   * 配置信息设置
   *
   * @return Map
   */
  public static Map<Object, Config> getConfigInstance() {
    Map<Object, Config> map = new HashMap<Object, Config>();
    Class<ConfigKeysConstants> classType = ConfigKeysConstants.class;
    Field[] fields = classType.getDeclaredFields();
    for (Field field : fields) {
      boolean hasAnnotation = field.isAnnotationPresent(ConfigDef.class);
      if (hasAnnotation) {
        ConfigDef annotation = field.getAnnotation(ConfigDef.class);
        Config config = new Config();
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
