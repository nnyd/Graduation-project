package com.cgn.bbs.beans;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 不良记录
 */
public class BadRecord {
	private Integer badRecId;//	int	11	0	0	0	-1	0									0	0	0	0	-1	0	0
	private Integer accId;//	int	11	0	0	0	0	0									0	0	0	0	0	0	0
	private Integer postId;//	int	11	0	-1	0	0	0									0	0	0	0	0	0	0
	private String recordDetail;//	varchar	255	0	-1	0	0	0				不良记录内容(如非法评论)			utf8	utf8_general_ci	0	0	0	0	0	0	0
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date recordTime;//	datetime	0	0	0	0	0	0			CURRENT_TIMESTAMP						0	0	0	0	0	0	0
	
	private String accName;
	private String postTitle;
	//==============================
	public Integer getBadRecId() {
		return badRecId;
	}
	public void setBadRecId(Integer badRecId) {
		this.badRecId = badRecId;
	}
	public Integer getAccId() {
		return accId;
	}
	public void setAccId(Integer accId) {
		this.accId = accId;
	}
	public Integer getPostId() {
		return postId;
	}
	public void setPostId(Integer postId) {
		this.postId = postId;
	}
	public String getRecordDetail() {
		return recordDetail;
	}
	public void setRecordDetail(String recordDetail) {
		this.recordDetail = recordDetail;
	}
	public Date getRecordTime() {
		return recordTime;
	}
	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
	}
	public String getAccName() {
		return accName;
	}
	public void setAccName(String accName) {
		this.accName = accName;
	}
	public String getPostTitle() {
		return postTitle;
	}
	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}
	
}
