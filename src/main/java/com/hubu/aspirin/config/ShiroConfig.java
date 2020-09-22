package com.hubu.aspirin.config;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author alex
 */
@Configuration
public class ShiroConfig {
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(){
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        // 设置SecurityManager
        factoryBean.setSecurityManager(getDefaultWebSecurityManager());
        return factoryBean;
    }
    /**
     * 创建DefaultWebSecurityManager
     * @return 返回DefaultWebSecurityManager
     */
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 管理自定义realm
        securityManager.setRealm(getRealm());
        return securityManager;
    }

    /**
     * 创建Realm
     * @return 返回自定义realm
     */
    @Bean
    public MyRealm getRealm(){
        MyRealm myRealm = new MyRealm();
        // 管理密码比对器
        myRealm.setCredentialsMatcher(getHashedCrendtialsMatcher());
        return myRealm;
    }


    @Bean
    public HashedCredentialsMatcher getHashedCrendtialsMatcher(){
        // 密码比对器
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        // 加密方式
        matcher.setHashAlgorithmName("SHA-256");
        // 迭代次数
        matcher.setHashIterations(1024);
        return matcher;
    }
}
