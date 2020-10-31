package com.mapper;


import com.domain.LoginUserInfo;
import feign.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户登录授权映射数据库接口
 */
@Repository
public interface LoginUserMapper {


    /**
     * 插入用户登录信息
     * @param userInfo userInfo
     * @return
     */
    int insertUser(LoginUserInfo userInfo);

    /**
     * 更新用户登录信息
     * @param userInfo userInfo
     * @return
     */
    int updateUser(LoginUserInfo userInfo);


    /**
     * 通过userCode查询用户信息
     * @param userCode userCode
     * @return
     */
    LoginUserInfo selectByUserCode(String userCode);


    /**
     * 通过userCode获取所有用户信息列表
     * @param userCode userCode
     * @return
     */
    List<LoginUserInfo> selectByUserCodes(@Param("userCode") List<String> userCode);


    /**
     * 通过userCode删除用户登录信息
     * @param userCode userCode
     * @return
     */
    int deleteUser(@Param("userCode") String userCode);


    /**
     * 通过mobilePhoneNos获取用户登录信息
     * @param mobilePhoneNos mobilePhoneNos
     * @return
     */
    List<LoginUserInfo> getUserInfoByPhoneNos(@Param("mobilePhoneNos") List<String> mobilePhoneNos);





}
