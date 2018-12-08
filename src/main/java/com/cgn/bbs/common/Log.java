package com.cgn.bbs.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgn.bbs.mapper.LogMapper;

@Service
public class Log {
	public static final String INFO="INFO";
	public static final String DEBUG="DEBUG";
	public static final String ERROR="ERROR";
	public static final String WARNING="WARNING";
	public static final String RECORD="RECORD";
	@Autowired
	private LogMapper LogMapper;
	
	public void Info(String logNum, String massage){
		LogMapper.insertGeneral(logNum,massage);
	}
	public void Debug(String logNum,String massage){
		LogMapper.insertGeneral(logNum,massage);
	}
	public void Error(String logNum,String massage,Error error){
		LogMapper.insertWrong(logNum,massage,error.toString());
	}
	public void Warning(String logNum,String massage,Exception exception){
		LogMapper.insertWrong(logNum,massage,exception.toString());
	}
	public void Record(String logNum,String massage){
		LogMapper.insertGeneral(logNum,massage);
	}
}
