package com.service.impl;

import com.common.ErrorCode;
import com.domain.LoginUserInfo;
import com.domain.PasswordModifyRecord;
import com.exception.ServiceException;
import com.service.AuthUserService;
import com.service.PasswordModifyRecordService;
import com.service.PasswordService;
import com.service.UserCacheService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthUserServiceImpl implements AuthUserService {


    private static Logger logger = LoggerFactory.getLogger(AuthUserServiceImpl.class);

    @Autowired
    private PasswordService passwordService;
    @Autowired
    private UserCacheService userCacheService;
    @Autowired
    private PasswordModifyRecordService passwordModifyRecordService;


    @Override
    public void activateUser(String userCode, String password, String updateBy) {
        logger.info("【激活用户信息】 Activating User {} by {}.", userCode, updateBy);
        userCacheService.activateAndSetPassword(userCode, password, updateBy);
        logger.info("【激活用户信息】 Activated User {} by {}.", userCode, updateBy);
    }

    @Override
    public void deActivateUser(String userCode, String updateBy) {
        logger.info("【取消激活用户信息】 deActivating User {} by {}.", userCode, updateBy);
        userCacheService.updateActiveStatus(userCode, false, updateBy);
        logger.info("【取消激活用户信息】 deActivating User {} by {}.", userCode, updateBy);
    }

    @Override
    public void modifyMobilePhone(String userCode, String newMobilePhoneNo, String updateBy) {
        logger.info("【更新用户电话号码】 modify User {} mobilePhone {}.", userCode, newMobilePhoneNo);
        userCacheService.updateMobilePhoneNo(userCode, newMobilePhoneNo, updateBy);
        logger.info("【更新用户电话号码】 modify User {} mobilePhone {} by user {} complete.",userCode, newMobilePhoneNo, updateBy);
    }

    @Override
    public void deleteUser(String userCode, String updateBy) {
        logger.info("【删除用户信息】 delete User {} by {}.", userCode, updateBy);
        userCacheService.deleteUser(userCode);
        logger.info("【删除用户信息】 delete User {} by {} complete.", userCode, updateBy);

    }

    @Override
    public LoginUserInfo getUserInfoByUserCode(String userCode) {
        return userCacheService.getUserInfoByUserCode(userCode);
    }

    @Override
    public LoginUserInfo getUserInfoByMobilePhone(String mobilePhoneNo) {
        return userCacheService.getUserInfoByMobilePhoneNo(mobilePhoneNo);
    }

    @Override
    public LoginUserInfo registerUser(LoginUserInfo userInfo, String operator, boolean admitSet) {
        validateLoginAccount(userInfo.getMobilePhone());
        if (StringUtils.isNotBlank(userInfo.getPassword())) {
            userInfo.setPassword(passwordService.encryptPassword(userInfo.getPassword()));
        }
        createUser(userInfo, operator);
        passwordModifyRecordService.savePasswordModifyRecord(
                new PasswordModifyRecord(userInfo.getUserCode(), userInfo.getPassword(), operator, admitSet));
        return userInfo;
    }


    //私有方法区
    private void validateLoginAccount(String mobilePhone) {
        if (StringUtils.isBlank(mobilePhone)) {
            logger.warn("Validation failed, the mobile phone number is empty.");
            throw new ServiceException(ErrorCode.MOBILE_PHONE_INVALID);
        } else {
            if (!StringUtils.isNumeric(mobilePhone)) {
                logger.error("Validation failed, hte mobile phone number is invalid.");
            }
        }
    }

    private void createUser(LoginUserInfo userInfo, String operator) {
        logger.info("create User {} by {}", userInfo, operator);
        if (StringUtils.isNotBlank(userInfo.getMobilePhone())) {
            LoginUserInfo userInfoInDb = userCacheService.getUserInfoByMobilePhoneNo(userInfo.getMobilePhone());
            if (userInfoInDb != null) {
                logger.warn("Mobile phone number {} is used by user {}.", userInfo.getMobilePhone(), userInfo.getUserCode());
                throw new ServiceException(ErrorCode.MOBILE_PHONE_USED_BY_OTHERS, userInfoInDb.getUserCode());
            }
        }
        userInfo.setCreated_by(operator);
        userInfo.setUpdated_by(operator);
        //gRPC
//        userInfo.setUserCode("UI" + );
        userCacheService.addUser(userInfo);
        logger.info("User {} created.", userInfo.getUserCode());
    }
}
