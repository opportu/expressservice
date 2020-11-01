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
    @Override
    public int userLogin(LoginUserInfo userInfo) {
        return loginUserMapper.insertUser(userInfo);
    }

    @Override
    public int updateUserInfo(LoginUserInfo userInfo) {
        return loginUserMapper.updateUser(userInfo);
    }

    @Override
    public LoginUserInfo getUserInfoByUserCode(String userCode) {
        return loginUserMapper.selectByUserCode(userCode);
    }

    @Override
    public int deleteUserInfo(String userCode) {
        return loginUserMapper.deleteUser(userCode);
    }

    @Override
    public List<LoginUserInfo> getUserInfoByPhoneNos(List<String> mobilePhoneNos) {
        return loginUserMapper.getUserInfoByPhoneNos(mobilePhoneNos);
    }
}
