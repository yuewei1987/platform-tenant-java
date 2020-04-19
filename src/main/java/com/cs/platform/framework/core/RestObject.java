package com.cs.platform.framework.core;

import java.io.Serializable;

/**
 * FIXME 类注释信息(此标记自动生成,注释填写完成后请删除)
 */
public class RestObject implements Serializable {

    /*
     * 返回码总体说明： ret = 0: 正确返回 ret > 0: 调用OpenAPI时发生错误，需要开发者进行相应的处理。 -50 <= ret
     * <= -1: 接口调用不能通过接口代理机校验，需要开发者进行相应的处理。 ret <-50:
     * 系统内部错误，请通过企业QQ联系技术支持，调查问题原因并获得解决方案。
     */

    /**
     * 状态码成功
     */
    public final static int STATUS_CODE_SUCCESS = 0;
    /**
     * 状态调用失败
     */
    public final static int STATUS_CODE_FAILURE = 300;
    /**
     * 状态码超时
     */
    public final static int STATUS_CODE_TIMEOUT = 301;
    /**
     * 时间戳错误超时
     */
    public final static int STATUS_INVALID_TIMESTAMP = 303;
    /**
     * 状态码禁止访问
     */
    public final static int STATUS_CODE_FORBIDDEN = 403;
    /**
     * 状态码未找到
     */
    public final static int STATUS_CODE_NOTFOUND = 404;
    /**
     * 状态码服务器端错误
     */
    public final static int STATUS_CODE_SERVEERROR = 500;
    /****** 未知帐号错误！ ******/
    public final static int STATUS_LOGIN_UNKNOWNACCOUNT = 1000;
    /****** 密码错误！ ******/
    public final static int STATUS_LOGIN_INCORRECTCREDENTIALS = 1001;
    /****** 客户端id未指定错误！ ******/
    public final static int STATUS_LOGIN_CLIENTIDNOTFOUND = 1002;
    /****** 验证码错误！ ******/
    public final static int STATUS_LOGIN_CAPTCHA = 1003;
    /****** 认证失败！ ******/
    public final static int STATUS_LOGIN_AUTHENTICATION = 1004;
    /****** 账号被冻结！ ******/
    public final static int STATUS_LOGIN_DISABLEDACCOUNT = 1005;
    /****** 强制升级！ ******/
    public final static int STATUS_FORCE_UPDATE = 1006;
    /****** 设备未进行接入登记！ ******/
    public final static int STATUS_DEVICE_NOT_REGISTER = 1007;
    /**
     * 不允许ROOT设备登录
     */
    public final static int STATUS_NOTALLOW_ROOT_DEVICE_LOGIN = 1011;
    /**
     * 4a认证异常
     */
    public final static int STATUS_LOGIN_4A = 1012;
    /****** 设备已登记，未进行审核！ ******/
    public final static int STATUS_DEVICE_NOT_AUDIT = 1008;
    /****** 账号未激活 ******/
    public final static int STATUS_ACCOUNT_NOT_ACTIVE = 1009;
    /****** 公有云登录未配置域 ******/
    public final static int STATUS_NO_DOMAIN_IN_PUBCLOUD = 1010;
    /**
     * FIXME
     */
    private static final long serialVersionUID = -7771374323162062273L;
    /**
     * 返回状态
     */
    private int state = STATUS_CODE_SUCCESS;

    /**
     * 消息
     */
    private String message = "";

    /**
     * 返回数据
     */
    private Object data = "";

    /**
     * 构造函数
     */
    public RestObject() {
    }

    /**
     * 构造函数
     *
     * @param state state
     */
    public RestObject(int state) {
        this.state = state;
    }

    /**
     * 构造函数
     *
     * @param state   state
     * @param message message
     */
    public RestObject(int state, String message) {
        this.state = state;
        this.message = message;
    }

    /**
     * 构造函数
     *
     * @param message message
     * @param state   state
     * @param data    data
     */
    public RestObject(int state, String message, Object data) {
        this.state = state;
        this.message = message;
        this.data = (null == data) ? "" : data;
    }

    /**
     * 生成成功对象
     *
     * @param message message
     * @return obj
     */
    public static RestObject newOk(String message) {
        return new RestObject(STATUS_CODE_SUCCESS, message);
    }

    /**
     * 生成成功对象
     *
     * @param message message
     * @param data    data
     * @return obj
     */
    public static RestObject newOk(String message, Object data) {
        return new RestObject(STATUS_CODE_SUCCESS, message, data);
    }

    /**
     * 生成错误对象
     *
     * @param message message
     * @return obj
     */
    public static RestObject newError(String message) {
        return new RestObject(STATUS_CODE_FAILURE, message);
    }

    /**
     * 生成错误对象
     *
     * @param message message
     * @param data    data
     * @return obj
     */
    public static RestObject newError(String message, Object data) {
        return new RestObject(STATUS_CODE_FAILURE, message, data);
    }

    /**
     * 生成超时对象
     *
     * @param message message
     * @return obj
     */
    public static RestObject newTimeout(String message) {
        return new RestObject(STATUS_CODE_TIMEOUT, message);
    }

    /**
     * 生成超时对象
     *
     * @param message message
     * @param data    data
     * @return obj
     */
    public static RestObject newTimeout(String message, Object data) {
        return new RestObject(STATUS_CODE_TIMEOUT, message, data);
    }

    /**
     * 生成禁止访问对象
     *
     * @param message message
     * @return obj
     */
    public static RestObject newForbidden(String message) {
        return new RestObject(STATUS_CODE_TIMEOUT, message);
    }

    /**
     * 生成禁止访问对象
     *
     * @param message message
     * @param data    data
     * @return obj
     */
    public static RestObject newForbidden(String message, Object data) {
        return new RestObject(STATUS_CODE_TIMEOUT, message, data);
    }

    /**
     * 生成访问对象
     *
     * @param state   state
     * @param message message
     * @param data    data
     * @return obj
     */
    public static RestObject newRestObject(int state, String message, Object data) {
        return new RestObject(state, message, data);
    }

    /**
     * 生成访问对象
     *
     * @param state   state
     * @param message message
     * @return obj
     */
    public static RestObject newRestObject(int state, String message) {
        return new RestObject(state, message);
    }

    /**
     * @return state
     */
    public int getState() {
        return state;
    }

    /**
     * @param state 要设置的 state
     */
    public void setState(int state) {
        this.state = state;
    }

    /**
     * @return message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message 要设置的 message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return data
     */
    public Object getData() {
        return data;
    }

    /**
     * @param data 要设置的 data
     */
    public void setData(Object data) {
        this.data = data;
    }

}
