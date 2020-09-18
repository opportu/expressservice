package com.config;

import com.netflix.discovery.converters.Auto;
import com.service.RedisCacheService;
import com.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class MyRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    
    @Autowired
    private RedisCacheService redisService;

    @Autowired
    public MyRealm() {
        super();
    }

    public boolean supports(AuthenticationToken token) {
        return true;
    }

    /**
     * 认证
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String token = (String) authenticationToken.getCredentials();
        //解密获取用户名
        return new AuthenticationInfo() {
            @Override
            public PrincipalCollection getPrincipals() {
                return null;
            }

            @Override
            public Object getCredentials() {
                return null;
            }
        };

    }
}
