package com.hubu.aspirin.config;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;

/**
 * @author alex
 */
@Configuration
public class ShiroConfig {
    // hash迭代次数
    public static final Integer HASH_ITERATIONS = 1024;

    // 加密hash算法
    private static final String HASH_ALGORITHM_NAME = "SHA-256";
    // Cookie保存时间
    private static final Integer COOKIE_MAX_AGE = 60 * 60 * 24 * 10;

    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean() {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        // 设置SecurityManager
        factoryBean.setSecurityManager(getDefaultWebSecurityManager());

        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();

        // 为Knife4J文档放开权限
        filterChainDefinitionMap.put("/swagger-resources", "anon");
        filterChainDefinitionMap.put("/v2/api-docs", "anon");
        filterChainDefinitionMap.put("/v2/api-docs-ext", "anon");
        filterChainDefinitionMap.put("/webjars/**", "anon");
        filterChainDefinitionMap.put("/doc.html", "anon");

        filterChainDefinitionMap.put("/api/test/**", "anon");
        filterChainDefinitionMap.put("/api/account/login", "anon");
        filterChainDefinitionMap.put("/api/account/simpleRegister", "anon");
        filterChainDefinitionMap.put("/**", "authc");
        // url过滤链
        factoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        // 使用自定义的过滤器
        LinkedHashMap<String, Filter> filterMap = new LinkedHashMap<>();
        filterMap.put("authc", new ShiroFormAuthenticationFilter());
        // 添加过滤器
        factoryBean.setFilters(filterMap);

        return factoryBean;
    }

    /**
     * 创建DefaultWebSecurityManager
     *
     * @return 返回DefaultWebSecurityManager
     */
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 管理自定义realm
        securityManager.setRealm(getRealm());
        securityManager.setRememberMeManager(cookieRememberMeManager());
        return securityManager;
    }

    /**
     * 创建Realm
     *
     * @return 返回自定义realm
     */
    @Bean
    public MyRealm getRealm() {
        MyRealm myRealm = new MyRealm();
        // 管理密码比对器
        myRealm.setCredentialsMatcher(getHashedCrendtialsMatcher());
        return myRealm;
    }


    @Bean
    public HashedCredentialsMatcher getHashedCrendtialsMatcher() {
        // 密码比对器
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        // 加密方式
        matcher.setHashAlgorithmName(HASH_ALGORITHM_NAME);
        // 迭代次数
        matcher.setHashIterations(HASH_ITERATIONS);
        return matcher;
    }


    /**
     * 实现RememberMe功能
     * 1. 配置Cookie对象
     * 记住我的cookie：rememberMe
     *
     * @return SimpleCookie rememberMeCookie
     */
    @Bean
    public SimpleCookie rememberMeCookie() {
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        //simpleCookie.setHttpOnly(true);
        //单位(秒)1天
        simpleCookie.setMaxAge(COOKIE_MAX_AGE);
        return simpleCookie;
    }

    /**
     * 2.配置cookie管理对象
     *
     * @return CookieRememberMeManager
     */
    @Bean
    public CookieRememberMeManager cookieRememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        // 由于每次程序重启都会重新生成一对密钥, 导致rememberMe失效，所以需要设置固定的秘钥
        cookieRememberMeManager.setCipherKey(Base64.decode("6ZmI6I2j5Y+R5aSn5ZOlAA=="));
        return cookieRememberMeManager;
    }

    /**
     * 下面两个方法开启shiro 注解支持. 使以下注解能够生效 :
     * 需要认证 {@link org.apache.shiro.authz.annotation.RequiresAuthentication RequiresAuthentication}
     * 需要用户 {@link org.apache.shiro.authz.annotation.RequiresUser RequiresUser}
     * 需要访客 {@link org.apache.shiro.authz.annotation.RequiresGuest RequiresGuest}
     * 需要角色 {@link org.apache.shiro.authz.annotation.RequiresRoles RequiresRoles}
     * 需要权限 {@link org.apache.shiro.authz.annotation.RequiresPermissions RequiresPermissions}
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(getDefaultWebSecurityManager());
        return authorizationAttributeSourceAdvisor;
    }

    @Bean
    @ConditionalOnMissingBean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator app = new DefaultAdvisorAutoProxyCreator();
        app.setProxyTargetClass(true);
        return app;
    }

}
