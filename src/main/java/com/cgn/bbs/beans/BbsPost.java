package com.cgn.bbs.beans;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 帖子表
 */
public class BbsPost {
	private Integer postId;//	int	11	0	0	0	-1	0									0	0	0	0	-1	0	0
	private String postTitle;//	varchar	255	0	0	0	0	0							utf8	utf8_general_ci	0	0	0	0	0	0	0
	private String postDetil;//	varchar	255	0	0	0	0	0							utf8	utf8_general_ci	0	0	0	0	0	0	0
	private Integer accId;//	int	11	0	0	0	0	0									0	0	0	0	0	0	0
	@JSONField(format="yyyy-MM-dd hh:mm:ss")
	private Date createTime;//	datetime	0	0	0	0	0	0			CURRENT_TIMESTAMP						0	0	0	0	0	0	0
	private Integer modelId;//	int	11	0	0	0	0	0									0	0	0	0	0	0	0
	private Integer visitNum;//	int	11	0	0	0	0	0			0						0	0	0	0	0	0	0
	private Integer fileId;
	
	private String comNum;//回复数
	private String accName;
	private String modelName1;
	private String modelName2;
	private String accSex;
	private String role;
	private String headPath;
	//===================================
	public Integer getPostId() {
		return postId;
	}
	public void setPostId(Integer postId) {
		this.postId = postId;
	}
	public String getPostTitle() {
		return postTitle;
	}
	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}
	public String getPostDetil() {
		return postDetil;
	}
	public void setPostDetil(String postDetil) {
		this.postDetil = postDetil;
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
	public Integer getModelId() {
		return modelId;
	}
	public void setModelId(Integer modelId) {
		this.modelId = modelId;
	}
	public Integer getVisitNum() {
		return visitNum;
	}
	public void setVisitNum(Integer visitNum) {
		this.visitNum = visitNum;
	}
	public String getAccName() {
		return accName;
	}
	public void setAccName(String accName) {
		this.accName = accName;
	}
	public String getModelName1() {
		return modelName1;
	}
	public void setModelName1(String modelName1) {
		this.modelName1 = modelName1;
	}
	public String getModelName2() {
		return modelName2;
	}
	public void setModelName2(String modelName2) {
		this.modelName2 = modelName2;
	}
	public Integer getFileId() {
		return fileId;
	}
	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}
	public String getAccSex() {
		return accSex;
	}
	public void setAccSex(String accSex) {
		this.accSex = accSex;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getComNum() {
		return comNum;
	}
	public void setComNum(String comNum) {
		this.comNum = comNum;
	}
	public String getHeadPath() {
		return headPath;
	}
	public void setHeadPath(String headPath) {
		this.headPath = headPath;
	}
	
}
