package com.cgn.bbs.beans;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class ContextData {
//	private HttpServletRequest request;
//	private HttpServletResponse response;
	ServletRequestAttributes attr=(ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
	private HttpServletRequest request = attr==null?null:attr.getRequest();
	private HttpServletResponse response = attr==null?null:attr.getResponse();
	private String role=null;
	private Map<String, Object> map=new HashMap<String, Object>();
	private Account account;
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public HttpServletResponse getResponse() {
		return response;
	}
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Map<String, Object> getMap() {
		return map;
	}
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	
}
