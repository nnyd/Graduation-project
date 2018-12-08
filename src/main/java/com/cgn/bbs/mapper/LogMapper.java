package com.cgn.bbs.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

public interface LogMapper {
	
	@Insert(" insert into log (logInfo,logType) values (#{logInfo},#{logType}) ")
	void insertGeneral(@Param("logType") String logNum, @Param("logInfo") String massage);
	@Insert(" insert into log (logInfo,logType,warningOrErrorInfo) values (#{logInfo},#{logType},#{warningOrErrorInfo}) ")
	void insertWrong(@Param("logType") String logNum,@Param("logInfo") String massage,@Param("warningOrErrorInfo") String wrong);

}
