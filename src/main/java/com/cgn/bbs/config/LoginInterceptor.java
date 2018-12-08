package com.cgn.bbs.config;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cgn.bbs.beans.Account;
import com.cgn.bbs.common.LoginAuth;

@Component
public class LoginInterceptor extends HandlerInterceptorAdapter{
	
//	public static boolean IsLogin=false;
	
	public boolean preHandle(HttpServletRequest request, 
	            HttpServletResponse response, Object handler) throws Exception { 
//		if (!handler.getClass().isAssignableFrom(HandlerMethod.class)) { 
//			return true; 
//		}
//		handlerSession(request);
//		System.out.println("进入拦截器");
//		AllController.getContextData().setRequest(request);
//		AllController.getContextData().setResponse(response);
//		Cookie[] cookies = request.getCookies();
//		if(IsLogin){
//			boolean isInvalid=false;
//			if(null!=cookies&&cookies.length>0){
//				for (Cookie cookie : cookies) {
//					if(cookie.getName().equals("USER_LOGIN_KEY")){//验证cookie是否失效
//						isInvalid=true;
//						break;
//					}
//				}
//			}
//			return isInvalid;
//		}else {
			final HandlerMethod handlerMethod = (HandlerMethod) handler; 
			final Method method = handlerMethod.getMethod(); 
//			final Class<?> clazz = method.getDeclaringClass(); 
			if (method.isAnnotationPresent(LoginAuth.class)){//有注解的查看是否登录
//				if (clazz.isAnnotationPresent(LoginAuth.class)||method.isAnnotationPresent(LoginAuth.class)){//有注解的查看是否登录
				request.setCharacterEncoding("UTF-8");
				response.setCharacterEncoding("UTF-8");
				HttpSession session = request.getSession();
				if(session.getAttribute("USER_LOGIN_KEY")!=null){
					Integer accId = ((Account) session.getAttribute("USER_LOGIN_KEY")).getAccId();
					if(accId==null||accId==0){
						response.sendRedirect("/toLogin");
					}else {
						return true;
					}
				}else {
					response.sendRedirect("/toLogin");
				}
//				if(null!=cookies&&cookies.length>0){
//					for (Cookie cookie : cookies) {
//						if(cookie.getName().equals("USER_LOGIN_KEY")){
//							//验证是否登陆成功
//							String userInfo=cookie.getValue();
//							Account account = new Account();
//							ReturnMap loginInfo=null;
//							if(!Utils.isNullOrEmpty(userInfo)){
//								account.setBeizhu(userInfo);
//								loginInfo = AllController.login(account, "1");//1为拦截器访问 2位页面访问
//							}
//							if(loginInfo.getCode()==100){
//								String redirect_url=request.getHeader("Referer");
////								URLEncoder.encode(redirect_url, "utf-8");
//								IsLogin=true;
//								response.sendRedirect(redirect_url);
//								return true;
//							}
//							IsLogin=false;
//							response.sendRedirect(request.getContextPath()+"/login");
//							return false;
//						}
//					}
//				}else {
//					IsLogin=false;
//					response.sendRedirect(request.getContextPath()+"/login");
//					return false;
//				}
			}
			return true;//无注解的可以通过
//		}
	} 
//	public void handlerSession(HttpServletRequest request) { 
//		String sessionId = request.getHeader(SESSION_KEY); 
//		if(org.apache.commons.lang3.StringUtils.isBlank(sessionId)){ 
//			sessionId=(String) request.getSession().getAttribute(SESSION_KEY);
//		} 
//		if (org.apache.commons.lang3.StringUtils.isNotBlank(sessionId)) { 
//			SessionData model = (SessionData) redisUtils.get(SESSION_KEY_PREFIX+sessionId); 
//			if (model == null) { 
//				return ; 
//			} 
//			request.setAttribute(SESSION_KEY,sessionId); 
//			Integer userCode = model.getUserCode(); 
//			if (userCode != null) { 
//				request.setAttribute(USER_CODE_SESSION_KEY, Long.valueOf(userCode)); 
//			} 
//			String mobile = model.getMobileNumber(); 
//			if (mobile != null) { 
//				request.setAttribute(MOBILE_NUMBER_SESSION_KEY, mobile); 
//			} 
//		} 
//		return ; 
//	} 
}
