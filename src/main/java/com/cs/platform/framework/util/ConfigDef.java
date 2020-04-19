package com.cs.platform.framework.util;

import java.lang.annotation.*;

/**
 * 注解定义
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface ConfigDef {

    /**
     * 描述
     */
    public String Desc() default "";

    /**
     * 是否显示
     */
    public boolean isDisplay() default true;

    /**
     * 中文名称
     */
    public String cnName();

    /**
     * 配置项的值
     */
    public String cnValue() default "";

    /**
     * 展示形式
     */
    public String showType() default "text";

    /**
     * 排序
     */
    public int sort() default 0;

    /**
     * 分组
     */
    public String group() default "basic";
}
