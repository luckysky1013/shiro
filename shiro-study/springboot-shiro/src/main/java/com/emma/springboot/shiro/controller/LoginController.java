package com.emma.springboot.shiro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.emma.springboot.shiro.service.LoginService;

/**
 * @author liujian
 * @date 2019/4/24
 */
@Controller
public class LoginController {

	@Autowired
	private LoginService loginService;
	
}
