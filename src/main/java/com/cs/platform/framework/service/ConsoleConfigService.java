package com.cs.platform.framework.service;

import com.cs.platform.framework.constants.ConsoleConfigKeysConstants;
import com.cs.platform.framework.entity.ConsoleConfig;
import com.cs.platform.framework.repository.ConsoleConfigDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ConsoleConfigService {

    @Autowired
    private ConsoleConfigDao consoleConfigDao;

    protected static final Map<String, String> CONSOLE_CONFIG_MAP = new HashMap();

    /**
     * 根据配置项查询配置
     *
     * @return List<Config>
     */
    public List<ConsoleConfig> findConfig() {
        Map<Object, ConsoleConfig> map = ConsoleConfigKeysConstants.getConfigInstance();
        Iterator<Map.Entry<Object, ConsoleConfig>> iterator = map.entrySet().iterator();
        List<ConsoleConfig> lstConfig = new ArrayList<ConsoleConfig>();
        while (iterator.hasNext()) {
            Map.Entry<Object, ConsoleConfig> entry = iterator.next();
            if (null != entry.getKey()) {
                ConsoleConfig consoleConfig = consoleConfigDao.findOne(String.valueOf(entry.getKey()));
                if (null == consoleConfig) {
                    consoleConfig = entry.getValue();
                } else {
                    consoleConfig.setCnName(entry.getValue().getCnName());
                    consoleConfig.setShowType(entry.getValue().getShowType());
                    consoleConfig.setGroup(entry.getValue().getGroup());
                    consoleConfig.setRemarks(entry.getValue().getRemarks());
                }
                consoleConfig.setSort(entry.getValue().getSort());
                lstConfig.add(consoleConfig);
            }
        }
        Collections.sort(lstConfig, new Comparator<ConsoleConfig>() {
            @Override
            public int compare(ConsoleConfig o1, ConsoleConfig o2) {
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
    public void saveConsoleConfig(ConsoleConfig config) {
        String[] ckeys = config.getCkey().split(",");
        String[] values = config.getCval().split(",");
        String[] remarks = config.getRemarks().split(",");
        String[] showType = config.getShowType().split(",");
        List<ConsoleConfig> lstConfig = new ArrayList<ConsoleConfig>();
        for (int i = 0; i < ckeys.length; i++) {
            ConsoleConfig newConfig = consoleConfigDao.findOne(ckeys[i]);
            if (null == newConfig) {
                newConfig = new ConsoleConfig();
            }
            newConfig.setCkey(ckeys.length > i ? ckeys[i] : "");
            newConfig.setCval(values.length > i ? values[i] : "");
            newConfig.setRemarks(remarks.length > i ? remarks[i] : "");
            newConfig.setType(showType.length > i ? showType[i] : "0");
            lstConfig.add(newConfig);
        }
        consoleConfigDao.saveAll(lstConfig);
        CONSOLE_CONFIG_MAP.clear();
    }

    /**
     * 查询初始化参数
     *
     * @return
     */
    public synchronized Map<String, String> initConfig() {
        if (CONSOLE_CONFIG_MAP.isEmpty()) {
            List<ConsoleConfig> list = findConfig();
            for (ConsoleConfig config : list) {
                CONSOLE_CONFIG_MAP.put(config.getCkey(), config.getCval());
            }
        }
        return CONSOLE_CONFIG_MAP;
    }

    /**
     * 获取配置项的值
     *
     * @param ckey
     * @return
     */
    public String getStringValue(String ckey) {
        initConfig();
        return CONSOLE_CONFIG_MAP.get(ckey);
    }

    public int getIntValue(String ckey) {
        initConfig();
        return Integer.parseInt(CONSOLE_CONFIG_MAP.get(ckey));
    }

    public boolean getBooleanValue(String ckey) {
        initConfig();
        return Boolean.parseBoolean(CONSOLE_CONFIG_MAP.get(ckey));
    }

}
