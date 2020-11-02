package com.service.impl;

import com.common.ErrorCode;
import com.domain.LoginUserInfo;
import com.exception.ServiceException;
import com.service.AccessTokenService;
import com.service.AuthService;
import com.service.AuthUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.credential.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;


@Slf4j
public class AuthServiceImpl implements AuthService {


    @Autowired
    private AccessTokenService accessTokenService;

    @Autowired
    private AuthUserService authUserService;

    @Autowired
    private PasswordService passwordService;

    /**
     * 更新refreshToken
     *
     * @param userCode     userCode
     * @param refreshToken refreshToken
     * @param platformCode platformCode
     * @return
     */
    @Override
    public String renewRefreshToken(String userCode, String refreshToken, String platformCode) {
        if (accessTokenService.isRefreshTokenValid(platformCode, userCode, refreshToken)) {
            log.info("User {} renew refreshToken for {}.", userCode, platformCode);
            LoginUserInfo userInfo = authUserService.getUserInfoByUserCode(userCode);
            validateUser(userInfo);
            return accessTokenService.createRefreshToken(userCode, platformCode);
        } else {
            log.warn("User {} renew refreshToken for {}.", userCode, platformCode);
            throw new ServiceException(ErrorCode.TOKEN_INVALID, "Refresh Token Invalid.");
        }
    }

    /**
     * 验证密码
     *
     * @param userCode userCode
     * @param password password
     */
    @Override
    public boolean validatePassword(String userCode, String password) {
        LoginUserInfo userInfo = authUserService.getUserInfoByUserCode(userCode);
        if (StringUtils.isEmpty(userInfo)) {
            throw new ServiceException(ErrorCode.USER_INFO_NOTFIND);
        }

        return passwordService.passwordsMatch(password, userInfo.getUserCode());

    }


    private void validateUser(LoginUserInfo userInfo) {
        if (StringUtils.isEmpty(userInfo)) {
            log.warn("UserInfo doesn't exist.");
            throw new ServiceException(ErrorCode.USER_INFO_NOTFIND);
        }
    }
}
