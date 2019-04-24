package com.emma.springboot.shiro.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emma.springboot.shiro.domian.User;
import com.emma.springboot.shiro.repository.UserRepository;
import com.emma.springboot.shiro.service.LoginService;

/**
 * @author liujian
 * @date 2019/4/24
 */
@Service
@Transactional
public class LoginServiceImpl implements LoginService{

	@Autowired
	private UserRepository userRepository;

	@Override
	public User findByUserName(String username) {

		return userRepository.findByUsername(username);
	}
}
