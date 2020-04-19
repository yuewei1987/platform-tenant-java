package com.cs.platform.framework.persistence;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.Map.Entry;

/**
 * 查询过滤器
 *
 * @author calvin
 */
public class SearchFilter {

    /**
     * 查询字段名
     */
    public String fieldName;
    /**
     * 值
     */
    public Object value;
    /**
     * 操作类型枚举值
     */
    public Operator operator;

    /**
     * 构造函数
     *
     * @param fieldName 查询字段名
     * @param operator  操作类型枚举值
     * @param value     值
     */
    public SearchFilter(String fieldName, Operator operator, Object value) {
        this.fieldName = fieldName;
        this.value = value;
        this.operator = operator;
    }

    /**
     * searchParams中key的格式为OPERATOR_FIELDNAME
     *
     * @param searchParams 查询字符串
     * @return Map<String               ,                               SearchFilter>
     */
    public static Map<String, SearchFilter> parse(Map<String, Object> searchParams) {
        Map<String, SearchFilter> filters = Maps.newHashMap();
        for (Entry<String, Object> entry : searchParams.entrySet()) {
            // 过滤掉空值
            String key = entry.getKey();
            Object value = entry.getValue();
            if (StringUtils.isBlank((String) value)) {
                continue;
            }
            if (value instanceof String) {
                value = escapeSql(value.toString());
            }
            // 拆分operator与filedAttribute
            String[] names = StringUtils.split(key, "_");
            if (names.length != 2) {
                throw new IllegalArgumentException(key + " is not a valid search filter name");
            }
            String filedName = names[1];
            Operator operator = Operator.valueOf(names[0]);
            // 创建searchFilter
            SearchFilter filter = new SearchFilter(filedName, operator, value);
            filters.put(key, filter);
        }
        return filters;
    }

    /**
     * @param src x
     * @return String
     */
    private static String escapeSql(String src) {
        if (src == null) {
            return "";
        }
        String _src = src;
        _src = _src.replaceAll("'", "\'");
        _src = _src.replaceAll("\\\\", "\\\\\\\\");
        // _src = _src.replaceAll("+", "\\+");
        _src = _src.replaceAll("-", "\\-");
        _src = _src.replaceAll("_", "\\\\_");
        _src = _src.replaceAll("%", "\\\\%");
        return _src;
    }

    /**
     * 查询操作枚举
     */
    public enum Operator {
        /**
         * 等于
         */
        EQ,
        /**
         * 不等于
         */
        NEQ,
        /**
         * 模糊匹配
         */
        LIKE,
        /**
         * 左侧模糊匹配
         */
        LLIKE,
        /**
         * 右侧模糊匹配
         */
        RLIKE,
        /**
         * 大于
         */
        GT,
        /**
         * 小于
         */
        LT,
        /**
         * 大于等于
         */
        GTE,
        /**
         * 小于 等于
         */
        LTE,
        /**
         * 不等于空
         */
        NOTNULL
    }
}
