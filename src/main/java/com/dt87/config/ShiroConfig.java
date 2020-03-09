package com.dt87.config;
import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
        System.out.println("执行 ShiroFilterFactoryBean.shiroFilter()");
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //必须设置securityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //需要登录的接口，如果访问某个接口，需要登录却没登录，则调用此接口(如果不是前后端分离，则跳转页面)
        shiroFilterFactoryBean.setLoginUrl("/transform/need_login");
        //登录成功，跳转url，如果前后端分离，则没这个调用
        //shiroFilterFactoryBean.setSuccessUrl("/");
        //没有权限，未授权就会调用此方法， 先验证登录-》再验证是否有权限
        shiroFilterFactoryBean.setUnauthorizedUrl("/pub/not_permit");
        //拦截器路径，坑一，部分路径无法进行拦截，时有时无；因为同学使用的是hashmap, 无序的，应该改为LinkedHashMap
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        //退出过滤器
        filterChainDefinitionMap.put("/logout","logout");
        //匿名可以访问，也是就游客模式
        /*filterChainDefinitionMap.put("/pub/**","anon");
        //登录用户才可以访问
        filterChainDefinitionMap.put("/authc/**","authc");
        //管理员角色才可以访问
        filterChainDefinitionMap.put("/admin/**","roles[admin]");
        //有编辑权限才可以访问
        filterChainDefinitionMap.put("/video/update","perms[video_update]");*/

// 登录放行
        filterChainDefinitionMap.put("/login.html", "anon");
        filterChainDefinitionMap.put("/user/login", "anon");
        filterChainDefinitionMap.put("/user/index", "anon");
// 静态资源放行
        filterChainDefinitionMap.put("/resources/**", "anon");
        filterChainDefinitionMap.put("/system/**", "anon");
        filterChainDefinitionMap.put("*.js","anon");
        filterChainDefinitionMap.put("*.css","anon");
        filterChainDefinitionMap.put("*.json","anon");

        //坑二: 过滤链是顺序执行，从上而下，一般讲/** 放到最下面
        //authc : url定义必须通过认证才可以访问
        //anon  : url可以匿名访问
        filterChainDefinitionMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    // 这里是为了能在html页面引用shiro标签
    @Bean(name = "shiroDialect")
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }

    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //如果不是前后端分离，则不必设置下面的sessionManager
        //securityManager.setSessionManager(sessionManager());
        //设置realm（推荐放到最后，不然某些情况会不生效）
        securityManager.setRealm(customRealm());
        return securityManager;
    }

    @Bean
    public CustomerFealm customRealm(){
        CustomerFealm customRealm = new CustomerFealm();
        //customRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return customRealm;
    }
}
