package com.service.impl;

import com.common.ErrorCode;
import com.domain.LoginUserInfo;
import com.exception.ServiceException;
import com.mapper.LoginUserMapper;
import com.service.RedisCacheService;
import com.service.UserCacheService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class UserCacheServiceImpl implements UserCacheService {

    @Autowired
    private LoginUserMapper loginUserMapper;

    @Autowired
    private RedisCacheService redisCacheService;

    private static final String MOBILE_USER_CODE_MAP_KEY_PREFIX = "AUTH_MOBILE2USER_CODE_KEY";
    private static final String USER_CODE_MAP_KEY = "AUTH_USER_CODE_KEY";
    private static final String LOGIN_SUCCESS_TIMESTAMP_MAP_KEY = "AUTH_LOGIN_SUCCESS_TIMESTAMP_KEY";
    private static final String SEPARATE = "_";

    @Override
    public LoginUserInfo getUser(String userCode) {
        LoginUserInfo userInfo = redisCacheService.getObject(getUserCode(userCode), LoginUserInfo.class);
        if (userInfo == null) {
            userInfo = loginUserMapper.selectByUserCode(userCode);
            if (userInfo == null) {
                throw new ServiceException(ErrorCode.USER_INFO_NOTFIND);
            } else {
                updateCache(userInfo);
            }
        }
        return enhanceLoginUserInfo(userInfo);
    }

    @Override
    public void addUser(LoginUserInfo userInfo) {
        loginUserMapper.insertUser(userInfo);
    }

    private void updateCache(LoginUserInfo userInfo) {
       if (StringUtils.isNotEmpty(userInfo.getMobilePhone())){
            redisCacheService.saveValue(getMobileUserCodeKey(userInfo), userInfo.getUserCode());
        } else {
            redisCacheService.delete(getMobileUserCodeKey(userInfo));
        }
        redisCacheService.saveObject(getUserCode(userInfo), userInfo);
    }

    @Override
    public LoginUserInfo getUserInfoByUserCode(String userCode) {
        return null;
    }

    @Override
    public LoginUserInfo getUserInfoByMobilePhoneNo(String mobilePhoneNo) {
        return null;
    }

    @Override
    public void deleteUser(String userCode) {

    }

    @Override
    public void updatePassword(String userCode, String password, String updateBy) {

    }

    @Override
    public void updateMobilePhoneNo(String userCode, String mobilePhoneNo, String updateBy) {

    }

    @Override
    public void updateActiveStatus(String userCode, boolean active, String updateBy) {

    }


    //私有方法区
    private LoginUserInfo enhanceLoginUserInfo(LoginUserInfo userInfo) {
        if (userInfo == null) {
            return null;
        } else {
            String userCode = userInfo.getUserCode();
            userInfo.setUpdated_at(redisCacheService.getObject(getLoginSuccessTimeStampKey(userCode), Date.class));
        }
        return userInfo;
    }

    private String getLoginSuccessTimeStampKey(String userCode) {
        return LOGIN_SUCCESS_TIMESTAMP_MAP_KEY + SEPARATE + userCode;
    }
    private String getUserCode(LoginUserInfo userInfo) {
        return this.getUserCode(userInfo.getUserCode());
    }

    private String getUserCode(String userCode) {
        return USER_CODE_MAP_KEY + SEPARATE + userCode;
    }

    private String getMobileUserCodeKey(LoginUserInfo userInfo) {
        return getMobileUserCodeKey(userInfo.getMobilePhone());
    }

    private String getMobileUserCodeKey(String mobilePhone) {
        return MOBILE_USER_CODE_MAP_KEY_PREFIX + SEPARATE + mobilePhone;
    }
}
