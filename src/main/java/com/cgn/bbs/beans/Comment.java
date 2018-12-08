package com.cgn.bbs.beans;

import java.util.Date;

/**
 * 评论
 */
public class Comment {
	private Integer commentId;//	int	11	0	0	0	-1	0									0	0	0	0	-1	0	0
	private Integer postId;//	int	11	0	0	0	0	0									0	0	0	0	0	0	0
	private Integer accId;//	int	11	0	0	0	0	0									0	0	0	0	0	0	0
	private String commentContent;//	varchar	255	0	0	0	0	0				评论内容(用于显示)			utf8	utf8_general_ci	0	0	0	0	0	0	0
	private String commentRealContent;//	varchar	255	0	0	0	0	0				评论真实内容			utf8	utf8_general_ci	0	0	0	0	0	0	0
	private Integer pid;//	int	11	0	0	0	0	0				记录评论父id					0	0	0	0	0	0	0
	private Date createTime;//	datetime	0	0	0	0	0	0			CURRENT_TIMESTAMP						0	0	0	0	0	0	0
	private Integer lou;//	int	11	0	0	0	0	0				楼层					0	0	0	0	0	0	0
	private boolean deleted;//	tinyint	1	0	0	0	0	0			0						0	0	0	0	0	0	0

	private String accName;
	private String headPath;
	private String accSex;
	private String role;
	private String accNamePare;
	private String commentContentPare;
	private Integer louPare;
	//====================================
	public Integer getCommentId() {
		return commentId;
	}
	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}
	public Integer getPostId() {
		return postId;
	}
	public void setPostId(Integer postId) {
		this.postId = postId;
	}
	public Integer getAccId() {
		return accId;
	}
	public void setAccId(Integer accId) {
		this.accId = accId;
	}
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	public String getCommentRealContent() {
		return commentRealContent;
	}
	public void setCommentRealContent(String commentRealContent) {
		this.commentRealContent = commentRealContent;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
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
	public Integer getLou() {
		return lou;
	}
	public void setLou(Integer lou) {
		this.lou = lou;
	}
	public String getAccNamePare() {
		return accNamePare;
	}
	public void setAccNamePare(String accNamePare) {
		this.accNamePare = accNamePare;
	}
	public String getCommentContentPare() {
		return commentContentPare;
	}
	public void setCommentContentPare(String commentContentPare) {
		this.commentContentPare = commentContentPare;
	}
	public Integer getLouPare() {
		return louPare;
	}
	public void setLouPare(Integer louPare) {
		this.louPare = louPare;
	}
	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	public String getHeadPath() {
		return headPath;
	}
	public void setHeadPath(String headPath) {
		this.headPath = headPath;
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
	
}
