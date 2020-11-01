package com.service;

public interface AccessTokenService {

    String createAccessTokenByUserCode(String platformCode, String userCode);

    String createRefreshToken(String userCode, String platformCode);

    boolean isRefreshTokenValid(String platformCode, String userCode, String refreshToken);

    boolean isTokenValid(String token, String secret, long timeStamp, String userCode);

    String isAccessTokenValid(String userCode, String platformCode, String token);
}
