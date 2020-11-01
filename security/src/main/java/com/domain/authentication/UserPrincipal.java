package com.domain.authentication;

import java.security.Principal;

public class UserPrincipal implements Principal {

    private final String userCode;

    private final String platformKey;

    public UserPrincipal(String userCode, String platformKey) {
        this.userCode = userCode;
        this.platformKey = platformKey;
    }

    public String getUserCode() {
        return userCode;
    }

    public String getPlatformKey() {
        return platformKey;
    }
    @Override
    public String getName() {
        return null;
    }
}
