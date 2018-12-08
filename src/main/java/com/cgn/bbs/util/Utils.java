package com.cgn.bbs.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

public class Utils {
	
	public static boolean isNullOrEmpty(Object obj){
		if(null==obj)
			return true;
		String str=obj.toString().trim().toLowerCase();
		if("null".equals(str)||"".equals(str)||"{}".equals(str)||"[]".equals(str)||"()".equals(str))
			return true;
		return false;
	}
	/**
	 * 返回异常堆栈信息
	 * @param t
	 * @return
	 */
	public static String getStackTrace(Throwable t){
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		try{
			t.printStackTrace(pw);
			return sw.toString();
		}finally{
			pw.close();
		}
	}
	/**
	 * 返回异常堆栈信息
	 * @param t
	 * @return
	 */
	public static String getStackTrace2(Throwable t){
		ByteArrayOutputStream buf = new java.io.ByteArrayOutputStream();
		try {
			t.printStackTrace(new java.io.PrintWriter(buf, true));
			return buf.toString();
		}finally{
			try {
				buf.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
