package com.service;

import com.domain.LoginUserInfo;

import java.util.List;

/**
 * 用户dao层
 */
public interface LoginUserInfoService {

    /**
     * 用户登录接口
     * @param userInfo userInfo
     * @return
     */
    int userLogin(LoginUserInfo userInfo);


    /**
     * 更新用户信息接口
     * @param userInfo userInfo
     * @return
     */
    int updateUserInfo(LoginUserInfo userInfo);


    /**
     * 通过用户code获取用户信息
     * @param userCode userCode
     * @return
     */
    LoginUserInfo getUserInfoByUserCode(String userCode);


    /**
     * 注销用户
     * @param userCode userCode
     * @return
     */
    int deleteUserInfo(String userCode);


    /**
     * 手机号码获取所有用户信息
     * @param mobilePhoneNos mobilePhoneNos
     * @return
     */
    List<LoginUserInfo> getUserInfoByPhoneNos(List<String> mobilePhoneNos);


}
