package com.cgn.bbs.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cgn.bbs.common.Log;
import com.cgn.bbs.util.Utils;

/**  
 * 全局捕获异常类
 */
@ControllerAdvice
public class GlobalDefaultExceptionHandler {
	@Autowired
	private Log log;
	/**
	 * 返回为view时 返回modelandview
	 * 返回为String json时 返回String 并添加@ResponseBody
	 * defaultExceptionHandler 
	 *  
	 * @author Administrator  
	 * @param req
	 * @param e
	 * @return  
	 * @since JDK 1.7
	 */
	@ExceptionHandler(Exception.class)
	public ModelAndView defaultExceptionHandler(HttpServletRequest req,Exception e)
	{
		log.Error(Log.ERROR, "程序出错在"+req.getRequestURL()+"信息"+Utils.getStackTrace(e), new Error(e));
		return new ModelAndView("error");
	}
}
  
