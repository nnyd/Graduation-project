package com.cgn.bbs.beans;

import java.util.Date;
/**
 * 访问记录
 */
public class AccessRecord {
	private Integer recId;//	int	11	0	0	0	-1	0									0	0	0	0	-1	0	0
//	private Integer arId;//	int	11	0	0	0	0	0				用户访问模块记录					0	0	0	0	0	0	0
	private Integer accId;//	int	11	0	0	0	0	0									0	0	0	0	0	0	0
	private Integer modelId;//	int	11	0	0	0	0	0									0	0	0	0	0	0	0
	private Date time;//	datetime	0	0	0	0	0	0			CURRENT_TIMESTAMP						0	0	0	0	0	0	0
	//==================================
	public Integer getRecId() {
		return recId;
	}
	public void setRecId(Integer recId) {
		this.recId = recId;
	}
//	public Integer getArId() {
//		return arId;
//	}
//	public void setArId(Integer arId) {
//		this.arId = arId;
//	}
	public Integer getAccId() {
		return accId;
	}
	public void setAccId(Integer accId) {
		this.accId = accId;
	}
	public Integer getModelId() {
		return modelId;
	}
	public void setModelId(Integer modelId) {
		this.modelId = modelId;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	
}
