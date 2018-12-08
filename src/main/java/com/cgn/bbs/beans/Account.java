package com.cgn.bbs.beans;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import com.alibaba.fastjson.annotation.JSONField;
/**
 * 用户
 */
public class Account {
	private Integer accId;//	int	11	0	0	0	-1	0									0	0	0	0	-1	0	0
//	@NotBlank(message="用户名不能为空")
	private String accName;//	varchar	255	0	0	0	0	0				账户名			utf8	utf8_general_ci	0	0	0	0	0	0	0
	private String accPassword;//	varchar	255	0	0	0	0	0				密码			utf8	utf8_general_ci	0	0	0	0	0	0	0
//	@NotBlank(message="该项不能为空")
	private String accRealName;//	varchar	255	0	0	0	0	0				真实姓名/公司名			utf8	utf8_general_ci	0	0	0	0	0	0	0
//	@NotBlank(message="请填写证件号码")
	private String accIDNum;//	varchar	255	0	0	0	0	0				身份证号/公司营业执照编号			utf8	utf8_general_ci	0	0	0	0	0	0	0
//	@NotBlank(message="请填写真实姓名")
	private String registerName;//	varchar	255	0	0	0	0	0				注册人姓名			utf8	utf8_general_ci	0	0	0	0	0	0	0
//	@NotBlank(message="请填写您的身份证号")
	private String registerIDNum;//	varchar	255	0	0	0	0	0				注册人身份证			utf8	utf8_general_ci	0	0	0	0	0	0	0
//	@NotBlank(message="该项不能为空")
	private String accSex;//	enum	0	0	0	0	0	0		'男','女','保密'		性别(注册人)			utf8	utf8_general_ci	0	0	0	0	0	0	0
	private Integer accAge;//	int	4	0	0	0	0	0				年龄(注册人)					0	0	0	0	0	0	0
	private String hobby;//	varchar	255	0	-1	0	0	0				爱好(可不填)			utf8	utf8_general_ci	0	0	0	0	0	0	0
	private String nativePlace;//	varchar	255	0	0	0	0	0				籍贯(注册人)			utf8	utf8_general_ci	0	0	0	0	0	0	0
//	@NotBlank(message="请填写您的现住址")
	private String address;//	varchar	255	0	0	0	0	0				现住址(注册人)			utf8	utf8_general_ci	0	0	0	0	0	0	0
	private String role;//	enum	0	0	0	0	0	0		'student','company','admin'		角色(账户)			utf8	utf8_general_ci	0	0	0	0	0	0	0
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date createTime;//	datetime	0	0	0	0	0	0			CURRENT_TIMESTAMP	注册时间					0	0	0	0	0	0	0
//	@Size(min=6,max=16,message="密码长度为6-16位。")
	private String realPassword;//	varchar	255	0	0	0	0	0				真实登录密码			utf8	utf8_general_ci	0	0	0	0	0	0	0
	private String passKey;//	varchar	255	0	0	0	0	0				注册时的加密还原密钥UUID			utf8	utf8_general_ci	0	0	0	0	0	0	0
	private String schoolPro;//	varchar	255	0	-1	0	0	0				大学省					0	0	0	0	0	0	0
	private String schoolCity;//	varchar	255	0	-1	0	0	0				大学市					0	0	0	0	0	0	0
	private String schoolName;//	varchar	255	0	-1	0	0	0				大学名					0	0	0	0	0	0	0
	@NotBlank(message="邮箱地址不能为空")
	@Email(message="请输入正确的邮箱地址")
	private String email;//	varchar	255	0	0	0	0	0				邮箱			utf8	utf8_general_ci	0	0	0	0	0	0	0
	private boolean isInUse;//	tinyint	1	0	0	0	0	0			1	是否启用					0	0	0	0	0	0	0
	private Integer headImg;//	varchar	500	0	0	0	0	0			'/static/img/defaultHead.jpg'				utf8	utf8_general_ci	0	0	0	0	0	0	0

	private String headPath;
	public String getHeadPath() {
		return headPath;
	}
	public void setHeadPath(String headPath) {
		this.headPath = headPath;
	}
	private String models;
	private String beizhu;//备注 非数据库字段
	//================================
	public Integer getAccId() {
		return accId;
	}
	public void setAccId(Integer accId) {
		this.accId = accId;
	}
	public String getAccName() {
		return accName;
	}
	public void setAccName(String accName) {
		this.accName = accName;
	}
	public String getAccPassword() {
		return accPassword;
	}
	public void setAccPassword(String accPassword) {
		this.accPassword = accPassword;
	}
	public String getAccRealName() {
		return accRealName;
	}
	public void setAccRealName(String accRealName) {
		this.accRealName = accRealName;
	}
	public String getAccIDNum() {
		return accIDNum;
	}
	public void setAccIDNum(String accIDNum) {
		this.accIDNum = accIDNum;
	}
	public String getRegisterName() {
		return registerName;
	}
	public void setRegisterName(String registerName) {
		this.registerName = registerName;
	}
	public String getRegisterIDNum() {
		return registerIDNum;
	}
	public void setRegisterIDNum(String registerIDNum) {
		this.registerIDNum = registerIDNum;
	}
	public String getAccSex() {
		return accSex;
	}
	public void setAccSex(String accSex) {
		this.accSex = accSex;
	}
	public Integer getAccAge() {
		return accAge;
	}
	public void setAccAge(Integer accAge) {
		this.accAge = accAge;
	}
	public String getHobby() {
		return hobby;
	}
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	public String getNativePlace() {
		return nativePlace;
	}
	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getRealPassword() {
		return realPassword;
	}
	public void setRealPassword(String realPassword) {
		this.realPassword = realPassword;
	}
	public String getPassKey() {
		return passKey;
	}
	public void setPassKey(String passKey) {
		this.passKey = passKey;
	}
	public String getBeizhu() {
		return beizhu;
	}
	public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
	}
	public String getSchoolPro() {
		return schoolPro;
	}
	public void setSchoolPro(String schoolPro) {
		this.schoolPro = schoolPro;
	}
	public String getSchoolCity() {
		return schoolCity;
	}
	public void setSchoolCity(String schoolCity) {
		this.schoolCity = schoolCity;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean getIsInUse() {
		return isInUse;
	}
	public void setIsInUse(boolean isInUse) {
		this.isInUse = isInUse;
	}
	public String getModels() {
		return models;
	}
	public void setModels(String models) {
		this.models = models;
	}
	public Integer getHeadImg() {
		return headImg;
	}
	public void setHeadImg(Integer headImg) {
		this.headImg = headImg;
	}
	@Override
	public String toString() {
		return "Account [accId=" + accId + ", accName=" + accName
				+ ", accPassword=" + accPassword + ", accRealName="
				+ accRealName + ", accIDNum=" + accIDNum + ", registerName="
				+ registerName + ", registerIDNum=" + registerIDNum
				+ ", accSex=" + accSex + ", accAge=" + accAge + ", hobby="
				+ hobby + ", nativePlace=" + nativePlace + ", address="
				+ address + ", role=" + role + ", createTime=" + createTime
				+ ", realPassword=" + realPassword + ", passKey=" + passKey
				+ ", schoolPro=" + schoolPro + ", schoolCity=" + schoolCity
				+ ", schoolName=" + schoolName + ", email=" + email
				+ ", isInUse=" + isInUse + ", headImg=" + headImg
				+ ", headPath=" + headPath + ", models=" + models + ", beizhu="
				+ beizhu + "]";
	}
	
}
