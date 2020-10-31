package com.domain;


import lombok.Data;

/**
 * 用户授权信息表
 */
@Data
public class AuthUserInfo {

    private String accessToken;

    private String refreshToken;

    private String platformToken;

    private LoginUserInfo userInfo;

}
