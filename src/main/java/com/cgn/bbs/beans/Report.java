package com.cgn.bbs.beans;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.annotation.JSONField;
/**
 * 举报
 */
public class Report {
	private Integer reportId;//	int	11	0	0	0	-1	0				举报					0	0	0	0	-1	0	0
	private Integer accId;//	int	11	0	0	0	0	0									0	0	0	0	0	0	0
	private Integer reportAccId;//	int	11	0	-1	0	0	0									0	0	0	0	0	0	0
	private String reportType;//	enum	0	0	-1	0	0	0		''		举报类型			utf8	utf8_general_ci	0	0	0	0	0	0	0
	private String reportDetail;//	varchar	255	0	-1	0	0	0							utf8	utf8_general_ci	0	0	0	0	0	0	0
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date reportTime;//	datetime	0	0	0	0	0	0			CURRENT_TIMESTAMP						0	0	0	0	0	0	0
	public static Map<String,String> map=new HashMap<String, String>();
	
	private String accName;
	private String reportaccName;
	//============================
	static{
		//'MG','FF','XJ'
		map.put("MG", "敏感词汇");
		map.put("FF", "非法用户");
		map.put("XJ", "虚假信息");
	}
	public static Map<String, String> getReport() {
		return map;
	}
	public Integer getReportId() {
		return reportId;
	}
	public void setReportId(Integer reportId) {
		this.reportId = reportId;
	}
	public Integer getAccId() {
		return accId;
	}
	public void setAccId(Integer accId) {
		this.accId = accId;
	}
	public String getReportType() {
		return reportType;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	public String getReportDetail() {
		return reportDetail;
	}
	public void setReportDetail(String reportDetail) {
		this.reportDetail = reportDetail;
	}
	public Date getReportTime() {
		return reportTime;
	}
	public void setReportTime(Date reportTime) {
		this.reportTime = reportTime;
	}
	public String getAccName() {
		return accName;
	}
	public void setAccName(String accName) {
		this.accName = accName;
	}
	public Integer getReportAccId() {
		return reportAccId;
	}
	public void setReportAccId(Integer reportAccId) {
		this.reportAccId = reportAccId;
	}
	public String getReportaccName() {
		return reportaccName;
	}
	public void setReportaccName(String reportaccName) {
		this.reportaccName = reportaccName;
	}
	
}
