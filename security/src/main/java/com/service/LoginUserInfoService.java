package com.service;

import com.domain.LoginUserInfo;

import java.util.List;

public interface LoginUserInfoService {

    int userLogin(LoginUserInfo userInfo);

    int updateUserInfo(LoginUserInfo userInfo);

    LoginUserInfo getUserInfoByUserCode(String userCode);

    int deleteUserInfo(String userCode);

    List<LoginUserInfo> getUserInfoByPhoneNos(List<String> mobilePhoneNos);


}
