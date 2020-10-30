package com.domain;


import lombok.Data;

import java.util.Date;

/**
 * 用户登录信息类
 */
@Data
public class LoginUserInfo {

    private String userCode;

    private String password;

    private String loginName;

    private String mobilePhone;

    private boolean active;

    private Date created_at;

    private String created_by;

    private Date updated_at;

    private String updated_by;

    @Override
    public String toString() {
        return "LoginUserInfo => {" +
                "userCode='" + userCode + '\'' +
                ", password='" + password + '\'' +
                ", loginName='" + loginName + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", active=" + active +
                '}';
    }
}
