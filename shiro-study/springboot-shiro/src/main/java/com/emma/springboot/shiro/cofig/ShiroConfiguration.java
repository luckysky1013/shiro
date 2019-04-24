package com.emma.springboot.shiro.cofig;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liujian
 * @date 2019/4/24
 */
@Configuration
public class ShiroConfiguration {

	//将自己的验证方式加入容器
	@Bean
	public MyShiroRealm myShiroRealm(){
		MyShiroRealm myShiroRealm=new MyShiroRealm();
		return myShiroRealm;
	}

	//权限管理 ，配置主要是Realm的管理认证
	@Bean(name = "securityManager")
	public DefaultWebSecurityManager securityManager(){
		DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();
		securityManager.setRealm(myShiroRealm());
		return securityManager;
	}

	@Bean
	public ShiroFilterFactoryBean shiroFilterFactoryBean(){
		ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager());
		Map<String,String> map=new HashMap<>();
		//登出
		map.put("/logout","logout");
		//对所有用户认证
		map.put("/**","authc");
		//登录
		shiroFilterFactoryBean.setLoginUrl("/login");
		//登录
		shiroFilterFactoryBean.setLoginUrl("/login");
		//错误页面，认证不通过跳转
		shiroFilterFactoryBean.setUnauthorizedUrl("/error");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
		return  shiroFilterFactoryBean;
	}


	//加入注解的使用，不加入这个注解不生效
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
		AuthorizationAttributeSourceAdvisor auth = new AuthorizationAttributeSourceAdvisor();
		auth.setSecurityManager(securityManager());
		return auth;
	}


}
