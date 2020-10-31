package com.common;

/**
 * 快递帮常用参数配置
 */
public enum CommonParams {

//    public static final String SESSION_KEY = "USER_CODE";

    SESSION_KEY(1000, "USER_CODE"),
    SUCCESS(1001, "SUCCESS"),
    FAIL(1002, "FAIL");

    private int code;
    private String description;

    CommonParams(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }



}
