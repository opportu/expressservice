package com.common;
/**
 * restful请求的返回结果
 */

import java.io.Serializable;

public class Result implements Serializable {

    /**
     * 不存在
     */
    private static final int NOT_EXIST = 100;

    /**
     * 重复
     */
    private static final int IEPRAT = 200;

    /**
     * 错误
     */
    private static final int ERROR = 300;

    /**
     * 失效
     */
    private static final int INEFFECTIVE = 400;

    /**
     * 不匹配
     */
    private static final int MISMATCH = 500;

    /**
     * 操作成功
     */
    private static final int OPTION_SUCCESS = 222;

    /**
     * 返回结果状态码
     */
    private int code;

    /**
     * 返回值
     */
    private Object data;

    /**
     * 返回结果说明
     */
    private String message;

    /**
     * 是否成功
     */
    private boolean isSuccess;


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(boolean success) {
        isSuccess = success;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", data=" + data +
                ", message='" + message + '\'' +
                ", isSuccess=" + isSuccess +
                '}';
    }
}
