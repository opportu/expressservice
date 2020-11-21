package com.service.impl;

import com.common.ErrorCode;
import com.domain.LoginUserInfo;
import com.exception.ServiceException;
import com.mapper.LoginUserMapper;
import com.service.RedisCacheService;
import com.service.UserCacheService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
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
        log.info("【从缓存中获取用户信息】 => 开始。。。");
        long startTime = System.currentTimeMillis();
        LoginUserInfo userInfo = redisCacheService.getObject(getUserCode(userCode), LoginUserInfo.class);
        log.info("【从缓存中获取用户信息】 => 结束。 花费时间为：{}.", System.currentTimeMillis() - startTime);
        if (userInfo == null) {
            startTime = System.currentTimeMillis();
            log.info("【从数据库中获取用户信息】 => 开始。。。");
            userInfo = loginUserMapper.selectByUserCode(userCode);
            log.info("【从数据库中获取用户信息】 => 结束。 花费时间为：{}.", System.currentTimeMillis() - startTime);
            if (userInfo == null) {
                log.warn("【数据库中没有用户信息】");
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

    /**
     * 更新用户信息
     * @param userInfo userInfo
     */
    private void updateCache(LoginUserInfo userInfo) {
       if (StringUtils.isNotEmpty(userInfo.getMobilePhone())){
           log.info("【在缓存中更新用户信息】 => {}.", userInfo.getUserCode());
            redisCacheService.saveValue(getMobileUserCodeKey(userInfo), userInfo.getUserCode());
        } else {
           log.info("【在缓存中删除用户信息】 => {}.", userInfo.getUserCode());
            redisCacheService.delete(getMobileUserCodeKey(userInfo));
        }
        redisCacheService.saveObject(getUserCode(userInfo), userInfo);
    }

    @Override
    public LoginUserInfo getUserInfoByUserCode(String userCode) {
        if (StringUtils.isNotEmpty(userCode)) {
            log.info("【在缓存中获取用户信息】 => {}.", userCode);
            LoginUserInfo userInfo = redisCacheService.getObject(getUserCode(userCode), LoginUserInfo.class);
            if (userInfo != null) {
                return userInfo;
            } else {
                log.info("【在数据库中获取用户信息】=> {}.", userCode);
                userInfo = loginUserMapper.selectByUserCode(userCode);
            }
            updateCache(userInfo);
            return userInfo;
        }
        return null;
    }

    @Override
    public LoginUserInfo getUserInfoByMobilePhoneNo(String mobilePhoneNo) {
        if (StringUtils.isNotEmpty(mobilePhoneNo)) {
            log.info("【在缓存中通过手机号码获取用户信息】 => {}.", mobilePhoneNo);
            LoginUserInfo userInfo = redisCacheService.getObject(getMobileUserCodeKey(mobilePhoneNo), LoginUserInfo.class);
            if (userInfo != null) {
                return userInfo;
            } else {
                log.info("【在数据库中通过手机号码获取用户信息】=> {}.", mobilePhoneNo);
                userInfo = loginUserMapper.selectByUserCode(mobilePhoneNo);
            }
            if (userInfo == null) {
                return null;
            }
            updateCache(userInfo);
            return userInfo;
        }
        return null;
    }

    @Override
    public void deleteUser(String userCode) {
        if (StringUtils.isNotEmpty(userCode)) {
            log.info("【在缓存中删除用户信息】 => {}.", userCode);
            redisCacheService.delete(getUserCode(userCode));
        }
    }

    @Override
    public void updatePassword(String userCode, String password, String updateBy) {
        if (StringUtils.isNotEmpty(userCode)) {
            log.info("【在数据库中更新用户信息】 => {}.", userCode);
            LoginUserInfo userInfo = new LoginUserInfo();
            userInfo.setUserCode(userCode);
            userInfo.setPassword(password);
            userInfo.setUpdated_by(updateBy);
            loginUserMapper.updateUser(userInfo);
            log.info("【在数据库中更新用户信息】 finish。");
        }
    }

    @Override
    public void updateMobilePhoneNo(String userCode, String mobilePhoneNo, String updateBy) {
        if (StringUtils.isEmpty(userCode)) {
            log.info("userCode = {} is empty.", userCode);
        }
        if (StringUtils.isEmpty(mobilePhoneNo)) {
            log.info("mobilePhoneNo = {} is empty.", mobilePhoneNo);
        }
        LoginUserInfo userInfo = new LoginUserInfo();
        userInfo.setUserCode(userCode);
        userInfo.setMobilePhone(mobilePhoneNo);
        userInfo.setUpdated_by(updateBy);
        loginUserMapper.updateUser(userInfo);
        log.info("【在数据库中更新用户信息】 finish。");
    }

    @Override
    public void updateActiveStatus(String userCode, boolean active, String updateBy) {
        if (StringUtils.isEmpty(userCode)) {
            log.info("userCode = {} is empty.", userCode);
        }
        LoginUserInfo userInfo = new LoginUserInfo();
        userInfo.setUserCode(userCode);
        userInfo.setActive(active);
        userInfo.setUpdated_by(updateBy);
        loginUserMapper.updateUser(userInfo);
        log.info("【在数据库中更新用户信息】 finish。");
    }

    @Override
    public void activateAndSetPassword(String userCode, String password, String updateBy) {
        LoginUserInfo userInfo = this.getUser(userCode);
        if (userInfo.isActive() && StringUtils.equals(userInfo.getPassword(), password)) {
            log.info("【更改用户密码】 = > 用户密码与原密码相同。{}", userInfo.getUserCode());
            return;
        }
        userInfo.setActive(true);
        userInfo.setPassword(password);
        userInfo.setUpdated_by(updateBy);
        updateCache(userInfo);
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
