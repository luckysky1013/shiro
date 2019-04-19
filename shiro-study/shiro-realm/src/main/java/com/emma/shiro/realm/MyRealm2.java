package com.emma.shiro.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

/**
 * @author liujian
 * @date 2019/4/19
 */
public class MyRealm2 implements Realm{
	@Override
	public String getName() {
		return "myRealm2";
	}

	@Override
	public boolean supports(AuthenticationToken authenticationToken) {
		return authenticationToken instanceof UsernamePasswordToken;
	}

	@Override
	public AuthenticationInfo getAuthenticationInfo(AuthenticationToken authenticationToken)
			throws AuthenticationException {
		//获取用户名
		String username=(String) authenticationToken.getPrincipal();
		//获取密码
		String password=new String ((char[])authenticationToken.getCredentials() );
		if(!"wanng".equals(username)){
			//用户名错误
			throw new UnknownAccountException();
		}
		if(!"123".equals(password)){
			//密码错误
			throw new IncorrectCredentialsException();
		}
		return new SimpleAuthenticationInfo(username,password,getName());
	}
}
