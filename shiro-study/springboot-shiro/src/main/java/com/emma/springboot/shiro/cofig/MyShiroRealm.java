package com.emma.springboot.shiro.cofig;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.emma.springboot.shiro.domian.Permission;
import com.emma.springboot.shiro.domian.Role;
import com.emma.springboot.shiro.domian.User;
import com.emma.springboot.shiro.service.LoginService;

/**
 * @author liujian
 * @date 2019/4/24
 *
 * 实现AuthorizingRealm接口用户用户认证
 */
public class MyShiroRealm extends AuthorizingRealm{

	//查询用户
	@Autowired
	private LoginService loginService;

	//角色权限和对应权限添加
	//授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		//获取登录的用户名
		String name= (String) principalCollection.getPrimaryPrincipal();
		//根据用户名查询用户
		User user=loginService.findByUserName(name);
		SimpleAuthorizationInfo simpleAuthorizationInfo=new SimpleAuthorizationInfo();
		for (Role role:user.getRoles()){
			//添加角色
			simpleAuthorizationInfo.addRole(role.getRoleName());
			for(Permission permission:role.getPermissions()){
				//添加权限
				simpleAuthorizationInfo.addStringPermission(permission.getPerssion());
			}
		}
		return simpleAuthorizationInfo;

	}

	//用户认证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
			throws AuthenticationException {
		SimpleAuthenticationInfo info=null;
		UsernamePasswordToken token= (UsernamePasswordToken) authenticationToken;
		//获取用户信息（登录的唯一凭证 手机号/邮箱）
		String username=token.getUsername();
		if(username==null){
			throw new AccountException("Null username are not allowed by this realm");
		}
		if(token.getPassword().length<1){
			throw new UnknownAccountException("Null password are not allowed by this realm");
		}

		User user = null;
		try {
			user = loginService.findByUserName(username);
			if(user!=null){
				info=new SimpleAuthenticationInfo(username,user.getPassword(),getName());
			}else{
				throw new AccountException("No this user.");
			}
		} catch (Exception e) {
			throw  new AuthenticationException(e);
		}

		return info;
	}
}
