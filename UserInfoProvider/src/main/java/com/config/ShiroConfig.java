package com.config;


import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.adapter.DefaultAdvisorAdapterRegistry;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.HashMap;
import java.util.Map;

/**
 * shiro 配置类
 */
@Configuration
public class ShiroConfig {

//    @Bean("securityManager")
    /*public DefaultWebSecurityManager getManager(MyRealm myRealm) {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator sessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        sessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(sessionStorageEvaluator);
        manager.setSubjectDAO(subjectDAO);
        manager.setRealm(myRealm);
        return manager;
    }*/

    /*@Bean("shiroFilter")
    public ShiroFilterFactoryBean factoryBean(DefaultSecurityManager securityManager) {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        //添加自己的过滤器
        Map<String, String> filterMap = new HashMap<String, String>();
//        filterMap.put("jwt", new JWTFilter());
//        factoryBean.setFilters(filterMap);
        factoryBean.setSecurityManager(securityManager);
        factoryBean.setUnauthorizedUrl("/401");

        *//**
         * 自定义 URL 规则
         *//*
        Map<String, String> filterUrlMap = new HashMap<String, String>();
        //所有请求通过我们自己的 filter
        filterUrlMap.put("/**","jwt");
        // 访问401和404页面不通过我们的Filter
        filterUrlMap.put("/401","anon");
        factoryBean.setFilterChainDefinitionMap(filterUrlMap);
        return factoryBean;

    }*/

    //添加注解支持

    /**
     *  开启shiro aop注解支持.
     *  使用代理方式;所以需要开启代码支持;
     */

    /*@Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }*/

   /* @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }*/



}
