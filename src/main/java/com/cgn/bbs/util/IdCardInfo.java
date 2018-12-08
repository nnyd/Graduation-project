package com.cgn.bbs.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
/***********************
 * Author ：陈革楠     
 * Day ：2017年11月2日
 * Time ：下午5:44:05
 * Functions:    根据已经判断好的身份证信息 获取相关信息
 **********************/
public class IdCardInfo {
	
	/**
	 * 地区map
	 */
	@SuppressWarnings("serial")
	private static Map<String, String> cityCodeMap = new HashMap<String, String>() {
	    {
	            this.put("11", "北京");    
	            this.put("12", "天津");    
	            this.put("13", "河北");    
	            this.put("14", "山西");    
	            this.put("15", "内蒙古");    
	            this.put("21", "辽宁");    
	            this.put("22", "吉林");    
	            this.put("23", "黑龙江");    
	            this.put("31", "上海");    
	            this.put("32", "江苏");    
	            this.put("33", "浙江");    
	            this.put("34", "安徽");    
	            this.put("35", "福建");    
	            this.put("36", "江西");    
	            this.put("37", "山东");    
	            this.put("41", "河南");    
	            this.put("42", "湖北");    
	            this.put("43", "湖南");    
	            this.put("44", "广东");    
	            this.put("45", "广西");    
	            this.put("46", "海南");    
	            this.put("50", "重庆");    
	            this.put("51", "四川");    
	            this.put("52", "贵州");    
	            this.put("53", "云南");    
	            this.put("54", "西藏");    
	            this.put("61", "陕西");    
	            this.put("62", "甘肃");    
	            this.put("63", "青海");    
	            this.put("64", "宁夏");    
	            this.put("65", "新疆");    
	            this.put("71", "台湾");    
	            this.put("81", "香港");    
	            this.put("82", "澳门");    
	            this.put("91", "国外");    
	        } };
	
	/** 
     * 根据身份证的号码算出当前身份证持有者的性别和年龄 18位身份证 
     *  
     * @return 
     * @throws Exception 
     */  
    private static Map<String, Object> getCarInfo(String CardCode)  
            throws Exception {  
        Map<String, Object> map = new HashMap<String, Object>();  
        map.put("city", cityCodeMap.get(CardCode.substring(0,2)));
        String year = CardCode.substring(6, 10);// 得到年份  
        String yue = CardCode.substring(10, 12);// 得到月份  
        String day=CardCode.substring(12,14);//得到日  
        map.put("birth", year+"-"+yue+"-"+day);
        String sex;  
        if (Integer.parseInt(CardCode.substring(16).substring(0, 1)) % 2 == 0) {// 判断性别  
            sex = "女";  
        } else {  
            sex = "男";  
        }  
        Date date = new Date();// 得到当前的系统时间  
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
        String fyear = format.format(date).substring(0, 4);// 当前年份  
        String fyue = format.format(date).substring(5, 7);// 月份  
        // String fday=format.format(date).substring(8,10);  
        int age = 0;  
        if (Integer.parseInt(yue) <= Integer.parseInt(fyue)) { // 当前月份大于用户出身的月份表示已过生  
            age = Integer.parseInt(fyear) - Integer.parseInt(year) + 1;  
        } else {// 当前用户还没过生  
            age = Integer.parseInt(fyear) - Integer.parseInt(year);  
        }  
        map.put("sex", sex);  
        map.put("age", age);  
        return map;  
    }  
  
    /** 
     * 15位身份证的验证 
     *  
     * @param 
     * @throws Exception 
     */  
    private static Map<String, Object> getCarInfo15W(String card)  
            throws Exception {  
        Map<String, Object> map = new HashMap<String, Object>();  
        map.put("city", cityCodeMap.get(card.substring(0,2)));
        String uyear = "19" + card.substring(6, 8);// 年份  
        String uyue = card.substring(8, 10);// 月份  
        String uday=card.substring(10, 12);//日  
        map.put("birth", uyear+"-"+uyue+"-"+uday);
        String usex = card.substring(14, 15);// 用户的性别  
        String sex;  
        if (Integer.parseInt(usex) % 2 == 0) {  
            sex = "女";  
        } else {  
            sex = "男";  
        }  
        Date date = new Date();// 得到当前的系统时间  
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
        String fyear = format.format(date).substring(0, 4);// 当前年份  
        String fyue = format.format(date).substring(5, 7);// 月份  
        // String fday=format.format(date).substring(8,10);  
        int age = 0;  
        if (Integer.parseInt(uyue) <= Integer.parseInt(fyue)) { // 当前月份大于用户出身的月份表示已过生  
            age = Integer.parseInt(fyear) - Integer.parseInt(uyear) + 1;  
        } else {// 当前用户还没过生  
            age = Integer.parseInt(fyear) - Integer.parseInt(uyear);  
        }  
        map.put("sex", sex);  
        map.put("age", age);  
        return map;  
    }  
    
    public static Map<String, Object> getIdCardInfo(String idCard) throws Exception
    {
    	if(idCard.length()==18)
    	{
    		return getCarInfo(idCard);
    	}
    	else if(idCard.length()==15)
    	{
    		return getCarInfo15W(idCard);
    	}
    	else
    	{
    		return null;
    	}
    }
    
 
	public static void main(String[] args) throws Exception {  
		// 新身份证
        String idCardNew = "320503199512122511"; 
        // 旧身份证
        String idCardOld = "230503760612250";
        System.out.println(getIdCardInfo(idCardNew));
        System.out.println(getIdCardInfo(idCardOld));
    }  
} 
