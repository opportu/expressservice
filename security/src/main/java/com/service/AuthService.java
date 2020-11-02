package com.service;

public interface AuthService {


    /**
     * 更新refreshToken
     *
     * @param userCode     userCode
     * @param refreshToken refreshToken
     * @param platformCode platformCode
     * @return
     */
    String renewRefreshToken(String userCode, String refreshToken, String platformCode);


    /**
     * 验证密码
     *
     * @param userCode userCode
     * @param password password
     */
   boolean validatePassword(String userCode, String password);
}
