package com.mapper;

import feign.Param;
import org.springframework.stereotype.Repository;

/**
 * 用户授权token映射数据库接口
 */
@Repository
public interface UserAuthTokenMapper {


    /**
     * 将token值插入到数据库中
     * @param platformKey platformKey
     * @param userCode userCode
     * @param token token
     * @return
     */
    int insertToken(@Param("platformKey") String platformKey, @Param("userCode") String userCode, @Param("token") String token);

    /**
     * 耍新token值
     * @param platformKey platformKey
     * @param userCode userCode
     * @param refreshToken refreshToken
     * @return
     */
    int updateRefreshToken(@Param("platformKey") String platformKey, @Param("userCode") String userCode, @Param("refreshToken") String refreshToken);

    /**
     * 获取刷新token值
     * @param platformKey platformKey
     * @param userCode userCode
     * @return
     */
    String selectRefreshTokenByUserCode(@Param("platformKey") String platformKey, @Param("userCode") String userCode);


    /**
     * 清空token值
     * @param userCode userCode
     * @return
     */
    int cleanRefreshToken(@Param("userCode") String userCode);

}
