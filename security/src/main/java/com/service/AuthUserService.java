package com.service;

import com.domain.LoginUserInfo;

public interface AuthUserService {


    /**
     * 用户授权登录
     * @param userCode userCode
     * @param password password
     * @param updateBy updateBy
     */
    void activateUser(String userCode, String password, String updateBy);

    /**
     * 取消用户授权，可以判断标志位 active
     * @param userCode userCode
     * @param updateBy updateBy
     */
    void deActivateUser(String userCode, String updateBy);

    /**
     * 更改手机号码
     * @param userCode userCode
     * @param newMobilePhoneNo newMobilePhoneNo
     * @param updateBy updateBy
     */
    void modifyMobilePhone(String userCode, String newMobilePhoneNo, String updateBy);

    /**
     * 注销用户
     * @param userCode userCode
     * @param updateBy updateBy
     */
    void deleteUser(String userCode, String updateBy);


    /**
     * 用户code获取到用户信息
     * @param userCode
     * @return
     */
    LoginUserInfo getUserInfoByUserCode(String userCode);

    LoginUserInfo getUserInfoByMobilePhone(String mobilePhoneNo);

    LoginUserInfo registerUser(LoginUserInfo userInfo, String operator, boolean admitSet);


}
