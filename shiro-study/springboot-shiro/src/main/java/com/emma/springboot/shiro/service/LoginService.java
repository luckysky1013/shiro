package com.emma.springboot.shiro.service;

import com.emma.springboot.shiro.domian.User;

/**
 * @author liujian
 * @date 2019/4/24
 */
public interface LoginService {
	public User findByUserName(String username) ;
}
