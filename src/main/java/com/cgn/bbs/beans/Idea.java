package com.cgn.bbs.beans;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 意见建议
 */
public class Idea {
	private Integer ideaId;//	int	11	0	0	0	-1	0				意见建议					0	0	0	0	-1	0	0
	private String ideaContent;//	varchar	255	0	0	0	0	0							utf8	utf8_general_ci	0	0	0	0	0	0	0
	private Integer accId;//	int	11	0	0	0	0	0									0	0	0	0	0	0	0
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date createTime;//	datetime	0	0	0	0	0	0			CURRENT_TIMESTAMP						0	0	0	0	0	0	0
	
	private String accName;
	//============================
	public Integer getIdeaId() {
		return ideaId;
	}
	public void setIdeaId(Integer ideaId) {
		this.ideaId = ideaId;
	}
	public String getIdeaContent() {
		return ideaContent;
	}
	public void setIdeaContent(String ideaContent) {
		this.ideaContent = ideaContent;
	}
	public Integer getAccId() {
		return accId;
	}
	public void setAccId(Integer accId) {
		this.accId = accId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getAccName() {
		return accName;
	}
	public void setAccName(String accName) {
		this.accName = accName;
	}
	
}
