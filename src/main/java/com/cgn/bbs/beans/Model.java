package com.cgn.bbs.beans;

import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

public class Model {
	private Integer modelId;//	int	11	0	0	0	-1	0									0	0	0	0	-1	0	0
	private String modelName;//	varchar	255	0	0	0	0	0							utf8	utf8_general_ci	0	0	0	0	0	0	0
	private Integer pid;//	int	11	0	0	0	0	0				父模块id					0	0	0	0	0	0	0
	private Integer createAccountId;//	int	11	0	0	0	0	0				添加用户id					0	0	0	0	0	0	0
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date createTime;//	datetime	0	0	0	0	0	0			CURRENT_TIMESTAMP						0	0	0	0	0	0	0
	private Integer visitNum;//	int	11	0	0	0	0	0			0						0	0	0	0	0	0	0
	private List<Model> childList;
	
	private String pareName;
	private String accName;
	//===============================
	public Integer getModelId() {
		return modelId;
	}
	public void setModelId(Integer modelId) {
		this.modelId = modelId;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public Integer getCreateAccountId() {
		return createAccountId;
	}
	public void setCreateAccountId(Integer createAccountId) {
		this.createAccountId = createAccountId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getVisitNum() {
		return visitNum;
	}
	public void setVisitNum(Integer visitNum) {
		this.visitNum = visitNum;
	}
	public List<Model> getChildList() {
		return childList;
	}
	public void setChildList(List<Model> childList) {
		this.childList = childList;
	}
	public String getAccName() {
		return accName;
	}
	public void setAccName(String accName) {
		this.accName = accName;
	}
	public String getPareName() {
		return pareName;
	}
	public void setPareName(String pareName) {
		this.pareName = pareName;
	}
	
}
