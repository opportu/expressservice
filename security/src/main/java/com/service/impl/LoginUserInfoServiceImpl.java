package com.service.impl;

import com.domain.LoginUserInfo;
import com.mapper.LoginUserMapper;
import com.service.LoginUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginUserInfoServiceImpl implements LoginUserInfoService {

    @Autowired
    private LoginUserMapper loginUserMapper;

    /**
     * 用户登录接口
     * @param userInfo userInfo
     * @return
     */
    @Override
    public int userLogin(LoginUserInfo userInfo) {
        return loginUserMapper.insertUser(userInfo);
    }


    /**
     * 更新用户信息接口
     * @param userInfo userInfo
     * @return
     */
    @Override
    public int updateUserInfo(LoginUserInfo userInfo) {
        return loginUserMapper.updateUser(userInfo);
    }


    /**
     * 通过用户code获取用户信息
     * @param userCode userCode
     * @return
     */
    @Override
    public LoginUserInfo getUserInfoByUserCode(String userCode) {
        return loginUserMapper.selectByUserCode(userCode);
    }


    /**
     * 注销用户
     * @param userCode userCode
     * @return
     */
    @Override
    public int deleteUserInfo(String userCode) {
        return loginUserMapper.deleteUser(userCode);
    }

    /**
     * 手机号码获取所有用户信息
     * @param mobilePhoneNos mobilePhoneNos
     * @return
     */
    @Override
    public List<LoginUserInfo> getUserInfoByPhoneNos(List<String> mobilePhoneNos) {
        return loginUserMapper.getUserInfoByPhoneNos(mobilePhoneNos);
    }
}
