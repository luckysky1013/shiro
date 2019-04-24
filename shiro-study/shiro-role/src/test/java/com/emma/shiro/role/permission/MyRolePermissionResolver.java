package com.emma.shiro.role.permission;

import java.util.Arrays;
import java.util.Collection;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.RolePermissionResolver;
import org.apache.shiro.authz.permission.WildcardPermission;

/**
 * @author liujian
 * @date 2019/4/24
 */
public class MyRolePermissionResolver implements RolePermissionResolver {


	@Override
	public Collection<Permission> resolvePermissionsInRole(String s) {
		if("role1".equals(s)){
			return Arrays.asList((Permission)new WildcardPermission("menu:*"));
		}
		return  null;
	}
}
