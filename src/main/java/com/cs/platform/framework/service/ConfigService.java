package com.cs.platform.framework.service;

import com.cs.platform.framework.constants.ConfigKeysConstants;
import com.cs.platform.framework.core.UserUtils;
import com.cs.platform.framework.entity.Config;
import com.cs.platform.framework.entity.Tenant;
import com.cs.platform.framework.repository.ConfigDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ConfigService {

    @Autowired
    private ConfigDao configDao;

    protected static final Map<String, String> CONFIG_MAP = new HashMap();

    /**
     * 根据配置项查询配置
     *
     * @return List<Config>
     */
    public List<Config> findConfig() {
        Map<Object, Config> map = ConfigKeysConstants.getConfigInstance();
        Iterator<Map.Entry<Object, Config>> iterator = map.entrySet().iterator();
        List<Config> lstConfig = new ArrayList<Config>();
        while (iterator.hasNext()) {
            Map.Entry<Object, Config> entry = iterator.next();
            if (null != entry.getKey()) {
//                Config config = configDao.findById(String.valueOf(entry.getKey())).orElse(null);
                Config config = configDao.findOne(String.valueOf(entry.getKey()), UserUtils.getTenantId());
                if (null == config) {
                    config = entry.getValue();
                } else {
                    config.setCnName(entry.getValue().getCnName());
                    config.setShowType(entry.getValue().getShowType());
                    config.setGroup(entry.getValue().getGroup());
                    config.setRemarks(entry.getValue().getRemarks());
                }
                config.setSort(entry.getValue().getSort());
                lstConfig.add(config);
            }
        }
        Collections.sort(lstConfig, new Comparator<Config>() {
            @Override
            public int compare(Config o1, Config o2) {
                return o1.getSort() - o2.getSort();
            }
        });
        return lstConfig;
    }

    /**
     * 保存配置信息
     *
     * @param config config
     */
    public void saveSysconfig(Config config) {
        String[] ckeys = config.getCkey().split(",");
        String[] values = config.getCval().split(",");
        String[] remarks = config.getRemarks().split(",");
        String[] showType = config.getShowType().split(",");
        List<Config> lstConfig = new ArrayList<Config>();
        for (int i = 0; i < ckeys.length; i++) {
            Config newConfig = configDao.findOne(ckeys[i], UserUtils.getTenantId());
            if (null == newConfig) {
                newConfig = new Config();
            }
            newConfig.setTenant(new Tenant(UserUtils.getTenantId()));
            newConfig.setCkey(ckeys.length > i ? ckeys[i] : "");
            newConfig.setCval(values.length > i ? values[i] : "");
            newConfig.setRemarks(remarks.length > i ? remarks[i] : "");
            newConfig.setType(showType.length > i ? showType[i] : "0");
            lstConfig.add(newConfig);
        }
        configDao.saveAll(lstConfig);
        CONFIG_MAP.clear();
    }

    /**
     * 查询初始化参数
     *
     * @return
     */
    public synchronized Map<String, String> initConfig() {
        if (CONFIG_MAP.isEmpty()) {
            List<Config> list = findConfig();
            for (Config config : list) {
                if (config.getTenant() == null) {
                    continue;
                }
                CONFIG_MAP.put(config.getCkey() + "_" + config.getTenant().getTenantId(), config.getCval());
            }
        }
        return CONFIG_MAP;
    }

    /**
     * 获取配置项的值
     *
     * @param ckey
     * @return
     */
    public String getStringValue(String ckey) {
        initConfig();
        return CONFIG_MAP.get(ckey + "_" + UserUtils.getTenantId());
    }

    public int getIntValue(String ckey) {
        initConfig();
        return Integer.parseInt(CONFIG_MAP.get(ckey + "_" + UserUtils.getTenantId()));
    }

    public boolean getBooleanValue(String ckey) {
        initConfig();
        return Boolean.parseBoolean(CONFIG_MAP.get(ckey + "_" + UserUtils.getTenantId()));
    }

}
