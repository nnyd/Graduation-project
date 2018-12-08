package com.cgn.bbs.beans;

import java.util.HashMap;
import java.util.Map;

public class ReturnMap {
	//100成功 200失败 300特殊错误
		private int code;
		//提示信息
		private String massage;
		//用户要返回给浏览器的数据
		private Map<String,Object> extend=new HashMap<String,Object>();
		
		public ReturnMap() {
			super();
		}

		public ReturnMap(int code, String massage) {
			super();
			this.code = code;
			this.massage = massage;
		}

		public static ReturnMap success(){
			ReturnMap result=new ReturnMap();
			result.setCode(100);
			result.setMassage("操作成功");
			return result;
		}
		
		public static ReturnMap fail(){
			ReturnMap result=new ReturnMap();
			result.setCode(200);
			result.setMassage("操作失败");
			return result;
		}
		
		public static ReturnMap unknown(){
			ReturnMap result=new ReturnMap();
			result.setCode(300);
			result.setMassage("系统错误");
			return result;
		}
		public static ReturnMap noAuthorized(){
			ReturnMap result=new ReturnMap();
			result.setCode(400);
			result.setMassage("没有权限");
			return result;
		}
		public static ReturnMap noLogin(){
			ReturnMap result=new ReturnMap();
			result.setCode(500);
			result.setMassage("没有登录");
			return result;
		}
		public ReturnMap put(String key,Object value){
			this.getExtend().put(key, value);
			return this;
		}
		
		
		public int getCode() {
			return code;
		}
		public void setCode(int code) {
			this.code = code;
		}
		public String getMassage() {
			return massage;
		}
		public void setMassage(String massage) {
			this.massage = massage;
		}
		public Map<String, Object> getExtend() {
			return extend;
		}
		public void setExtend(Map<String, Object> extend) {
			this.extend = extend;
		}
		
}
