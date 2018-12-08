package com.cgn.bbs.beans;

import java.util.Date;

public class Dict {
	private Integer dictId;//	int	11	0	0	0	-1	0									0	0	0	0	-1	0	0
	private String dictType;//	enum	0	0	0	0	0	0		'ZD','JH'					utf8	utf8_general_ci	0	0	0	0	0	0	0
	private Integer tid;//	int	11	0	0	0	0	0									0	0	0	0	0	0	0
	private Date createTime;//	datetime	0	0	0	0	0	0			CURRENT_TIMESTAMP						0	0	0	0	0	0	0
	private String beizhu;//	varchar	255	0	-1	0	0	0							utf8	utf8_general_ci	0	0	0	0	0	0	0
	//==============================
	public Integer getDictId() {
		return dictId;
	}
	public void setDictId(Integer dictId) {
		this.dictId = dictId;
	}
	public String getDictType() {
		return dictType;
	}
	public void setDictType(String dictType) {
		this.dictType = dictType;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getBeizhu() {
		return beizhu;
	}
	public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
	}
	public Integer getTid() {
		return tid;
	}
	public void setTid(Integer tid) {
		this.tid = tid;
	}
	
}
