package com.cgn.bbs.beans;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 审批表
 */
public class Examine {
	private Integer examineId;//	int	11	0	0	0	-1	0									0	0	0	0	-1	0	0
	private String examineType;//	enum	0	0	0	0	0	0		'model'		审批类型			utf8	utf8_general_ci	0	0	0	0	0	0	0
	private Integer pid;//	int	11	0	0	0	0	0									0	0	0	0	0	0	0
	private String name;//	varchar	255	0	0	0	0	0							utf8	utf8_general_ci	0	0	0	0	0	0	0
	private String status;//	enum	0	0	0	0	0	0		'DSP','TG','WTG'					utf8	utf8_general_ci	0	0	0	0	0	0	0
	private int accId;//	int	11	0	0	0	0	0				申请人					0	0	0	0	0	0	0
	@JSONField(format="yyyy-MM-dd hh:mm:ss")
	private Date createTime;
	
	private String accName;
	private String modelName;
	private String postTitle;
	private String postModel;
	private String role;
	//===================================
	public Integer getExamineId() {
		return examineId;
	}
	public void setExamineId(Integer examineId) {
		this.examineId = examineId;
	}
	public String getExamineType() {
		return examineType;
	}
	public void setExamineType(String examineType) {
		this.examineType = examineType;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getAccId() {
		return accId;
	}
	public void setAccId(int accId) {
		this.accId = accId;
	}
	public String getAccName() {
		return accName;
	}
	public void setAccName(String accName) {
		this.accName = accName;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public String getPostTitle() {
		return postTitle;
	}
	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}
	public String getPostModel() {
		return postModel;
	}
	public void setPostModel(String postModel) {
		this.postModel = postModel;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
}
