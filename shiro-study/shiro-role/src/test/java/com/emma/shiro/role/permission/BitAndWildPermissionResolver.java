package com.emma.shiro.role.permission;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.PermissionResolver;
import org.apache.shiro.authz.permission.WildcardPermission;

/**
 * @author liujian
 * @date 2019/4/24
 */
public class BitAndWildPermissionResolver implements PermissionResolver {
	@Override
	public Permission resolvePermission(String s) {
		if (s.startsWith("+")){
			return new BitPermission(s);
		}
		return new WildcardPermission(s);
	}
}
