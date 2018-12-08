package com.cgn.bbs.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cgn.bbs.common.LoginAuth;

@RestController
public class TestController {

	@LoginAuth
	@RequestMapping("/bbs/test")
	public String test() {
		System.out.println(11);
		return "s11";
	}
	
	@RequestMapping("/bbs/test2")
	public String test2() {
		return "s22";
	}
	@RequestMapping("/test3")
	public String test3(HttpServletResponse response) {
		Cookie cookie = new Cookie("USER_LOGIN_KEY","asdzxc");
		response.addCookie(cookie);
		System.out.println(33);
		return "s33";
	}
//	@RequestMapping("/login")
//	public String test4() {
//		System.out.println(44);
//		return "s44";
//	}
}
