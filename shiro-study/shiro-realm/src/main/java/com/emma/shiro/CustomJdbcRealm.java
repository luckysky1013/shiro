package com.emma.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;

/**
 * @author liujian
 * @date 2019/4/23
 */
public class CustomJdbcRealm {

	public static void main(String [] args){
		//创建工厂
		Factory<SecurityManager> factory=new IniSecurityManagerFactory("classpath:shiro-jdbc-realm.ini");

		//获取SecurityManager实例，并绑定给securityutils
		SecurityManager securityManager=factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);

		//获取subject  及创建用户名/密码身份验证token（即用户身份/凭证）
		Subject subject=SecurityUtils.getSubject();
		UsernamePasswordToken token=new UsernamePasswordToken("zhang","1233");

		try {
			//登录  、身份认证
			subject.login(token);
		} catch (AuthenticationException e) {
			e.printStackTrace();
		}

		Assert.assertEquals(true, subject.isAuthenticated()); //断言用户已经登录

		//6、退出
		subject.logout();
	}
}
