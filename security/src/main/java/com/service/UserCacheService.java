package com.service;

import com.domain.LoginUserInfo;

public interface UserCacheService {

    LoginUserInfo getUser(String userCode);

    void addUser(LoginUserInfo userInfo);

    LoginUserInfo getUserInfoByUserCode(String userCode);

    LoginUserInfo getUserInfoByMobilePhoneNo(String mobilePhoneNo);

    void deleteUser(String userCode);

    void updatePassword(String userCode, String password, String updateBy);

    void updateMobilePhoneNo(String userCode, String mobilePhoneNo, String updateBy);

    void updateActiveStatus(String userCode, boolean active, String updateBy);

    void activateAndSetPassword(String userCode, String password, String updateBy);


}
