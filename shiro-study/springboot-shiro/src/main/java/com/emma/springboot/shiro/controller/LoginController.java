package com.emma.springboot.shiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.emma.springboot.shiro.service.LoginService;

/**
 * @author liujian
 * @date 2019/4/24
 */
@Controller
public class LoginController {

	Logger logger= LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private LoginService loginService;

	//退出的时候是get请求，主要是用于退出
	@RequestMapping(value = "/login",method = RequestMethod.GET)
	public String login(){
		return "login";
	}

	@RequestMapping(value = "login",method = RequestMethod.POST)
	public String login(String username,String password){
		try {
			Subject subject = SecurityUtils.getSubject();
			UsernamePasswordToken token=new UsernamePasswordToken(username,password);
			subject.login(token);
			return "index";
		} catch (AuthenticationException e) {
			e.printStackTrace();
			logger.error("用户登录失败: "+e);
		}
		return "login";
	}

}
