package com.cgn.bbs.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	/*
    private static final SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat DEFAULT_DATETIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat DEFAULT_YMD_FORMAT = new SimpleDateFormat("yyyyMMdd");
    private static final SimpleDateFormat DEFAULT_YM_FORMAT = new SimpleDateFormat("yyyyMM");
    private static final SimpleDateFormat DEFAULT_YMDHMS_FORMAT = new SimpleDateFormat("yyyyMMddHHmmss");
    */
	
	private static final String PATTERN_DATE_FORMAT = "yyyy-MM-dd";
    private static final String PATTERN_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final String PATTERN_YMD_FORMAT = "yyyyMMdd";
    private static final String PATTERN_YM_FORMAT = "yyyyMM";
    private static final String PATTERN_YMDHMS_FORMAT = "yyyyMMddHHmmss";
    
    private DateUtils() {
    }
    
    private static SimpleDateFormat getDateFormat(String pattern) {
    	SimpleDateFormat sdf = new SimpleDateFormat(pattern);
    	sdf.setLenient(false);
    	return sdf; 
    }
    
    public static String get(String foramt, Date date) {
    	if (foramt == null || date == null)
    		return null;
    	return DateUtils.getDateFormat(foramt).format(date);
    }
    
    public static Date get(String format, String dateString) {
    	if (format == null || dateString == null)
    		return null;
    	
    	dateString = dateString.trim();
    	if (dateString.length() == 0)
    		return null;
    	
    	try {
			return DateUtils.getDateFormat(format).parse(dateString);
		} catch (ParseException e) {
//			Log.getLogger().warning(e);
			return null;
		}
    }

    public static Date getNow() {
        return Calendar.getInstance().getTime();
    }

    public static String getDateString(Date date) {
    	return DateUtils.get(DateUtils.PATTERN_DATE_FORMAT, date);
    }
    
    public static Date getDate(String date) {
    	return DateUtils.get(DateUtils.PATTERN_DATE_FORMAT, date);
    }
    
    public static String getDateTimeString(Date date) {
    	return DateUtils.get(DateUtils.PATTERN_DATETIME_FORMAT, date);
    }
    
    public static Date getDateTime(String date) {
    	return DateUtils.get(DateUtils.PATTERN_DATETIME_FORMAT, date);
    }
    
    public static String getYMDString(Date date) {
    	return DateUtils.get(DateUtils.PATTERN_YMD_FORMAT, date);
    }
    
    public static Date getYMD(String date) {
    	return DateUtils.get(DateUtils.PATTERN_YMD_FORMAT, date);
    }
    
    public static String getYMString(Date date) {
    	return DateUtils.get(DateUtils.PATTERN_YM_FORMAT, date);
    }
    
    public static Date getYM(String date){
    	return DateUtils.get(DateUtils.PATTERN_YM_FORMAT, date);
    }
    
    public static String getYMDHMSString(Date date) {
    	return DateUtils.get(DateUtils.PATTERN_YMDHMS_FORMAT, date);
    }
    
    public static Date getYMDHMS(String date) {
    	return DateUtils.get(DateUtils.PATTERN_YMDHMS_FORMAT, date);
    }
    
    public static int getYear(Date date) {
    	Calendar c = Calendar.getInstance();
    	c.setTime(date);
        return c.get(Calendar.YEAR);
    }

    /**
     * 获得月份
     * @param date 日期
     * @return 月份（1-12）
     */
    public static int getMonth(Date date) {
    	Calendar c = Calendar.getInstance();
    	c.setTime(date);
    	return c.get(Calendar.MONTH) + 1;
    }

    public static int getDay(Date date) {
    	Calendar c = Calendar.getInstance();
    	c.setTime(date);
    	return c.get(Calendar.DATE);
    }
    
    /**
     * 获得星期
     * @param date 日期
     * @return 星期一：1，……，星期日：7
     */
    public static int getWeekDay(Date date) {
    	Calendar c = Calendar.getInstance();
    	c.setTime(date);
    	int weekDay = c.get(Calendar.DAY_OF_WEEK);
    	
    	if (weekDay == Calendar.SUNDAY)
    		weekDay = 7;
    	else
    		weekDay--;
    	return weekDay;
    }
    
    public static int getHour(Date date) {
    	Calendar c = Calendar.getInstance();
    	c.setTime(date);
    	return c.get(Calendar.HOUR_OF_DAY);
    }
   
    public static Date addYears(Date date, int years){
    	return add(date, years, Calendar.YEAR);
    }
    
    public static Date addDays(Date date, int days) {
        return add(date, days, Calendar.DATE);
    }

    public static Date addMonths(Date date, int months) {
        return add(date, months, Calendar.MONTH);
    }
    
    public static Date addHours(Date date,int hours){
    	return add(date, hours, Calendar.HOUR);
    }
    
    public static Date addMinutes(Date date, int minutes){
    	return add(date, minutes, Calendar.MINUTE);
    }

    private static Date add(Date date, int amount, int field) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(field, amount);
        return c.getTime();
    }
    
    public static Date getMonthLastDate(Date date) {
        date = addMonths(date, 1);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DATE, 1);
        date = c.getTime();
        date = addDays(date, -1);
        return date;
    }
    
    public static Date getMonthFirstDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DATE, 1);
        return c.getTime();
    }
    
    public static boolean isYMDString(String ymd) {
    	if (ymd == null)
    		return false;
    	ymd = ymd.trim();
    	if (ymd.length() != 8)
    		return false;
    	Date date = getYMD(ymd);
		return date != null;
    }
    
    public static boolean isYMString(String ym) {
    	if (ym == null)
    		return false;
    	ym = ym.trim();
    	if (ym.length() != 6)
    		return false;
    	Date date = getYM(ym);
		return date != null;
    }
    public static boolean isDateString(String ymd){
    	if (ymd == null)
    		return false;
    	ymd = ymd.trim();
    	if (ymd.length() != 10)
    		return false;
    	Date date = getDate(ymd);
    	return date != null;
    }
    
    public static Date setDateTime(Date date, int year, int month, int day, int hour, int minute, int second) {
    	return setDateTimeEx(date, year, month, day, hour, minute, second);
    }
    
    public static Date setDateTimeEx(Date date, Integer year, Integer month, Integer day, Integer hour, Integer minute, Integer second) {
    	Calendar c = Calendar.getInstance();
        c.setTime(date);
        
        if (year != null)
        	c.set(Calendar.YEAR, year);
        
        if (month != null)
        	c.set(Calendar.MONTH, month - 1);
        
        if (day != null)
        	c.set(Calendar.DATE, day);
        
        if (hour != null)
        	c.set(Calendar.HOUR_OF_DAY, hour);
        
        if (minute != null)
        	c.set(Calendar.MINUTE, minute);
        
        if (second != null)
        	c.set(Calendar.SECOND, second);
        
        return c.getTime();
    }
    
    public static Date setTime(Date date, int hour, int minute, int second) {
    	return setTimeEx(date, hour, minute, second);
    }
    
    public static Date setTimeEx(Date date, Integer hour, Integer minute, Integer second) {
    	Calendar c = Calendar.getInstance();
        c.setTime(date);
       
        if (hour != null)
        	c.set(Calendar.HOUR_OF_DAY, hour);
        
        if (minute != null)
        	c.set(Calendar.MINUTE, minute);
        
        if (second != null)
        	c.set(Calendar.SECOND, second);
        
        return c.getTime();
    }
    
    public static Date setDate(Date date, int year, int month, int day) {
        return setDateEx(date, year, month, day);
    }
    
    public static Date setDateEx(Date date, Integer year, Integer month, Integer day) {
    	Calendar c = Calendar.getInstance();
        c.setTime(date);
        
        if (year != null)
        	c.set(Calendar.YEAR, year);
        
        if (month != null)
        	c.set(Calendar.MONTH, month - 1);
        
        if (day != null)
        	c.set(Calendar.DATE, day);
        
        return c.getTime();
    }
    
    public static Date setMonth(Date date, int month) {
    	Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.MONTH, month - 1);
        return c.getTime();
    }
    
    public static Date setDay(Date date, int day) {
    	Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DATE, day);
        return c.getTime();
    }
    
//    public static boolean isHoliday(Date date) {
////    	Date day = setDate(date, 0, 0, 0);
//    	JDBCTool jdbc = null;
//    	String strDay = DateUtils.getDateString(date);
//    	try {
//    		String sql = "SELECT Holiday FROM Holiday WHERE date = ?";
//    		jdbc = JDBCTool.getMySQL();
//    		jdbc.open();
//    		Boolean holiday = jdbc.selectScalar(sql, strDay);
//    		if (holiday == null)
//    			return false;
//    		return holiday;
//    	} catch (Exception ex) {
//    		Log.getLogger().warning(ex);
//    		return false;
//    	}
//    	finally {
//    		JDBCTool.Close(jdbc);
//    	}
//    }
//    
//    /**
//     * 获取第n个工作日
//     * @param startDate 开始日期
//     * @param days 第几个工作日
//     * @param includeStartDate 从开始日期算起还是从次日算起
//     * @return 工作日
//     */
//    public static Date getWorkday(Date startDate, int days, boolean includeStartDate) {
//    	Date day = setTime(startDate,0,0,0);
//    	if (!includeStartDate)
//    		day = addDays(day, 1);
//    	if (days < 1)
//    		return day;
//    	JDBCTool jdbc = null;
//    	try {
//    		String sql = "SELECT date FROM Holiday WHERE date >= ? AND Holiday = False ORDER BY date ASC LIMIT ?";
//    		jdbc = JDBCTool.getMySQL();
//    		jdbc.open();
//    		List<Object[]> daylist = jdbc.select(sql, day, days);
//    		return (Date)daylist.get(daylist.size() - 1)[0];
//    	} catch (Exception ex) {
//    		Log.getLogger().warning(ex);
//    		return day;
//    	}
//    	finally {
//    		JDBCTool.Close(jdbc);
//    	}
//    }
}
