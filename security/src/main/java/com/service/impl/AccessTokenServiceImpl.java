package com.service.impl;

import com.constant.TimeRange;
import com.service.AccessTokenService;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;
import org.apache.commons.codec.digest.DigestUtils;
import org.bouncycastle.util.encoders.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;

public class AccessTokenServiceImpl implements AccessTokenService {


    /**
     * 日志记录
     */
    private static Logger logger = LoggerFactory.getLogger(AccessTokenServiceImpl.class);

    private byte[] jwtRefreshTokenSecretByte;

    @Value("${jwt.id}")
    private String jwtId;

    @Value("${jwt.access.token.secret}")
    private String jwtAccessTokenSecret;

    @Value("${jwt.refresh.secret}")
    public void setJwtRefreshTokenSecret(String jwtRefreshTokenSecret) {
        this.jwtRefreshTokenSecretByte = Base64.decode(jwtRefreshTokenSecret);
    }


    /**
     * 生成token方法
     *
     * @param userCode     userCode
     * @param platformCode platformCode
     * @return 返回token
     */
    private String createAccessToken(String userCode, String platformCode) {
        byte[] accessSecretByte = Base64.decode(jwtAccessTokenSecret);
        SecretKey key = new SecretKeySpec(accessSecretByte, 0, accessSecretByte.length, "AES");
        DefaultClaims defaultClaims = createDefaultClaims(userCode, platformCode);
        defaultClaims.put("userCode", userCode);
        defaultClaims.put("platformCode", platformCode);
        JwtBuilder jwtBuilder = Jwts.builder().setClaims(defaultClaims).signWith(SignatureAlgorithm.HS256, key);
        return jwtBuilder.compact();
    }


    /**
     * 获取到注册中的必要声明
     *
     * @param userCode     userCode
     * @param platformCode platformCode
     * @return
     */
    private DefaultClaims createDefaultClaims(String userCode, String platformCode) {
        DefaultClaims defaultClaims = new DefaultClaims();
        long current = System.currentTimeMillis();
        long expirationMills = current + TimeRange.TEN_MINUTES_MILLS;
        //关键代码
        defaultClaims.setId(this.jwtId).setSubject(userCode).setIssuedAt(new Date(current)).setAudience(platformCode)
                .setExpiration(new Date(expirationMills));
        return defaultClaims;
    }


    /**
     * 生成token值
     * @param platformCode platformCode
     * @param userCode userCode
     * @return
     */
    @Override
    public String createAccessTokenByUserCode(String platformCode, String userCode) {
        return createAccessToken(userCode, platformCode);
    }

    @Override
    public String createRefreshToken(String userCode, String platformCode) {
        long currentTime = System.currentTimeMillis();
        SecretKey key = new SecretKeySpec(jwtRefreshTokenSecretByte, 0, jwtRefreshTokenSecretByte.length, "AES");
        DefaultClaims defaultClaims = new DefaultClaims();
        defaultClaims.setId(this.jwtId).setSubject(userCode).setAudience(platformCode).setIssuedAt(new Date(currentTime))
                .setExpiration(new Date(currentTime + TimeRange.ONE_DAY_MILLS));
        JwtBuilder jwtBuilder = Jwts.builder().setClaims(defaultClaims).signWith(SignatureAlgorithm.HS256, key);
        return jwtBuilder.compact();
    }

    @Override
    public boolean isRefreshTokenValid(String platformCode, String userCode, String refreshToken) {
        return false;
    }

    @Override
    public boolean isTokenValid(String token, String secret, long timeStamp, String userCode) {
       String encrypted = DigestUtils.sha256Hex(secret + ":" + token + ":" + timeStamp);
       if (!encrypted.equals(token)) {
           logger.error("User {} tries to login in with a invalid token {}.", userCode, token);
           return false;
       } else {
           return true;
       }
    }

    @Override
    public String isAccessTokenValid(String userCode, String platformCode, String token) {
        return null;
    }
}
